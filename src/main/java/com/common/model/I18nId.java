/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.common.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.JdbcType;

import javax.validation.constraints.NotNull;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

public abstract class I18nId extends LongId implements TranslateMessageInterface {

	private static final Logger logger = LoggerFactory.getLogger(I18nId.class);

	@JdbcType(VARCHAR)
	@Column(name = "I18N_ID")
	@NotNull
	private String messageId;

	@Transient
	private TranslateMessage translateMessage;

	private String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public TranslateMessage getTranslateMessage() {
		if (null == translateMessage) {
			translateMessage = new TranslateMessage(getMessageId(), getDefaultMessage());
		}
		return translateMessage;
	}
}
