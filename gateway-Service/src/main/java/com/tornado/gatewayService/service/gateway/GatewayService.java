package com.tornado.gatewayService.service.gateway;

import java.util.List;

import com.tornado.gatewayService.model.Gateway;

public interface GatewayService {

	Gateway save(Gateway gateway);
	
	List<Gateway> findAll();

	Gateway findById(Long id);

}
