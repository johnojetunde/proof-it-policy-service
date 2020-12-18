package com.proofit.policy.domain.service.concretes;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.models.PolicySubObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.proofit.policy.fixtures.TestFixtures.subObject;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TheftRiskTypePremiumCalculatorTest {

    private final TheftRiskTypePremiumCalculator calculator = new TheftRiskTypePremiumCalculator();

    @Test
    void calculate_whenSumInsuredIs15() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(5), RiskType.THEFT),
                subObject(BigDecimal.valueOf(10), RiskType.THEFT)
        );
        BigDecimal expectedPremiumAmount = BigDecimal.valueOf(15).multiply(BigDecimal.valueOf(0.05));
        BigDecimal premiumAmount = calculator.calculate(subObjects);

        assertThat(premiumAmount).isEqualTo(expectedPremiumAmount);
    }

    @Test
    void calculate_whenSumInsuredIsLessThan15() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(5), RiskType.FIRE),
                subObject(BigDecimal.valueOf(4), RiskType.FIRE)
        );
        BigDecimal expectedPremiumAmount = BigDecimal.valueOf(9).multiply(BigDecimal.valueOf(0.11));
        BigDecimal premiumAmount = calculator.calculate(subObjects);

        assertThat(premiumAmount).isEqualTo(expectedPremiumAmount);
    }

    @Test
    void calculate_whenSumInsuredIsGreaterThan15() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(50), RiskType.FIRE),
                subObject(BigDecimal.valueOf(60), RiskType.FIRE)
        );
        BigDecimal expectedPremiumAmount = BigDecimal.valueOf(110).multiply(BigDecimal.valueOf(0.05));
        BigDecimal premiumAmount = calculator.calculate(subObjects);

        assertThat(premiumAmount).isEqualTo(expectedPremiumAmount);
    }
}