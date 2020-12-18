package com.proofit.policy.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Accessors(chain = true)
@Data
public class PolicyObject {
    @NotBlank(message = "PolicyRequest name is required")
    private String name;
    @Valid
    private List<PolicySubObject> subObjects;
}
