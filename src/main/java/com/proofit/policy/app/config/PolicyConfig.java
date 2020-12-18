package com.proofit.policy.app.config;

import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.service.abstracts.RiskTypePremiumCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.proofit.policy.domain.util.FunctionUtil.emptyIfNullStream;

@Configuration
@RequiredArgsConstructor
public class PolicyConfig {

    private final List<RiskTypePremiumCalculator> riskTypeCalculators;

    @Bean("riskCalculators")
    public Map<RiskType, RiskTypePremiumCalculator> getCalculatorMap() {
        return emptyIfNullStream(riskTypeCalculators)
                .collect(Collectors.toMap(RiskTypePremiumCalculator::type, Function.identity()));
    }
}
