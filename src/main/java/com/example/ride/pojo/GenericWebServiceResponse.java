package com.example.ride.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericWebServiceResponse extends AbstractWebServiceResponse {

	private Object payload;

	public GenericWebServiceResponse() {
	}

	public GenericWebServiceResponse(Object payload) {
		this.payload = payload;
		setSuccess(true);
	}

	public GenericWebServiceResponse(boolean success, String message, Object payload) {
		setSuccess(success);
		setMessage(message);
		this.payload = payload;
	}

	public GenericWebServiceResponse(boolean success, String message) {
		setSuccess(success);
		setMessage(message);
	}

	public void setErrorMessage(String message) {
		setSuccess(false);
		setMessage(message);
	}

	/**
	 * Lazy create a simple success response
	 * 
	 * @return {@link GenericWebServiceResponse}
	 */
	public static final GenericWebServiceResponse ok() {
		return new GenericWebServiceResponse(true, null);
	}

}
