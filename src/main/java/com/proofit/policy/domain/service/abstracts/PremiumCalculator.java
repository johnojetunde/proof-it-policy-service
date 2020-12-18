package com.proofit.policy.domain.service.abstracts;

import com.proofit.policy.domain.models.Policy;

import java.math.BigDecimal;

public interface PremiumCalculator {
    BigDecimal calculate(Policy policy);
}
