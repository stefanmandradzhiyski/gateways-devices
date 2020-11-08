package com.snmi.mapper;

import com.snmi.domain.Gateway;
import com.snmi.dto.FullGatewayDTO;
import com.snmi.dto.GatewayDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = DeviceMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GatewayMapper {

    @Mappings({
            @Mapping(target = "serialNumber", source = "gatewayDTO.serialNumber"),
            @Mapping(target = "name", source = "gatewayDTO.name"),
            @Mapping(target = "ipAddress", source = "gatewayDTO.ipAddress")
    })
    @BeanMapping(ignoreByDefault = true)
    Gateway toGateway(GatewayDTO gatewayDTO);

    @Mappings({
            @Mapping(target = "id", source = "gateway.id"),
            @Mapping(target = "serialNumber", source = "gateway.serialNumber"),
            @Mapping(target = "name", source = "gateway.name"),
            @Mapping(target = "ipAddress", source = "gateway.ipAddress"),
            @Mapping(target = "devices", source = "gateway.devices")
    })
    FullGatewayDTO toFullGatewayDTO(Gateway gateway);

}
