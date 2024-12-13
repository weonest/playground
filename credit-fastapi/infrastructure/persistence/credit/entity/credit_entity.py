from sqlalchemy import String, Integer
from sqlalchemy.orm import Mapped, mapped_column

from infrastructure.persistence.base import BaseEntity


class CreditEntity(BaseEntity):
    __tablename__ = "credits"

    id: Mapped[str] = mapped_column(String(26), nullable=False, primary_key=True)
    amount: Mapped[int] = mapped_column(Integer, nullable=False)
