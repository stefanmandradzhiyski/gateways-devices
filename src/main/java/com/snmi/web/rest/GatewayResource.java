package com.snmi.web.rest;

import com.snmi.dto.FullGatewayDTO;
import com.snmi.dto.GatewayDTO;
import com.snmi.dto.DeviceAssociationDTO;
import com.snmi.service.GatewayService;
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
@RequestMapping("api/v1/gateways")
@Api("Gateways API")
public class GatewayResource {

    private final GatewayService gatewayService;

    @ApiOperation(value = "Create a new gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been created successfully", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Its properties aren't valid", response = ResponseEntity.class),
            @ApiResponse(code = 409, message = "Serial number isn't unique", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @PostMapping
    public ResponseEntity<FullGatewayDTO> createGateway(@Valid @RequestBody GatewayDTO gatewayDTO) {
        return new ResponseEntity<>(gatewayService.createGateway(gatewayDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get a specific gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been retrieved successfully", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "It doesn't exist", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @GetMapping("/{gatewayId}")
    public ResponseEntity<FullGatewayDTO> getGateway(@PathVariable("gatewayId") @NotNull Integer gatewayId) {
        return new ResponseEntity<>(gatewayService.getGateway(gatewayId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all gateways")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All gateways have been retrieved successfully", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @GetMapping
    public ResponseEntity<List<FullGatewayDTO>> getAllGateways() {
        return new ResponseEntity<>(gatewayService.getAllGateways(), HttpStatus.OK);
    }

    @ApiOperation(value = "Updated a specific gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been updated successfully", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Its properties aren't valid", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "It doesn't exist", response = ResponseEntity.class),
            @ApiResponse(code = 409, message = "Serial number isn't unique", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @PutMapping("/{gatewayId}")
    public ResponseEntity<FullGatewayDTO> updateGateway(@PathVariable("gatewayId") @NotNull Integer gatewayId,
                                                        @Valid @RequestBody GatewayDTO gatewayDTO) {
        return new ResponseEntity<>(gatewayService.updateGateway(gatewayId, gatewayDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Add or remove devices from gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "They have been associated successfully", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Properties aren't valid", response = ResponseEntity.class),
            @ApiResponse(code = 409, message = "Devices list several potential issues", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @PutMapping("/{gatewayId}/devices-association")
    public ResponseEntity<FullGatewayDTO> associateDevicesToGateway(@PathVariable("gatewayId") @NotNull Integer gatewayId,
                                                                    @Valid @RequestBody DeviceAssociationDTO deviceAssociationDTO) {
        return new ResponseEntity<>(gatewayService.associateDevicesToGateway(gatewayId, deviceAssociationDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "It has been deleted successfully", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "It doesn't exist", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Something wrong with our service", response = ResponseEntity.class)
    })
    @DeleteMapping("/{gatewayId}")
    public ResponseEntity<Void> deleteGateway(@PathVariable("gatewayId") @NotNull Integer gatewayId) {
        gatewayService.deleteGateway(gatewayId);
        return ResponseEntity.ok().build();
    }
}
