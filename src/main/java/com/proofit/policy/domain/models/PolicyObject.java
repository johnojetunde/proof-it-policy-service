package com.proofit.policy.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class PolicyObject {
    private String name;
    private List<PolicySubObject> subObjects;
}
