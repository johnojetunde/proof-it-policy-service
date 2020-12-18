package com.proofit.policy.domain.service.concretes;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.factory.RiskTypePremiumCalculatorFactory;
import com.proofit.policy.domain.models.Policy;
import com.proofit.policy.domain.models.PolicyObject;
import com.proofit.policy.domain.models.PolicySubObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.proofit.policy.fixtures.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultPremiumCalculatorTest {

    @Mock
    private RiskTypePremiumCalculatorFactory riskCalculatorFactory;
    @InjectMocks
    private DefaultPremiumCalculator defaultPremiumCalculator;

    @BeforeEach
    void setUp() {
        when(riskCalculatorFactory.getRiskCalculator(eq(RiskType.FIRE)))
                .thenReturn(new FireRiskTypePremiumCalculator());
        when(riskCalculatorFactory.getRiskCalculator(eq(RiskType.THEFT)))
                .thenReturn(new TheftRiskTypePremiumCalculator());
    }

    @Test
    void calculate_whenPolicyHas1Object() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(100), RiskType.FIRE),
                subObject(BigDecimal.valueOf(8), RiskType.THEFT)
        );

        PolicyObject policyObject = policyObject(subObjects);
        Policy policyModel = policy(List.of(policyObject));

        BigDecimal expectedResult = BigDecimal.valueOf(2.28);
        BigDecimal result = defaultPremiumCalculator.calculate(policyModel);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void calculate_whenPolicyHasMultipleObject() {
        List<PolicySubObject> subObjects1 = List.of(subObject(BigDecimal.valueOf(500), RiskType.FIRE));
        List<PolicySubObject> subObjects2 = List.of(subObject(BigDecimal.valueOf(102.51), RiskType.THEFT));

        Policy policyModel = policy(List.of(policyObject(subObjects1), policyObject(subObjects2)));

        BigDecimal expectedResult = BigDecimal.valueOf(17.13);
        BigDecimal result = defaultPremiumCalculator.calculate(policyModel);

        assertThat(result).isEqualTo(expectedResult);
    }
}