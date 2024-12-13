from fastapi import APIRouter

from business.mock_user import user_service
from support.common.base_dto import ResponseDTO

mock_user_router = APIRouter(prefix="/v0/users", tags=["유저"])


@mock_user_router.post("", summary="유저 생성")
async def user_create(
) -> ResponseDTO:
    await user_service.create_user()
    return ResponseDTO.ok()
