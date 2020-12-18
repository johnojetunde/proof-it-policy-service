package com.proofit.policy.domain.service.concretes;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.factory.RiskTypePremiumCalculatorFactory;
import com.proofit.policy.domain.models.Policy;
import com.proofit.policy.domain.models.PolicyObject;
import com.proofit.policy.domain.models.PolicySubObject;
import com.proofit.policy.domain.service.abstracts.PremiumCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import static com.proofit.policy.domain.util.FunctionUtil.emptyIfNullStream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultPremiumCalculator implements PremiumCalculator {

    private final RiskTypePremiumCalculatorFactory calculatorFactory;

    public DefaultPremiumCalculator(RiskTypePremiumCalculatorFactory calculatorFactory) {
        this.calculatorFactory = calculatorFactory;
    }

    @Override
    public BigDecimal calculate(Policy policy) {
        var policySubjects = emptyIfNullStream(policy.getObjects())
                .map(PolicyObject::getSubObjects)
                .flatMap(List::stream)
                .collect(toList());

        return calculate(policySubjects).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculate(List<PolicySubObject> subObjects) {
        Map<RiskType, List<PolicySubObject>> subObjectMap = groupSubjectsByType(subObjects);
        return subObjectMap.entrySet().stream()
                .map(s -> calculatorFactory.getRiskCalculator(s.getKey()).calculate(s.getValue()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private Map<RiskType, List<PolicySubObject>> groupSubjectsByType(List<PolicySubObject> subObjects) {
        return emptyIfNullStream(subObjects)
                .collect(groupingBy(PolicySubObject::getType));
    }
}
