from abc import ABC
from functools import wraps


class Singleton(ABC):
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(Singleton, cls).__new__(cls)
        return cls._instance


def initialize_once(init_method):
    @wraps(init_method)
    def _impl(self, *method_args, **method_kwargs):
        initialized = getattr(self, "__initialized", False)
        if initialized:
            return
        else:
            self.__initialized = True
            return init_method(self, *method_args, **method_kwargs)
    return _impl
