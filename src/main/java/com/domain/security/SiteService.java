
package com.domain.security;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.JoinColumn;
import org.springframework.data.mybatis.annotations.OneToMany;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(table = "SITE_SERVICE")
public class SiteService extends SiteAccess {

	@OneToMany
	@JoinColumn(name = "SITE_SERVICE_ID", referencedColumnName = "ID")
	@NotNull
	private List<SiteFunction> siteFunctionList;

	public List<SiteFunction> getSiteFunctionList() {
		return siteFunctionList;
	}

	public void setSiteFunctionList(List<SiteFunction> siteFunctionList) {
		this.siteFunctionList = siteFunctionList;
	}

}
