import logging
import timeit
from contextlib import asynccontextmanager

from fastapi import FastAPI, Request
from fastapi_events.handlers.local import local_handler
from fastapi_events.middleware import EventHandlerASGIMiddleware

from api import main_router
from api.exception_handler import add_exception_handler
from api.settings import settings, Settings
from infrastructure.datasource.postgres.source import AsyncPostgres


@asynccontextmanager
async def lifespan(app: FastAPI):
    database = AsyncPostgres()
    database.initialize(
        host=settings.db_host,
        port=settings.db_port,
        user=settings.db_user,
        password=settings.db_password,
        db_name=settings.db_name,
        pool_size=settings.db_pool_size,
        max_overflow=settings.db_max_overflow,
        debug=settings.local,
    )
    yield


class Application:

    def __init__(self, app: FastAPI, settings: Settings):
        self.app = app
        self.settings = settings
        self.setup()

    def __call__(self):
        return self.app

    def setup(self):
        database = AsyncPostgres()
        database.initialize(
            host=self.settings.db_host,
            port=self.settings.db_port,
            user=self.settings.db_user,
            password=self.settings.db_password,
            db_name=self.settings.db_name,
            pool_size=self.settings.db_pool_size,
            max_overflow=self.settings.db_max_overflow,
            debug=self.settings.local,
        )
        self.app.include_router(main_router)
        self.app.add_middleware(EventHandlerASGIMiddleware, handlers=[local_handler])
        add_exception_handler(self.app)

        @self.app.middleware("http")
        async def log_middleware(request: Request, call_next):
            logging.basicConfig(level=logging.INFO)
            tic = timeit.default_timer()
            try:
                response = await call_next(request)
                toc = timeit.default_timer()
                if 200 <= response.status_code < 400:
                    header_info = {key: value for key, value in request.headers.items()}
                    logging.info(
                        f"""
                        method : {request.method}
                        url : {request.url}
                        pathParams : {request.path_params}
                        queryParams : {request.query_params}
                        header : {header_info}
                        timeMeasure : {(toc - tic):.5f} ms
                        """
                    )
                return response
            except Exception as e:
                raise e


app = Application(
    app=FastAPI(lifespan=lifespan, docs_url="/api/docs", swagger_ui_parameters={"operationsSorter": "function"}),
    settings=settings
)
