from sqlalchemy.ext.asyncio import AsyncSession


async def charge_credit(db_session: AsyncSession, credit_id: str, amount: int):
    pass