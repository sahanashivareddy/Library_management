package org.paycorp.librarymanagmentsystem.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseStructure<T> {
	int status;
	String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	T data;

}
