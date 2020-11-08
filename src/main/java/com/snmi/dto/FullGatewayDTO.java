package com.snmi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "The class represent a more detailed gateway")
public class FullGatewayDTO extends GatewayDTO {

    @ApiModelProperty(notes = "Gateway's unique identifier", example = "1",
            accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Integer id;

    @ApiModelProperty(notes = "List of all devices it manages", example = "[]",
            accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Set<DeviceDTO> devices = new HashSet<>();

}
