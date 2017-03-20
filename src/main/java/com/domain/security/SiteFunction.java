
package com.domain.security;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.JoinColumn;
import org.springframework.data.mybatis.annotations.OneToMany;
import org.springframework.data.mybatis.annotations.OneToOne;

import javax.validation.constraints.NotNull;
import java.util.List;


@Entity(table = "SITE_FUNCTION")
public class SiteFunction extends SiteAccess {

	@OneToOne
	@JoinColumn(name = "SITE_SERVICE_ID")
	@NotNull
	private SiteService siteService;

	@OneToMany
	@JoinColumn(name = "SITE_FUNCTION_ID", referencedColumnName = "ID")
	@NotNull
	private List<SiteContent> siteContentList;

	public SiteService getSiteService() {
		return siteService;
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	public List<SiteContent> getSiteContentList() {
		return siteContentList;
	}

	public void setSiteContentList(List<SiteContent> siteContentList) {
		this.siteContentList = siteContentList;
	}
}
