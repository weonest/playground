from pydantic_settings import BaseSettings
from dotenv import load_dotenv
from os.path import dirname, join


dotenv_path = join(dirname(__file__), "./", ".env")
load_dotenv(dotenv_path)


class Settings(BaseSettings):
    # db settings
    db_host: str = "localhost"
    db_port: int = 5432
    db_user: str = "postgres"
    db_password: str
    db_name: str = ""
    db_pool_size: int = 10
    db_max_overflow: int = 5

