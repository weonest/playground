
from sqlalchemy.ext.asyncio import AsyncSession
from ulid import ULID

from business.credit.dto import CreditCreateCommand
from domain.credit.entity.credit import Credit
from infrastructure.persistence.credit.adapter import credit_persistence_adapter


async def create(db_session: AsyncSession, command: CreditCreateCommand):
    id = str(ULID())
    credit = Credit(id=id, amount=command.amount)
    await credit_persistence_adapter.create(db_session=db_session, credit=credit)
