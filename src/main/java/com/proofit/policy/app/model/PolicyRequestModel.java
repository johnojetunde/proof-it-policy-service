package com.proofit.policy.app.model;

import com.proofit.policy.domain.enums.PolicyStatus;
import com.proofit.policy.domain.models.Policy;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.proofit.policy.domain.util.FunctionUtil.emptyIfNullStream;
import static java.util.stream.Collectors.toList;

@Data
@Accessors(chain = true)
public class PolicyRequestModel {
    @NotBlank(message = "Policy number is required")
    private String number;
    @NotNull
    private PolicyStatus status;
    @NotEmpty
    @Valid
    private List<PolicyRequestObject> objects;

    public Policy toModel() {
        var objectModels = emptyIfNullStream(objects)
                .map(PolicyRequestObject::toModel)
                .collect(toList());

        return new Policy()
                .setNumber(getNumber())
                .setStatus(getStatus())
                .setObjects(objectModels);
    }
}
