package com.domain.common;

import com.common.model.VersionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.JdbcType;

import java.io.Serializable;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

public abstract class PersonABC extends VersionId implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(PersonABC.class);

	@JdbcType(VARCHAR)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@JdbcType(VARCHAR)
	@Column(name = "LAST_NAME")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
}
