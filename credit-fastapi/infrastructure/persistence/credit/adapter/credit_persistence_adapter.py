from sqlalchemy import insert
from sqlalchemy.ext.asyncio import AsyncSession

from domain.credit.entity.credit import Credit
from infrastructure.persistence.credit.entity.credit_entity import CreditEntity


async def create(db_session: AsyncSession, credit: Credit):
    stmt = insert(CreditEntity).values(**credit.model_dump())
    await db_session.execute(stmt)
