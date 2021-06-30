package com.tornado.gatewayService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GATEWAYS")
public class Gateway {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "UNIQUE_SERIAL_NUMBER")
	private String uniqueSerialNumber;

	@Column(name = "NAME")
	private String name;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	
}
