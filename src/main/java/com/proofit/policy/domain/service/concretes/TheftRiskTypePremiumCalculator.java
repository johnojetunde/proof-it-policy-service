package com.proofit.policy.domain.service.concretes;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.models.PolicySubObject;
import com.proofit.policy.domain.service.abstracts.RiskTypePremiumCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TheftRiskTypePremiumCalculator implements RiskTypePremiumCalculator {
    @Override
    public BigDecimal calculate(List<PolicySubObject> policySubObjects) {
        BigDecimal sumInsured = sumInsuredAmount(policySubObjects);
        BigDecimal coefficient = getCoEfficient(sumInsured);
        return sumInsured.multiply(coefficient);
    }

    private BigDecimal getCoEfficient(BigDecimal insuredAmount) {
        return isSumInsuredOver15(insuredAmount)
                ? BigDecimal.valueOf(0.05)
                : BigDecimal.valueOf(0.11);
    }

    private boolean isSumInsuredOver15(BigDecimal insuredAmount) {
        return insuredAmount.compareTo(BigDecimal.valueOf(15)) >= 0;
    }

    @Override
    public RiskType type() {
        return RiskType.THEFT;
    }
}
