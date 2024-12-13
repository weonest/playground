from support.common.base_domain import BaseDomain


class Credit(BaseDomain):
    id: str
    amount: int = 0

    # 가드 패턴을 이용한 비지니스 로직 검증
    def model_post_init(self, __context) -> None:
        if self.amount < 0:
            raise ValueError("수량은 0 이상이어야 합니다.")

    def __setattr__(self, key, value):
        if key == str(self.id):
            raise AttributeError("도메인 식별자는 조작할 수 없습니다.")
        super().__setattr__(key, value)

    def update_amount(self, amount: int):
        self.amount = amount
