from support.common.base import CamelDTO


class ChargeCreditRequest(CamelDTO):
    credit_id: str
    amount: int
