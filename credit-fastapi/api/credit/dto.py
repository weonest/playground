from business.credit.dto import CreditCreateCommand
from support.common.base_dto import CamelDTO


class CreateCreditRequest(CamelDTO):
    amount: int

    def to_command(self) -> CreditCreateCommand:
        return CreditCreateCommand(amount=self.amount)
