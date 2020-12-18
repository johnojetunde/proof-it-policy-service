package com.proofit.policy.domain.factory;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.exception.PolicyServiceException;
import com.proofit.policy.domain.service.abstracts.RiskTypePremiumCalculator;
import com.proofit.policy.domain.service.concretes.FireRiskTypePremiumCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RiskTypePremiumCalculatorFactoryTest {

    private final Map<RiskType, RiskTypePremiumCalculator> calculatorMap = Map.of(RiskType.FIRE, new FireRiskTypePremiumCalculator());
    private final RiskTypePremiumCalculatorFactory factory = new RiskTypePremiumCalculatorFactory(calculatorMap);

    @Test
    void getCalculator_whenNotImplemented() {
        assertThatThrownBy(() -> factory.getRiskCalculator(RiskType.THEFT))
                .isInstanceOf(PolicyServiceException.class)
                .hasMessage("Unable to get risk calculator");
    }

    @Test
    void getCalculator_whenImplemented() {
        assertThat(factory.getRiskCalculator(RiskType.FIRE).type()).isEqualTo(RiskType.FIRE);
    }
}