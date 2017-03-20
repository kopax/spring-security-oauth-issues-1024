package com.common.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mybatis.domains.LongId;

import java.util.Comparator;

public class LongIdComparator implements Comparator<LongId> {

	private static final Logger logger = LoggerFactory.getLogger(LongIdComparator.class);

	public int compare(LongId db1,
	                   LongId db2) {
		return db1.getId().compareTo(db2.getId());
	}
}
