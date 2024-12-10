import logging
from datetime import datetime

from fastapi import FastAPI
from pydantic import Field
from fastapi.responses import JSONResponse

from support.common.base import CamelDTO
from support.common.exception import CustomException


class ErrorResponse(CamelDTO):
    status_code: int
    time: str = Field(default_factory=lambda: datetime.now().isoformat())
    type: str
    code: str
    message: str


def create_error_response(type: str, code: str, message: str, status_code: int = 400):
    return JSONResponse(
        content=ErrorResponse(
            status_code=status_code,
            type=type,
            code=code,
            message=message,
        ).model_dump()
    )


def add_exception_handler(app: FastAPI):
    @app.exception_handler(CustomException)
    async def handler_custom_exception(request, exc: CustomException):
        logging.warning(
            f"""
            method : {request.method}
            url : {request.url}
            pathParams : {request.path_params}
            queryParams : {request.query_params}
            header : {{key: value for key, value in request.headers.items()}}
            message : {exc.message}
            """
        )
        return create_error_response(exc.type, exc.code, exc.message)
