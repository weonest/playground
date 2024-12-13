from pydantic import ConfigDict, BaseModel


class BaseDomain(BaseModel):

    model_config = ConfigDict(
        from_attributes=True,
        use_enum_values=True,
        populate_by_name=True,
        validate_assignment=True,
    )
