package com.example.ride.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractWebServiceResponse implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1672596376266116629L;

	private boolean success;
	private String message;

}
