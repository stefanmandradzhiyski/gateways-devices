package com.snmi.component;

import com.snmi.domain.Device;
import com.snmi.exception.NotFoundException;
import com.snmi.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class DeviceComponent {

    private static final String DEVICE_NOT_FOUND = "Device with id=%s isn't found";

    private final DeviceRepository deviceRepository;

    public Device findByIdOrThrowException(Integer deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new NotFoundException(String.format(DEVICE_NOT_FOUND, deviceId)));
    }

    public List<Device> findAllByIdIn(Set<Integer> deviceIds) {
        return deviceRepository.findAllById(deviceIds);
    }
}
