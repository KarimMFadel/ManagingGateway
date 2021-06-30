package com.tornado.gatewayService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tornado.gatewayService.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

	@Query("SELECT * from Device a WHERE a.gateway.id = ?1 ")
	List<Device> findByGatewayId(Long uniqueSerialNumber);
}
