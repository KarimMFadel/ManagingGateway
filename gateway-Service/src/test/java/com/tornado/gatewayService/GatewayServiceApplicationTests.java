package com.tornado.gatewayService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
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

	private static final Long GATEWAY_ID = 1L;
	private static final Long DEVICE_ID = 1L;

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
//		gatewayService.save(defineGateways().get(0));
//		assertNotNull(gatewayService.findById(1L));

		when(gatewayService.save(defineGateways().get(0))).thenReturn(defineGateways().get(0));
		gatewayService.save(defineGateways().get(0));
		verify(gatewayRepository, times(1)).save(defineGateways().get(0));
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
		when(deviceRepository.findByGatewayId(GATEWAY_ID)).thenReturn(defineDevices());
		assertThat(deviceService.findByGatewayId(GATEWAY_ID)).size().isGreaterThan(0);
	}

	@Test
	void addDevice() {
		deviceService.save(defineDevices().get(0), GATEWAY_ID);
		verify(gatewayRepository, times(1)).getById(GATEWAY_ID);
	}

	@Test
	void removeDevice() {
		when(deviceRepository.getById(DEVICE_ID)).thenReturn(defineDevices().get(0));

		deviceService.remove(DEVICE_ID);

		verify(deviceRepository, times(1)).getById(DEVICE_ID);
		verify(deviceRepository, times(1)).deleteById(DEVICE_ID);
	}

	private List<Gateway> defineGateways() {
		List<Gateway> gateways = new ArrayList<>();

		Gateway gateway = new Gateway(GATEWAY_ID, "DFGF111111FD11", "Acer", "10.0.1.0");
		Gateway gateway2 = new Gateway(2L, "EEE2222FD11", "Acer2", "10.0.3.0");

		gateways.add(gateway);
		gateways.add(gateway2);

		return gateways;
	}

	private List<Device> defineDevices() {
		List<Device> devices = new ArrayList<>();

		Device device1 = new Device(DEVICE_ID, "124323453", "Ericsson", new Date(), DeviceStatus.OFFLINE,
				defineGateways().get(0));
		Device device2 = new Device(2L, "124323232", "Ericsson", new Date(), DeviceStatus.OFFLINE,
				defineGateways().get(0));

		devices.add(device1);
		devices.add(device2);

		return devices;
	}

}
