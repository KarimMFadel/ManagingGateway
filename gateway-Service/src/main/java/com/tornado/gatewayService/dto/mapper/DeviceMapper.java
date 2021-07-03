package com.tornado.gatewayService.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.tornado.gatewayService.dto.DeviceDto;
import com.tornado.gatewayService.model.Device;

public class DeviceMapper {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	
	public static DeviceDto convertToDto(Device device) {
		DeviceDto deviceDto = modelMapper.map(device, DeviceDto.class);
		deviceDto.setGatewayId(device.getGateway().getId());
		return deviceDto;
	}
	
	
	public static List<DeviceDto> convertToDto(List<Device> devices) {
		return devices.stream().map(device -> convertToDto(device) ).collect(Collectors.toList());
	}
	
	
	public static Device convertFromDto(DeviceDto deviceDto) {
		Device device = modelMapper.map(deviceDto, Device.class);
		return device;
	}
	
}
