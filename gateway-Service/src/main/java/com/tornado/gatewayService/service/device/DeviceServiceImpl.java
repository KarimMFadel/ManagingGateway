package com.tornado.gatewayService.service.device;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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

	
	@Transactional
	@Override
	public Device save(Device device, Long gatewayId) {
		validateDevice(device, gatewayId);
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
		if( !deviceRepository.existsById(id))
			throw new GatewayException("There is no device with id : " + id);
		return deviceRepository.getById(id);
	}
	
	
	@Transactional
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
	
	
	private void validateDevice(Device device, Long gatewayId) {
		if (deviceRepository.checkUniquenessOfUniqueNumber(device.getUniqueNumber()))
			throw new GatewayException("UniqueNumber must be unique, there is a device with the same unique number");
		if (deviceRepository.getNumberOfDevicesPerEachGateway(gatewayId) > 10)
			throw new GatewayException("Couldn't add more than 10 device with the same gateway : " + gatewayId);
	}
	
}
