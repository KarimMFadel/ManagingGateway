package com.tornado.gatewayService.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.tornado.gatewayService.dto.DeviceDto;
import com.tornado.gatewayService.dto.GatewayDTO;
import com.tornado.gatewayService.model.Device;
import com.tornado.gatewayService.model.Gateway;

public class GatewayMapper {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	
	public static GatewayDTO convertToDto(Gateway gateway) {
		return modelMapper.map(gateway, GatewayDTO.class);
	}
	
	
	public static List<GatewayDTO> convertToDto(List<Gateway> gateways) {
		return gateways.stream().map(gateway ->  convertToDto(gateway) ).collect(Collectors.toList());
	}
	
	
	public static Gateway convertFromDto(GatewayDTO gatewayDTO) {
		Gateway device = modelMapper.map(gatewayDTO, Gateway.class);
		return device;
	}
	
}
