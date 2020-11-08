package com.snmi.domain;

import com.snmi.exception.ConflictException;
import com.snmi.validation.ISSN;
import com.snmi.validation.IpAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gateway")
public class Gateway extends BaseEntity {

    private static final String MAX_DEVICE_SIZE = "One gateway can contain max 10 devices. " +
            "Current assigned devices count is %d so there are %d free slots.";
    private static final String ASSIGNED_DEVICE = "Device with id=%s is already assigned to gateway with id=%s. " +
            "To proceed further please remove this device from the gateway first!";
    private static final String NOT_ASSIGNED_DEVICE = "Device with id=%s isn't assign to the gateway";

    @NotNull(message = "Serial number must not be null")
    @ISSN(message = "Serial number length must be 8 and must contain only digits")
    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @NotNull(message = "Name must not be null")
    @Size(min = 3, max = 25, message = "Name length must be between 3 and 25")
    @Column(name = "name")
    private String name;

    @NotNull(message = "IPv4 address must not be null")
    @IpAddress(message = "Invalid IPv4 address")
    @Column(name = "ip_address")
    private String ipAddress;

    @OneToMany(mappedBy = "gateway")
    @BatchSize(size = 10)
    private Set<Device> devices = new HashSet<>();

    public void updateGateway(
            @NotNull @Size(min = 3, max = 25) String name,
            @NotNull @IpAddress String ipAddress,
            @NotNull @ISSN String serialNumber
    ) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.ipAddress = ipAddress;
    }

    public void addDevices(List<Device> devices) {
        if (this.devices.size() + devices.size() <= 10) {
            for (Device device : devices) {
                if (device.getGateway() == null) {
                    if (this.devices.add(device)) {
                        device.setGateway(this);
                    }
                } else {
                    throw new ConflictException(String.format(ASSIGNED_DEVICE, device.getId(), device.getGateway().getId()));
                }
            }
        } else {
            throw new ConflictException(String.format(MAX_DEVICE_SIZE, this.devices.size(), 10 - this.devices.size()));
        }
    }

    public void removeDevices(List<Device> devices) {
        for (Device device : devices) {
            if (this.devices.contains(device)) {
                if (this.devices.remove(device)) {
                    device.setGateway(null);
                }
            } else {
                throw new ConflictException(String.format(NOT_ASSIGNED_DEVICE, device.getId()));
            }
        }
    }
}
