from fastapi import APIRouter

from api.credit.controller import credit_router

main_router = APIRouter(prefix="/api")

main_router.include_router(credit_router)