from fastapi import APIRouter

from api.credit.controller import credit_router
from api.mock_user.controller import mock_user_router
from api.mock_user.event_handler import handler_user_created_event

main_router = APIRouter(prefix="/api")

main_router.include_router(credit_router)
main_router.include_router(mock_user_router)
