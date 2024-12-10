from fastapi import FastAPI

from api import main_router
from api.settings import settings, Settings
from infrastructure.datasource.postgres.source import AsyncPostgres


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


app = Application(app=FastAPI(), settings=settings)
