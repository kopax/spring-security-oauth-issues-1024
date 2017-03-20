package com.common.model;

public abstract class ClientApplicationError {

	private Integer id;
	private Integer message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMessage() {
		return message;
	}

	public void setMessage(Integer message) {
		this.message = message;
	}

}
