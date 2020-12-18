package com.proofit.policy.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofit.policy.app.model.PolicyRequestModel;
import com.proofit.policy.app.model.PolicyRequestObject;
import com.proofit.policy.app.model.PolicyRequestSubObject;
import com.proofit.policy.domain.enums.RiskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static com.proofit.policy.fixtures.TestFixtures.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PolicyControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void calculatePolicy() throws Exception {
        List<PolicyRequestSubObject> subObjects = List.of(
                subObjectRequest(BigDecimal.valueOf(100), RiskType.FIRE),
                subObjectRequest(BigDecimal.valueOf(8), RiskType.THEFT)
        );

        PolicyRequestObject policyObject = policyObjectRequest(subObjects);
        PolicyRequestModel policyModel = policyRequest(List.of(policyObject));

        String data = objectMapper.writeValueAsString(policyModel);

        mvc.perform(post("/policies/calculate-premium")
                .content(data).contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.premium").value("2.28"))
                .andReturn();
    }

    @Test
    public void calculatePolicy_inputValidationFails() throws Exception {
        List<PolicyRequestSubObject> subObjects = List.of(
                subObjectRequest(BigDecimal.valueOf(100), RiskType.FIRE),
                subObjectRequest(BigDecimal.valueOf(8), RiskType.THEFT)
        );

        PolicyRequestObject policyObject = policyObjectRequest(subObjects);
        PolicyRequestModel policyModel = policyRequest(List.of(policyObject));
        policyModel.setNumber(null);

        String data = objectMapper.writeValueAsString(policyModel);

        mvc.perform(post("/policies/calculate-premium")
                .content(data).contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}