package com.snmi.mapper;

import com.snmi.domain.Device;
import com.snmi.domain.Gateway;
import com.snmi.dto.DeviceDTO;
import com.snmi.dto.FullDeviceDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DeviceMapper {

    @Mappings({
            @Mapping(target = "vendor", source = "deviceDTO.vendor"),
            @Mapping(target = "dateCreated", source = "deviceDTO.dateCreated"),
            @Mapping(target = "status", source = "deviceDTO.status")
    })
    @BeanMapping(ignoreByDefault = true)
    Device toDevice(DeviceDTO deviceDTO);

    @Mappings({
            @Mapping(target = "id", source = "device.id"),
            @Mapping(target = "vendor", source = "device.vendor"),
            @Mapping(target = "dateCreated", source = "device.dateCreated"),
            @Mapping(target = "status", source = "device.status"),
            @Mapping(target = "gatewayId", source = "device.gateway", qualifiedByName = "getGatewayId")
    })
    FullDeviceDTO toFullDeviceDTO(Device device);

    @Named("getGatewayId")
    default Integer getGatewayId(Gateway gateway) {
        if (gateway != null) {
            return gateway.getId();
        }

        return null;
    }
}
