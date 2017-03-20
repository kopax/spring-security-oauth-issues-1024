/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.common.model;

import com.google.gson.Gson;

import java.util.HashMap;

public class TranslateMessage {

	private final static Gson gson = new Gson();

	private String id;
	private String defaultMessage;

	public TranslateMessage(String id, String defaultMessage) {
		this.id = id;
		this.defaultMessage = defaultMessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public String toJSON() {
		try {
			HashMap<String, String> map = new HashMap<>();
			map.put("id", this.getId());
			map.put("defaultMessage", this.getDefaultMessage());
			return gson.toJson(map);
		} catch (Exception e) {
			return gson.toJson("error");
		}
	}

	public String toString() {
		return this.toJSON();
	}
}
