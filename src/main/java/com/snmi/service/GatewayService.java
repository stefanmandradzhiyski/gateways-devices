package com.snmi.service;

import com.snmi.component.DeviceComponent;
import com.snmi.component.GatewayComponent;
import com.snmi.domain.Device;
import com.snmi.domain.Gateway;
import com.snmi.dto.FullGatewayDTO;
import com.snmi.dto.GatewayDTO;
import com.snmi.dto.DeviceAssociationDTO;
import com.snmi.enums.Action;
import com.snmi.mapper.GatewayMapper;
import com.snmi.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GatewayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayService.class);

    private final GatewayRepository gatewayRepository;
    private final GatewayComponent gatewayComponent;
    private final DeviceComponent deviceComponent;
    private final GatewayMapper gatewayMapper;

    @Transactional
    public FullGatewayDTO createGateway(GatewayDTO gatewayDTO) {
        LOGGER.info("Creating a new gateway");
        gatewayComponent.checkForUniqueSerialNumber(gatewayDTO.getSerialNumber());
        Gateway gateway = gatewayRepository.save(gatewayMapper.toGateway(gatewayDTO));
        LOGGER.info(String.format("The gateway with id=%s has been created successfully", gateway.getId()));

        return gatewayMapper.toFullGatewayDTO(gateway);
    }

    @Transactional(readOnly = true)
    public FullGatewayDTO getGateway(Integer gatewayId) {
        LOGGER.info("Retrieving a specific gateway");
        Gateway existingGateway = gatewayComponent.findByIdOrThrowException(gatewayId);
        LOGGER.info(String.format("The gateway with id=%s has been retrieved successfully", existingGateway.getId()));

        return gatewayMapper.toFullGatewayDTO(existingGateway);
    }

    @Transactional(readOnly = true)
    public List<FullGatewayDTO> getAllGateways() {
        LOGGER.info("Retrieving all gateways");

        return gatewayRepository.findAll().stream()
                .map(gatewayMapper::toFullGatewayDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FullGatewayDTO updateGateway(Integer gatewayId, GatewayDTO gatewayDTO) {
        LOGGER.info("Updating a specific gateway");
        Gateway existingGateway = gatewayComponent.findByIdOrThrowException(gatewayId);
        gatewayComponent.checkForUniqueSerialNumber(gatewayDTO.getSerialNumber(), existingGateway.getSerialNumber());
        existingGateway.updateGateway(gatewayDTO.getName(), gatewayDTO.getIpAddress(), gatewayDTO.getSerialNumber());
        Gateway updatedGateway = gatewayRepository.save(existingGateway);
        LOGGER.info(String.format("The gateway with id=%s has been updated successfully", updatedGateway.getId()));

        return gatewayMapper.toFullGatewayDTO(updatedGateway);
    }

    @Transactional
    public FullGatewayDTO associateDevicesToGateway(Integer gatewayId, DeviceAssociationDTO deviceAssociationDTO) {
        LOGGER.info("Associating devices to the gateway");
        Gateway existingGateway = gatewayComponent.findByIdOrThrowException(gatewayId);
        List<Device> existingDevices = deviceComponent.findAllByIdIn(deviceAssociationDTO.getDeviceIds());

        if (Action.ADD == deviceAssociationDTO.getAction()) {
            existingGateway.addDevices(existingDevices);
            LOGGER.info(String.format("All devices have been added to gateway with id=%s", existingGateway.getId()));
        } else if (Action.REMOVE == deviceAssociationDTO.getAction()) {
            existingGateway.removeDevices(existingDevices);
            LOGGER.info(String.format("All devices have been removed from gateway with id=%s", existingGateway.getId()));
        }

        return gatewayMapper.toFullGatewayDTO(gatewayRepository.save(existingGateway));
    }

    @Transactional
    public void deleteGateway(Integer gatewayId) {
        LOGGER.info(String.format("Deleting gateway with id=%s", gatewayId));
        gatewayRepository.delete(gatewayComponent.findByIdOrThrowException(gatewayId));
        LOGGER.info(String.format("The gateway with id=%s has been deleted successfully", gatewayId));
    }


}
