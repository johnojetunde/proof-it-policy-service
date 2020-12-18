package com.proofit.policy.app.model;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.models.PolicySubObject;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class PolicyRequestSubObject {
    @NotBlank(message = "Policy sub object name is required")
    private String name;
    @NotNull
    @Min(value = 1)
    private BigDecimal sumInsured;
    @NotNull
    private RiskType type;

    public PolicySubObject toModel() {
        return new PolicySubObject()
                .setName(getName())
                .setSumInsured(getSumInsured())
                .setType(getType());
    }
}
