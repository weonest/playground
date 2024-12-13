from fastapi import Depends
from fastapi_events.handlers.local import local_handler
from fastapi_events.typing import Event
from sqlalchemy.ext.asyncio import AsyncSession

from business.credit import credit_service
from domain.mock_user.event.user_event import UserCreatedEvent
from infrastructure.datasource.postgres.source import AsyncPostgres


async def get_async_session() -> AsyncSession:
    db = AsyncPostgres()
    return db.get_session_maker()


@local_handler.register(event_name="user_created_event")
async def handler_user_created_event(
        event: Event,
        db_session: AsyncSession = Depends(get_async_session),
):
    print("UserCreatedEvent is handled")
    event_name, event = event
    event = UserCreatedEvent.model_validate(event)
    try:
        await credit_service.invoke(db_session, event)
    except Exception as e:
        await db_session.rollback()
        raise e
    finally:
        await db_session.commit()
        await db_session.close()
