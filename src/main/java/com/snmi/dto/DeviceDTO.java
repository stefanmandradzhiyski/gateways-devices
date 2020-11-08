package com.snmi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.snmi.enums.Status;
import com.snmi.util.LocalDateHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "The class represent a device")
public class DeviceDTO {

    @NotNull(message = "Vendor must not be null")
    @Size(min = 3, max = 50, message = "Vendor length must be between 3 and 50")
    @ApiModelProperty(notes = "Device's vendor", example = "BOSCH", required = true,
            accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private String vendor;

    @NotNull(message = "Date created must not be null")
    @JsonDeserialize(using = LocalDateHandler.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(notes = "The date on which the device was created", example = "2019-07-11", required = true,
            accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private LocalDate dateCreated;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "What is the current device's status", example = "ONLINE", required = true,
            accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private Status status;

}
