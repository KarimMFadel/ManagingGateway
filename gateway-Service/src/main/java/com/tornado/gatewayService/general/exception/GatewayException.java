package com.tornado.gatewayService.general.exception;

public class GatewayException extends RuntimeException{
	
	private static final long serialVersionUID = 1969442617900358857L;

	public GatewayException() {
		super();
	}
	
	public GatewayException(String message) {
		super(message);
	}
	
	
}
