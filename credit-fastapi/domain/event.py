from typing import TypeVar, Generic

from support.common.base_dto import RootModel

T = TypeVar("T")


class DomainEvent(RootModel, Generic[T]):
    entity: T
