package com.tornado.gatewayService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tornado.gatewayService.dto.GatewayDTO;
import com.tornado.gatewayService.dto.mapper.DeviceMapper;
import com.tornado.gatewayService.dto.mapper.GatewayMapper;
import com.tornado.gatewayService.model.Gateway;
import com.tornado.gatewayService.service.device.DeviceService;
import com.tornado.gatewayService.service.gateway.GatewayService;


@RestController
@RequestMapping("device")
public class GatewayController {

	@Autowired
	GatewayService gatewayService;
	
	@Autowired
	DeviceService deviceService;
	
	@PostMapping(produces = "application/json")
    public Gateway getGateway(@RequestBody GatewayDTO deviceDto) {
        return gatewayService.save(GatewayMapper.convertFromDto(deviceDto));
    }
	
	
	@GetMapping(path = "/{id}", produces = "application/json")
    public GatewayDTO getGateway(@PathVariable Long id) {
        return GatewayMapper.convertToDto(gatewayService.findById(id));
    }
	
	
	@GetMapping(path = "/", produces = "application/json")
    public List<GatewayDTO> getAllGateways() {
        return GatewayMapper.convertToDto( gatewayService.findAll() );
    }
	
	
	@GetMapping(path = "/allWithDevices/", produces = "application/json")
    public List<GatewayDTO> getAllGatewaysWithDevices() {
		List<GatewayDTO> gatewayDTOs = new ArrayList<>();
		gatewayService.findAll().forEach(gateway -> {
			GatewayDTO gatewayDTO = GatewayMapper.convertToDto(gateway);
			gatewayDTO.setDeviceDtos(DeviceMapper.convertToDto(deviceService.findByGatewayId(gateway.getId()) ) );
			gatewayDTOs.add(gatewayDTO);
		});
        return GatewayMapper.convertToDto( gatewayService.findAll() );
    }
	
}
