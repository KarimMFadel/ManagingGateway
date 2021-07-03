package com.tornado.gatewayService.service.device;

import java.util.List;

import com.tornado.gatewayService.model.Device;

public interface DeviceService {

	Device save(Device device, Long gatewayId);

	
	List<Device> findAll();

	
	Device findById(Long id);
	
	
	void remove(Long id);
	
	
	List<Device> findByGatewayId(Long GatewayId);
}
