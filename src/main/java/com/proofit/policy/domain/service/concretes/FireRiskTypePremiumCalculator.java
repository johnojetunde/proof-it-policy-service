package com.proofit.policy.domain.service.concretes;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.models.PolicySubObject;
import com.proofit.policy.domain.service.abstracts.RiskTypePremiumCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation for FIRE - RiskType
 */
@Service
public class FireRiskTypePremiumCalculator implements RiskTypePremiumCalculator {

    @Override
    public BigDecimal calculate(List<PolicySubObject> policySubObjects) {
        BigDecimal sumInsured = sumInsuredAmount(policySubObjects);
        BigDecimal coefficient = getCoEfficient(sumInsured);
        return sumInsured.multiply(coefficient);
    }

    private BigDecimal getCoEfficient(BigDecimal insuredAmount) {
        return isSumInsuredOver100(insuredAmount)
                ? BigDecimal.valueOf(0.024)
                : BigDecimal.valueOf(0.014);
    }

    private boolean isSumInsuredOver100(BigDecimal insuredAmount) {
        return insuredAmount.compareTo(BigDecimal.valueOf(100)) > 0;
    }

    @Override
    public RiskType type() {
        return RiskType.FIRE;
    }
}
