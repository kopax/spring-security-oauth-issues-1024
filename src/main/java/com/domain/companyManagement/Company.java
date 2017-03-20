package com.domain.companyManagement;

import com.common.model.VersionId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.JdbcType;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static org.apache.ibatis.type.JdbcType.*;

@Entity(table = "COMPANY")
public class Company extends VersionId {

	@JdbcType(VARCHAR)
	@Column(name = "NAME")
	private String name;

	@JsonIgnore
	@JdbcType(VARCHAR)
	@Column(name = "ADDRESS")
	private String address;

	@JdbcType(VARCHAR)
	@Column(name = "ADDRESS_SECONDARY")
	private String addressSecondary;

	@JdbcType(BOOLEAN)
	@Column(name = "IS_INTERNAL")
	@NotNull
	private Boolean isInternal;

	@JdbcType(VARCHAR)
	@Column(name = "FOUNDING_DATE")
	private LocalDate foundingDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressSecondary() {
		return addressSecondary;
	}

	public void setAddressSecondary(String addressSecondary) {
		this.addressSecondary = addressSecondary;
	}

	public LocalDate getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(LocalDate foundingDate) {
		this.foundingDate = foundingDate;
	}

	public Boolean getInternal() {
		return isInternal;
	}

	public void setInternal(Boolean internal) {
		isInternal = internal;
	}
}
