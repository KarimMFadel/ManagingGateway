package com.tornado.gatewayService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tornado.gatewayService.model.Gateway;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {

	@Query("SELECT case when (count(a) > 0) then true else false end from Gateway a WHERE a.uniqueSerialNumber = ?1 ")
	boolean checkUniquenessOfSerialNumber(String uniqueSerialNumber);
}
