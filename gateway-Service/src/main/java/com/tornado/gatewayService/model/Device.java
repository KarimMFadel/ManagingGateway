package com.tornado.gatewayService.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEVICES")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEVICE_ID")
	private Long id;

	@Column(name = "UNIQUE_NUMBER")
	private String uniqueNumber;

	@Column(name = "VENDOR")
	private String vendor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private DeviceStatus status;

	@ManyToOne
	@JoinColumn(name = "GATEWAY_ID")
	private Gateway gateway;
	
	
	@PrePersist
    protected void onCreate() {
		setCreatedOn(new Date());
	}
}
