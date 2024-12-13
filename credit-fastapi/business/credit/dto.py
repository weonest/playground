from support.common.base_dto import RootModel


class CreditCreateCommand(RootModel):
    amount: int
