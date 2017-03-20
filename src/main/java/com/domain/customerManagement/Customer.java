package com.domain.customerManagement;

import com.domain.common.PersonABC;
import com.domain.companyManagement.Company;
import org.springframework.data.mybatis.annotations.*;

import javax.validation.constraints.NotNull;

import static org.apache.ibatis.type.JdbcType.BIGINT;
import static org.apache.ibatis.type.JdbcType.VARCHAR;

@Entity(table = "CUSTOMER")
public class Customer extends PersonABC {

	@JdbcType(VARCHAR)
	@Column(name = "PRINCIPAL_PHONE")
	private String principalPhone;

	@JdbcType(VARCHAR)
	@Column(name = "PRINCIPAL_ADDRESS")
	private String principalAddress;

	@JdbcType(BIGINT)
	@OneToOne
	@JoinColumn(name = "COMPANY_ID")
	@NotNull
	private Company company;

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getPrincipalAddress() {
		return principalAddress;
	}

	public void setPrincipalAddress(String principalAddress) {
		this.principalAddress = principalAddress;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
