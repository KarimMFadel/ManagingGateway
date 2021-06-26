package com.tornado.gatewayService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueSerialNumber() {
		return uniqueSerialNumber;
	}

	public void setUniqueSerialNumber(String uniqueSerialNumber) {
		this.uniqueSerialNumber = uniqueSerialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
