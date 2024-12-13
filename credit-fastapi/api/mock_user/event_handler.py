from fastapi import Depends
from fastapi_events.handlers.local import local_handler
from fastapi_events.typing import Event
from sqlalchemy.ext.asyncio import AsyncSession

from api.dependencies.db_session import get_async_db_session
from business.credit import credit_service
from domain.mock_user.event.user_event import UserCreatedEvent


@local_handler.register(event_name="user_created_event")
async def handler_user_created_event(
        event: Event,
        db_session: AsyncSession = Depends(get_async_db_session),
):
    print("UserCreatedEvent is handled")
    event_name, event = event
    event = UserCreatedEvent.model_validate(event)
    await credit_service.invoke(db_session, event)
