package com.tornado.gatewayService.dto;

import java.util.Date;

import com.tornado.gatewayService.model.DeviceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

	private Long id;

	private String uniqueNumber;

	private String vendor;

	private Date createdOn;

	private DeviceStatus status;

	private Long gatewayId;

}
