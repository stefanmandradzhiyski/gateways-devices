package com.snmi.service;

import com.snmi.component.DeviceComponent;
import com.snmi.domain.Device;
import com.snmi.dto.DeviceDTO;
import com.snmi.dto.FullDeviceDTO;
import com.snmi.mapper.DeviceMapper;
import com.snmi.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    private final DeviceRepository deviceRepository;
    private final DeviceComponent deviceComponent;
    private final DeviceMapper deviceMapper;

    public DeviceService(DeviceRepository deviceRepository, DeviceComponent deviceComponent, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceComponent = deviceComponent;
        this.deviceMapper = deviceMapper;
    }

    @Transactional
    public FullDeviceDTO createDevice(DeviceDTO deviceDTO) {
        LOGGER.info("Creating a new device");
        Device device = deviceRepository.save(deviceMapper.toDevice(deviceDTO));
        LOGGER.info(String.format("The device with id=%s has been created successfully", device.getId()));

        return deviceMapper.toFullDeviceDTO(device);
    }

    @Transactional(readOnly = true)
    public FullDeviceDTO getDevice(Integer deviceId) {
        LOGGER.info("Retrieving a specific device");
        Device existingDevice = deviceComponent.findByIdOrThrowException(deviceId);
        LOGGER.info(String.format("The device with id=%s has been retrieved successfully", existingDevice.getId()));


        return deviceMapper.toFullDeviceDTO(existingDevice);
    }

    @Transactional(readOnly = true)
    public List<FullDeviceDTO> getAllDevices() {
        LOGGER.info("Retrieving all devices");

        return deviceRepository.findAll().stream()
                .map(deviceMapper::toFullDeviceDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FullDeviceDTO updateDevice(Integer deviceId, DeviceDTO deviceDTO) {
        LOGGER.info("Updating a specific device");
        Device existingDevice = deviceComponent.findByIdOrThrowException(deviceId);
        existingDevice.updateDevice(deviceDTO.getVendor(), deviceDTO.getDateCreated(), deviceDTO.getStatus());
        Device updatedDevice = deviceRepository.save(existingDevice);
        LOGGER.info(String.format("The device with id=%s has been updated successfully", updatedDevice.getId()));

        return deviceMapper.toFullDeviceDTO(updatedDevice);
    }

    @Transactional
    public void deleteDevice(Integer deviceId) {
        LOGGER.info(String.format("Deleting device with id=%s", deviceId));
        deviceRepository.delete(deviceComponent.findByIdOrThrowException(deviceId));
        LOGGER.info(String.format("The device with id=%s has been deleted successfully", deviceId));
    }

}
