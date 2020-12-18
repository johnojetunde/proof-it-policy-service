package com.proofit.policy.domain.models;

import com.proofit.policy.domain.enums.PolicyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Policy {
    private String number;
    private PolicyStatus status;
    private List<PolicyObject> objects;
}
