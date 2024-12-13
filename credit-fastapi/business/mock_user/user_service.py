from fastapi_events.dispatcher import dispatch

from domain.mock_user.entity.user import User
from domain.mock_user.event.user_event import UserCreatedEvent


async def create_user():
    # 유저 생성 동작...
    print("유저 생성")
    user_created_event = UserCreatedEvent(entity=User(id="1", name="test"))
    dispatch(user_created_event)
