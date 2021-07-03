package com.tornado.gatewayService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayDTO {

	private Long id;

	private String uniqueSerialNumber;

	private String name;

	private String ipAddress;
	
	private List<DeviceDto> deviceDtos;

}
