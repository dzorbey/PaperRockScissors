package com.restapi.utility;

import com.restapi.domain.AbstractResponse;
import com.restapi.domain.ErrorResponse;

public class Utility {
	
	public static AbstractResponse error(String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		errorResponse.setSuccess(false);
		return errorResponse;
	}
}
