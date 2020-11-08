package com.snmi.repository;

import com.snmi.domain.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Integer> {

    boolean existsBySerialNumber(String serialNumber);

}
