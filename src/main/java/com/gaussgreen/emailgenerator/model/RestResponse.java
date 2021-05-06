package com.gaussgreen.emailgenerator.model;

import java.util.HashMap;

public class RestResponse<T> {
	
	private HashMap<String,String> errorMessages;
	private T output;

	public RestResponse(HashMap<String,String> errorMessages, T  output) {
		super();
		this.errorMessages = errorMessages;
		this.output = output;
	}

	public RestResponse() {
		super();
		this.errorMessages = new HashMap<String,String>();
	}

	public HashMap<String,String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(HashMap<String,String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public T getOutput() {
		return output;
	}

	public void setOutput(T output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "RestResponse [errorMessages=" + errorMessages + ", output=" + output + "]";
	}

}
