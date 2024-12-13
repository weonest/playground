from domain.event import DomainEvent
from domain.mock_user.entity.user import User


class UserCreatedEvent(DomainEvent[User]):
    __event_name__ = "user_created_event"

    entity: User
