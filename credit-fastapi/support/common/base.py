from typing import Optional, Generic, TypeVar

from pydantic import BaseModel, ConfigDict
from pydantic.alias_generators import to_camel


T = TypeVar("T")


class CamelDTO(BaseModel):
    model_config = ConfigDict(
        alias_generator=to_camel,
        populate_by_name=True,
    )


class BaseDTO(BaseModel):
    model_config = ConfigDict(
        from_attributes=True,
        use_enum_values=True,
        populate_by_name=True,
        validate_assignment=True,
    )


class PageDTO(BaseDTO, Generic[T]):
    data: T | list[T]
    page: int
    page_size: int
    total_elements: int
    total_pages: int


class ResponseDTO(CamelDTO, Generic[T]):
    status_code: int = 200
    data: T | list[T]

    @staticmethod
    def ok():
        return ResponseDTO(status_code=200, data={"status": "OK"})
