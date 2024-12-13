from sqlalchemy.ext.asyncio import AsyncSession
from ulid import ULID

from business.credit.dto import CreditCreateCommand
from domain.credit.entity.credit import Credit
from domain.mock_user.event.user_event import UserCreatedEvent
from infrastructure.persistence.credit.adapter import credit_persistence_adapter


async def create(db_session: AsyncSession, command: CreditCreateCommand):
    id = str(ULID())
    credit = Credit(id=id, amount=command.amount)
    await credit_persistence_adapter.create(db_session=db_session, credit=credit)


async def invoke(db_session: AsyncSession, event: UserCreatedEvent):
    default_amount = 3  # 환경 변수로 관리하면 좋을듯
    credit = Credit(id=event.entity.id, amount=default_amount)
    print(event.entity.id)
    print(credit.id)
    await credit_persistence_adapter.create(db_session=db_session, credit=credit)
