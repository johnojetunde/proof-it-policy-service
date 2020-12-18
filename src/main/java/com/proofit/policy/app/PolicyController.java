package com.proofit.policy.app;

import com.proofit.policy.app.model.PolicyRequestModel;
import com.proofit.policy.domain.service.abstracts.PremiumCalculator;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/policies", produces = APPLICATION_JSON_VALUE)
public class PolicyController {

    private final PremiumCalculator premiumCalculator;

    @PostMapping(value = "/calculate-premium", consumes = APPLICATION_JSON_VALUE)
    public ResponseModel get(@Valid @RequestBody PolicyRequestModel policy) {
        return new ResponseModel(premiumCalculator.calculate(policy.toModel()));
    }

    @Value
    static class ResponseModel {
        BigDecimal premium;
    }
}
