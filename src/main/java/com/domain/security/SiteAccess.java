
package com.domain.security;

import com.common.model.I18nId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mybatis.annotations.Column;

import javax.validation.constraints.NotNull;

/**
 * Created by dka on 1/13/17.
 */
public abstract class SiteAccess extends I18nId {

	@Column(name = "NAME")
	@NotNull
	private String name;

	protected SiteAccess() {
	}

	public SiteAccess(String name) {
		this.name = name;
	}

	public SiteAccess(String name, String i18nMessageId) {
		this.name = name;
		this.setMessageId(i18nMessageId);
	}

	@JsonIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@JsonIgnore
	public String getDefaultMessage() {
		return getName();
	}

}
