package com.snmi.dto;

import com.snmi.validation.ISSN;
import com.snmi.validation.IpAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "The class represent a gateway")
public class GatewayDTO {

    @NotNull(message = "Serial number must not be null")
    @ISSN(message = "Serial number length must be 8 and must contain only digits")
    @ApiModelProperty(notes = "Gateway's internation standard serial number", example = "14567284", required = true,
            accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private String serialNumber;

    @NotNull(message = "Name must not be null")
    @Size(min = 3, max = 25, message = "Name length must be between 3 and 25")
    @ApiModelProperty(notes = "Gateway's name", example = "CISCO", required = true,
            accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private String name;

    @NotNull(message = "IPv4 address must not be null")
    @IpAddress(message = "Invalid IPv4 address")
    @ApiModelProperty(notes = "Gateway's IPv4 address", example = "127.1.1.0", required = true,
            accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private String ipAddress;

}
