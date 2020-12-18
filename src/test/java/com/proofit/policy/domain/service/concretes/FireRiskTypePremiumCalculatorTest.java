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
class FireRiskTypePremiumCalculatorTest {

    private final FireRiskTypePremiumCalculator calculator = new FireRiskTypePremiumCalculator();

    @Test
    void calculate_whenSumInsuredIs100() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(60), RiskType.FIRE),
                subObject(BigDecimal.valueOf(40), RiskType.FIRE)
        );
        BigDecimal expectedPremiumAmount = BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(0.014));
        BigDecimal premiumAmount = calculator.calculate(subObjects);

        assertThat(premiumAmount).isEqualTo(expectedPremiumAmount);
    }

    @Test
    void calculate_whenSumInsuredIsLessThan100() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(50), RiskType.FIRE),
                subObject(BigDecimal.valueOf(40), RiskType.FIRE)
        );
        BigDecimal expectedPremiumAmount = BigDecimal.valueOf(90).multiply(BigDecimal.valueOf(0.014));
        BigDecimal premiumAmount = calculator.calculate(subObjects);

        assertThat(premiumAmount).isEqualTo(expectedPremiumAmount);
    }

    @Test
    void calculate_whenSumInsuredIsGreaterThan100() {
        List<PolicySubObject> subObjects = List.of(
                subObject(BigDecimal.valueOf(50), RiskType.FIRE),
                subObject(BigDecimal.valueOf(60), RiskType.FIRE)
        );
        BigDecimal expectedPremiumAmount = BigDecimal.valueOf(110).multiply(BigDecimal.valueOf(0.024));
        BigDecimal premiumAmount = calculator.calculate(subObjects);

        assertThat(premiumAmount).isEqualTo(expectedPremiumAmount);
    }
}