package com.snmi.web.rest;

import com.snmi.dto.DeviceDTO;
import com.snmi.dto.FullDeviceDTO;
import com.snmi.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/devices")
@Api("Devices API")
public class DeviceResource {

    private final DeviceService deviceService;

    @ApiOperation(value = "Create a new device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been created successfully", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Its properties aren't valid", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @PostMapping
    public ResponseEntity<FullDeviceDTO> createDevice(@Valid @RequestBody DeviceDTO deviceDTO) {
        return new ResponseEntity<>(deviceService.createDevice(deviceDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get a specific device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been retrieved successfully", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "It doesn't exist", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @GetMapping("/{deviceId}")
    public ResponseEntity<FullDeviceDTO> getDevice(@PathVariable("deviceId") @NotNull Integer deviceId) {
        return new ResponseEntity<>(deviceService.getDevice(deviceId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all devices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All devices have been retrieved successfully", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @GetMapping
    public ResponseEntity<List<FullDeviceDTO>> getAllDevices() {
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }

    @ApiOperation(value = "Updated a specific device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been updated successfully", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Its properties aren't valid", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "It doesn't exist", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @PutMapping("/{deviceId}")
    public ResponseEntity<FullDeviceDTO> updateDevice(@PathVariable("deviceId") @NotNull Integer deviceId,
                                                      @Valid @RequestBody DeviceDTO deviceDTO) {
        return new ResponseEntity<>(deviceService.updateDevice(deviceId, deviceDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific device")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been deleted successfully", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "It doesn't exist", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("deviceId") @NotNull Integer deviceId) {
        deviceService.deleteDevice(deviceId);
        return ResponseEntity.ok().build();
    }
}
