package com.common.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.*;
import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.JdbcType;

import javax.validation.constraints.NotNull;
import java.time.Instant;

import static org.apache.ibatis.type.JdbcType.*;

public abstract class VersionId extends LongId implements Cloneable {

	private static final Logger logger = LoggerFactory.getLogger(VersionId.class);

	@Version
	@NotNull
	@JdbcType(BIGINT)
	@Column(name = "VERSION")
	private Integer version;

	@CreatedDate
	@NotNull
	@JdbcType(TIMESTAMP)
	@Column(name = "CREATED_DATE")
	@JsonUnwrapped
	private Instant createdDate;

	@LastModifiedDate
	@NotNull
	@JdbcType(TIMESTAMP)
	@JsonUnwrapped
	@Column(name = "LAST_MODIFIED_DATE")
	private Instant lastModifiedDate;

	@CreatedBy
	@Column(name = "CREATED_BY")
	@NotNull
	@JdbcType(BIGINT)
	private Long createdById;

	@Column(name = "LAST_MODIFIED_BY")
	@LastModifiedBy
	@JdbcType(BIGINT)
	private Long lastModifiedById;

	@JdbcType(BOOLEAN)
	@Column(name = "ACTIVE")
	@NotNull
	private Boolean active = true;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public Long getLastModifiedById() {
		return lastModifiedById;
	}

	public void setLastModifiedById(Long lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
