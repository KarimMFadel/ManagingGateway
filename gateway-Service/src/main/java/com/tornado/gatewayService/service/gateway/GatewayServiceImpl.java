package com.tornado.gatewayService.service.gateway;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tornado.gatewayService.general.exception.GatewayException;
import com.tornado.gatewayService.general.util.IPv4ValidatorRegex;
import com.tornado.gatewayService.model.Gateway;
import com.tornado.gatewayService.repository.GatewayRepository;

@Service
public class GatewayServiceImpl implements GatewayService {
	
	@Autowired
	GatewayRepository gatewayRepository;

	@Transactional
	@Override
	public Gateway save(Gateway gateway) {
		validate(gateway);
		return gatewayRepository.save(gateway);
	}
	

	@Override
	public List<Gateway> findAll() {
		return gatewayRepository.findAll();
	}
	

	@Override
	public Gateway findById(Long id) {
		if(Objects.isNull(id) || Objects.equals(id, 0L))
			throw new GatewayException("invalid GatewayId parameter");
		if( !gatewayRepository.existsById(id) )
			throw new GatewayException("There is no gateway with id : " + id);
		return gatewayRepository.getById(id);
	}

	
	 private void validate(Gateway gateway) {
		if (!IPv4ValidatorRegex.isValid(gateway.getIpAddress()))
			throw new GatewayException("invalid IP4-address parameter");
		if (gatewayRepository.checkUniquenessOfSerialNumber(gateway.getUniqueSerialNumber()))
			throw new GatewayException("SerialNumber must be unique, there is a gateway with the same SerialNumber");
	 }

}
