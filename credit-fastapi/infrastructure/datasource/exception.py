from support.common.exception import CustomException, DEFAULT_MESSAGE


class DatabaseException(CustomException):
    def __init__(self, code_number: int, message: str = DEFAULT_MESSAGE) -> None:
        super().__init__(type="DB", code_number=code_number, message=message)


class DatabaseInitializeError(DatabaseException):
    def __init__(self, message: str = DEFAULT_MESSAGE) -> None:
        super().__init__(code_number=1, message=message)
