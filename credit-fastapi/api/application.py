from fastapi import FastAPI
from pydantic_settings import BaseSettings


class Application:

    def __init__(self, app: FastAPI, settings: BaseSettings):
        self.app = app
        self.settings = settings

