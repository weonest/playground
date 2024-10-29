from fastapi import FastAPI
from pydantic_settings import BaseSettings

from api import main_router


class Application:

    def __init__(self, app: FastAPI, settings: BaseSettings):
        self.app = app
        self.settings = settings
        self.setup()

    def __call__(self):
        return self.app

    def setup(self):
        self.app.include_router(main_router)


app = Application(app=FastAPI(), settings=BaseSettings())
