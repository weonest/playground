from fastapi import APIRouter, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from api.credit.dto import ChargeCreditRequest
from api.dependencies.db_session import get_async_db_session
from business.credit import service

credit_router = APIRouter(prefix="/v0/credits", tags=["크레딧"])


@credit_router.post("", summary="크레딧 충전")
async def charge_credit(
        request_body: ChargeCreditRequest,
        db_session: AsyncSession = Depends(get_async_db_session),
):
    await service.charge_credit(db_session, request_body.amount)
