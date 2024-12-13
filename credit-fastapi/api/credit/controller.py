from fastapi import APIRouter, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from api.credit.dto import CreateCreditRequest
from api.dependencies.db_session import get_async_db_session
from business.credit import credit_service
from support.common.base_dto import ResponseDTO

credit_router = APIRouter(prefix="/v0/credits", tags=["크레딧"])


@credit_router.post("", summary="크레딧 충전")
async def create_credit(
        request_body: CreateCreditRequest,
        db_session: AsyncSession = Depends(get_async_db_session),
) -> ResponseDTO:
    await db_session.execute("SELECT 1")
    await credit_service.create(db_session, request_body.to_command())
    return ResponseDTO.ok()
