from os.path import join, dirname

from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    local: bool = False

    # db settings
    db_host: str = "localhost"
    db_port: int = 5432
    db_user: str = "postgres"
    db_password: str
    db_name: str = ""
    db_pool_size: int = 10
    db_max_overflow: int = 5

    class Config:
        env_file = join(dirname(__file__), ".env")


settings = Settings()
