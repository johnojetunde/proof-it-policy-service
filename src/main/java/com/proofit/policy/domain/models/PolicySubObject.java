package com.proofit.policy.domain.models;

import com.proofit.policy.domain.enums.RiskType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class PolicySubObject {
    private String name;
    private BigDecimal sumInsured;
    private RiskType type;
}
