package com.snmi.dto;

import com.snmi.enums.Action;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "The class must be used as device to gateway association")
public class DeviceAssociationDTO {

    @NotNull(message = "Device IDs must not be null")
    @Size(min = 1, max = 10, message = "Device IDs size must contains between 1 and 10 unique ids")
    @ApiModelProperty(notes = "Device unique identifier which will be added/removed from gateway", example = "[8, 13]",
            required = true, accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private Set<Integer> deviceIds = new HashSet<>();

    @NotNull(message = "Action must not be null")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "What should happen to the device. Add it or remove it from gateway", example = "ADD",
            required = true, accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private Action action;

}
