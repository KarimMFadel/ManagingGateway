package com.tornado.gatewayService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tornado.gatewayService.general.exception.GatewayException;
import com.tornado.gatewayService.model.Device;
import com.tornado.gatewayService.model.DeviceStatus;
import com.tornado.gatewayService.model.Gateway;
import com.tornado.gatewayService.repository.DeviceRepository;
import com.tornado.gatewayService.repository.GatewayRepository;
import com.tornado.gatewayService.service.device.DeviceService;
import com.tornado.gatewayService.service.gateway.GatewayService;

@SpringBootTest
class GatewayServiceApplicationTests {

	@Autowired
	private GatewayService gatewayService;

	@Autowired
	DeviceService deviceService;

	@MockBean
	private GatewayRepository gatewayRepository;

	@MockBean
	DeviceRepository deviceRepository;

	@Test
	void contextLoads() {
	}

	
	@Test
	void addGatewayWithValidInput() {
		gatewayService.save(defineGateways().get(0));
		assertNotNull(gatewayService.findById(1L));
	}

	
	@Test
	void addGatewayWithInvalidInput() {

		Exception exception = assertThrows(GatewayException.class, () -> {
			Gateway gateway = defineGateways().get(0);
			gateway.setIpAddress("10.0");
			gatewayService.save(gateway);
		});

		String expectedMessage = "invalid IP4-address parameter";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	
	@Test
	void returnListOfGateways() {
		when(gatewayRepository.findAll()).thenReturn(defineGateways());
		assertThat(gatewayService.findAll()).size().isGreaterThan(0);
	}

	
	@Test
	void returnListOfdevices() {
		when(deviceRepository.findByGatewayId(1L)).thenReturn(defineDevices());
		assertThat(deviceService.findByGatewayId(1L)).size().isGreaterThan(0);
	}

	
	@Test
	void addDevice() {
		Gateway gateway = defineDevices().get(0).getGateway();
		gatewayService.save(gateway);
		deviceService.save(defineDevices().get(0), gateway.getId());
		assertNotNull(gatewayService.findById(1L));
	}
	

	@Test
	void removeDevice() {
		when(deviceService.findById(1L)).thenReturn(defineDevices().get(0));
		
		deviceService.remove(1L);
		
		Exception exception = assertThrows(GatewayException.class, () -> {
			deviceService.findById(1L);
		});

		String expectedMessage = "invalid deviceId parameter";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
		
		verify(deviceRepository).deleteById(1L);
	}

	
	private List<Gateway> defineGateways() {
		List<Gateway> gateways = new ArrayList<>();

		Gateway gateway = new Gateway();
		gateway.setId(1L);
		gateway.setIpAddress("10.0.1.0");
		gateway.setName("Acer");
		gateway.setUniqueSerialNumber("DFGF111111FD11");

		Gateway gateway2 = new Gateway();
		gateway2.setId(2L);
		gateway2.setIpAddress("10.0.3.0");
		gateway2.setName("Acer2");
		gateway2.setUniqueSerialNumber("EEE2222FD11");

		gateways.add(gateway);
		gateways.add(gateway2);

		return gateways;
	}

	
	private List<Device> defineDevices() {
		List<Device> devices = new ArrayList<>();

		Device device1 = new Device();
		device1.setCreatedOn(new Date());
		device1.setGateway(defineGateways().get(0));
		device1.setId(1L);
		device1.setStatus(DeviceStatus.OFFLINE);
		device1.setUniqueNumber("124323453");
		device1.setVendor("Ericsson");

		Device device2 = new Device();
		device2.setCreatedOn(new Date());
		device2.setGateway(defineGateways().get(0));
		device2.setId(2L);
		device2.setStatus(DeviceStatus.OFFLINE);
		device2.setUniqueNumber("124323453");
		device2.setVendor("Ericsson");

		devices.add(device1);
		devices.add(device2);

		return devices;
	}

}
