package com.tornado.gatewayService.service.device;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tornado.gatewayService.general.exception.GatewayException;
import com.tornado.gatewayService.model.Device;
import com.tornado.gatewayService.repository.DeviceRepository;
import com.tornado.gatewayService.service.gateway.GatewayService;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	GatewayService GatewayService;

	
	@Override
	public Device save(Device device, Long gatewayId) {
		device.setGateway(GatewayService.findById(gatewayId));
		return deviceRepository.save(device);
	}

	
	@Override
	public List<Device> findAll() {
		return deviceRepository.findAll();
	}

	
	@Override
	public Device findById(Long id) {
		if(Objects.isNull(id) || Objects.equals(id, 0L))
			throw new GatewayException("invalid deviceId parameter");
		return deviceRepository.getById(id);
	}
	
	
	@Override
	public void remove(Long id) {
		this.findById(id);
		deviceRepository.deleteById(id);
	}
	
	
	@Override
	public List<Device> findByGatewayId(Long GatewayId) {
		GatewayService.findById(GatewayId);
		return deviceRepository.findByGatewayId(GatewayId);
	}
	
}
