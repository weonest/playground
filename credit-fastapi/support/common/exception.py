from abc import ABC


DEFAULT_CODE_PREFIX = "UNKNOWN"
DEFAULT_MESSAGE = "알 수 없는 오류가 발생했습니다."
DEFAULT_CODE_NUMBER_LENGTH = 3


class CustomException(ABC, Exception):
    def __init__(self, type: str = DEFAULT_CODE_PREFIX, code_number: int = 0, message: str = DEFAULT_MESSAGE):
        self.type = type
        self.code = str(code_number).zfill(DEFAULT_CODE_NUMBER_LENGTH)
        self.message = message
