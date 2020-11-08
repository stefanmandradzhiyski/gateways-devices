package com.snmi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "The class represent a more detailed device")
public class FullDeviceDTO extends DeviceDTO {

    @ApiModelProperty(notes = "Device's unique identifier", example = "1",
            accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Integer id;

    @ApiModelProperty(notes = "The gateway id to which the device is associated", example = "1",
            accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Integer gatewayId;

}
