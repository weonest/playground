from infrastructure.datasource.postgres import AsyncPostgres


async def get_async_db_session():
    db = AsyncPostgres()
    async with db.get_session() as session:
        yield session
