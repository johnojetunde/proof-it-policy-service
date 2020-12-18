package com.proofit.policy.domain.factory;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.exception.PolicyServiceException;
import com.proofit.policy.domain.service.abstracts.RiskTypePremiumCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class RiskTypePremiumCalculatorFactory {

    private final Map<RiskType, RiskTypePremiumCalculator> riskCalculatorMap;

    public RiskTypePremiumCalculatorFactory(@Qualifier("riskCalculators") Map<RiskType, RiskTypePremiumCalculator> riskCalculatorMap) {
        this.riskCalculatorMap = riskCalculatorMap;
    }

    public RiskTypePremiumCalculator getRiskCalculator(RiskType riskType) {
        return Optional.ofNullable(riskCalculatorMap.get(riskType))
                .orElseThrow(() -> new PolicyServiceException("Unable to get risk calculator"));
    }
}
