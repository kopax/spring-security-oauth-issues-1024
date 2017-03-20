/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mybatis.annotations.Column;

/**
 * This is overwriting the default LongId so we can force uppercase column name usage
 * Created by dka on 12/23/16.
 */
public class LongId extends org.springframework.data.mybatis.domains.LongId {

	@Override
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}


	@Transient
	@JsonIgnore
	@Override
	public boolean isNew() {
		return null == this.getId();
	}

}
