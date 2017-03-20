
package com.domain.security;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.JoinColumn;
import org.springframework.data.mybatis.annotations.OneToOne;

import javax.validation.constraints.NotNull;

@Entity(table = "SITE_CONTENT")
public class SiteContent extends SiteAccess {

	@OneToOne
	@JoinColumn(name = "SITE_FUNCTION_ID")
	@NotNull
	private SiteFunction siteFunction;

	public SiteFunction getSiteFunction() {
		return siteFunction;
	}

	public void setSiteFunction(SiteFunction siteFunction) {
		this.siteFunction = siteFunction;
	}
}
