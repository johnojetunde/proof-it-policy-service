package com.proofit.policy.fixtures;

import com.proofit.policy.app.model.PolicyRequestModel;
import com.proofit.policy.app.model.PolicyRequestObject;
import com.proofit.policy.app.model.PolicyRequestSubObject;
import com.proofit.policy.domain.enums.PolicyStatus;
import com.proofit.policy.domain.enums.RiskType;
import com.proofit.policy.domain.models.Policy;
import com.proofit.policy.domain.models.PolicyObject;
import com.proofit.policy.domain.models.PolicySubObject;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class TestFixtures {
    public static PolicySubObject subObject(BigDecimal amountInsured, RiskType type) {
        return new PolicySubObject()
                .setSumInsured(amountInsured)
                .setType(type)
                .setName("SubObject");
    }

    public static PolicyObject policyObject(List<PolicySubObject> subObjects) {
        return new PolicyObject()
                .setName("Object")
                .setSubObjects(subObjects);
    }

    public static Policy policy(List<PolicyObject> objects) {
        return new Policy()
                .setNumber("LV-20-10000000")
                .setObjects(objects)
                .setStatus(PolicyStatus.APPROVED);
    }

    public static PolicyRequestSubObject subObjectRequest(BigDecimal amountInsured, RiskType type) {
        return new PolicyRequestSubObject()
                .setSumInsured(amountInsured)
                .setType(type)
                .setName("SubObject");
    }

    public static PolicyRequestObject policyObjectRequest(List<PolicyRequestSubObject> subObjects) {
        return new PolicyRequestObject()
                .setName("Object")
                .setSubObjects(subObjects);
    }

    public static PolicyRequestModel policyRequest(List<PolicyRequestObject> objects) {
        return new PolicyRequestModel()
                .setNumber("LV-20-10000000")
                .setObjects(objects)
                .setStatus(PolicyStatus.APPROVED);
    }
}
