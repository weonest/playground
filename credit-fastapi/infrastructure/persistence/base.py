from datetime import datetime

from sqlalchemy import DateTime
from sqlalchemy.orm import declarative_base, Mapped, mapped_column

Base = declarative_base()
target_metadata = Base.metadata


class BaseEntity(Base):
    __abstract__ = True

    created_at: Mapped[datetime] = mapped_column(
        DateTime(timezone=True),
        nullable=False,
        default=lambda: datetime.now(),
    )
    updated_at: Mapped[datetime] = mapped_column(
        DateTime(timezone=True),
        nullable=False,
        default=lambda: datetime.now(),
        onupdate=lambda: datetime.now(),
    )
