package com.tornado.gatewayService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tornado.gatewayService.dto.DeviceDto;
import com.tornado.gatewayService.dto.mapper.DeviceMapper;
import com.tornado.gatewayService.model.Device;
import com.tornado.gatewayService.service.device.DeviceService;

@RestController
@RequestMapping("device")
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	
	@PostMapping(produces = "application/json")
    public Device addDevice(@RequestBody DeviceDto deviceDto) {
        return deviceService.save(DeviceMapper.convertFromDto(deviceDto), deviceDto.getGatewayId());
    }
	
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
    public void removeDevice(@PathVariable Long id) {
        deviceService.remove(id);
    }
	
	
	@GetMapping(path = "/gateway/{gatewayId}", produces = "application/json")
    public List<DeviceDto> getDeviceByGatewayId(@PathVariable Long gatewayId) {
        return DeviceMapper.convertToDto(deviceService.findByGatewayId(gatewayId));
    }
	
	
	@GetMapping(path = "/{id}", produces = "application/json")
    public DeviceDto getDevice(@PathVariable Long id) {
        return DeviceMapper.convertToDto(deviceService.findById(id));
    }
	
	
	@GetMapping(path = "/", produces = "application/json")
    public List<DeviceDto> getAllDevice() {
        return DeviceMapper.convertToDto( deviceService.findAll() );
    }
}
