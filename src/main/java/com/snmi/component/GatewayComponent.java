package com.snmi.component;

import com.snmi.domain.Gateway;
import com.snmi.exception.ConflictException;
import com.snmi.exception.NotFoundException;
import com.snmi.repository.GatewayRepository;
import org.springframework.stereotype.Component;

@Component
public class GatewayComponent {

    private static final String GATEWAY_NOT_FOUND = "Gateway with id=%s isn't found";
    private static final String SERIAL_NUMBER_EXISTS = "Serial number (%s) already exists";

    private final GatewayRepository gatewayRepository;

    public GatewayComponent(GatewayRepository gatewayRepository) {
        this.gatewayRepository = gatewayRepository;
    }

    public Gateway findByIdOrThrowException(Integer gatewayId) {
        return gatewayRepository.findById(gatewayId)
                .orElseThrow(() -> new NotFoundException(String.format(GATEWAY_NOT_FOUND, gatewayId)));
    }

    public void checkForUniqueSerialNumber(String newGatewaySerialNumber) {
        checkForUniqueSerialNumber(newGatewaySerialNumber, null);
    }

    public void checkForUniqueSerialNumber(String newGatewaySerialNumber, String existingGatewaySerialNumber) {
        boolean isSerialNumberUnique = !newGatewaySerialNumber.equals(existingGatewaySerialNumber)
                && gatewayRepository.existsBySerialNumber(newGatewaySerialNumber);

        if (isSerialNumberUnique) {
            throw new ConflictException(String.format(SERIAL_NUMBER_EXISTS, newGatewaySerialNumber));
        }
    }

}
