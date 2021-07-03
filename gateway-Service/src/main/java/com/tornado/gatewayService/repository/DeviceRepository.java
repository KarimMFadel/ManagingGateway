package com.tornado.gatewayService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tornado.gatewayService.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

	@Query("SELECT a FROM Device a WHERE a.gateway.id = ?1 ")
	List<Device> findByGatewayId(Long uniqueSerialNumber);
	
	
	@Query("SELECT case when (count(a) > 0) then true else false end FROM Device a WHERE a.uniqueNumber = ?1 ")
	boolean checkUniquenessOfUniqueNumber(String uniqueNumber);
	
	
	@Query("SELECT count(a) FROM Device a WHERE a.gateway.id = ?1 ")
	int getNumberOfDevicesPerEachGateway(Long gatewayId);
}
