import threading
from contextlib import asynccontextmanager
from typing import Optional, AsyncGenerator

from sqlalchemy.ext.asyncio import AsyncEngine, async_sessionmaker, create_async_engine, AsyncSession

from infrastructure.datasource.exception import DatabaseInitializeError
from support.common.singleton import Singleton, initialize_once


class AsyncPostgres(Singleton):
    @initialize_once
    def __init__(self):
        super().__init__()
        self.engine: Optional[AsyncEngine] = None
        self.session_maker: Optional[async_sessionmaker] = None
        self.__singleton_lock = threading.Lock()

    def initialize(
            self,
            host: str,
            port: int,
            user: str,
            password: str,
            db_name: str,
            debug: bool = False,
            pool_size: Optional[int] = 10,
            max_overflow: Optional[int] = 5,
    ):
        with self.__singleton_lock:
            if not self.engine:
                db_url = f"postgresql+psycopg://{user}:{password}@{host}:{port}/{db_name}"
                self.engine = create_async_engine(db_url, echo=debug, pool_size=pool_size, max_overflow=max_overflow)
                self.session_maker = async_sessionmaker(bind=self.engine)

    def get_session_maker(self) -> AsyncSession:
        if self.session_maker is None:
            raise DatabaseInitializeError
        return self.session_maker()

    @asynccontextmanager
    async def get_session(self) -> AsyncGenerator[AsyncSession, None]:
        if self.session_maker is None:
            raise DatabaseInitializeError
        async_session = self.session_maker()
        try:
            yield async_session
            await async_session.commit()
            await async_session.close()
        except Exception as e:
            await async_session.rollback()
            await async_session.close()
            raise e