package com.proofit.policy.domain.service.abstracts;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.models.PolicySubObject;

import java.math.BigDecimal;
import java.util.List;

import static com.proofit.policy.domain.util.FunctionUtil.emptyIfNullStream;

/**
 * RiskTypePremiumCalculator: this interface should be implemented if we add a new RiskType.
 * The implementation is the only thing that needs to be done when a new RiskType is added
 */
public interface RiskTypePremiumCalculator {
    BigDecimal calculate(List<PolicySubObject> policySubObjectList);

    RiskType type();

    default BigDecimal sumInsuredAmount(List<PolicySubObject> policySubObjectList) {
        return emptyIfNullStream(policySubObjectList)
                .map(PolicySubObject::getSumInsured)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
