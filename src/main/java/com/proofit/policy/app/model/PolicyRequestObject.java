package com.proofit.policy.app.model;

import com.proofit.policy.domain.models.PolicyObject;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.proofit.policy.domain.util.FunctionUtil.emptyIfNullStream;
import static java.util.stream.Collectors.toList;

@Accessors(chain = true)
@Data
public class PolicyRequestObject {

    @NotBlank(message = "PolicyRequest name is required")
    private String name;
    @Valid
    private List<PolicyRequestSubObject> subObjects;

    public PolicyObject toModel() {
        var subObjectModels = emptyIfNullStream(subObjects)
                .map(PolicyRequestSubObject::toModel)
                .collect(toList());

        return new PolicyObject()
                .setName(getName())
                .setSubObjects(subObjectModels);
    }
}
