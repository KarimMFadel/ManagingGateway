package com.tornado.gatewayService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GATEWAYS")
// fix ===> No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor
// tell your jsonplug component to ignore the attributes in the array corresponding to value when converting your proxy object into a json object
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})

public class Gateway {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "UNIQUE_SERIAL_NUMBER")
	private String uniqueSerialNumber;

	@Column(name = "GATEWAY_NAME")
	private String name;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	
}
