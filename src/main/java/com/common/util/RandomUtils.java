/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RandomUtils {

	private static final Logger logger = LoggerFactory.getLogger(RandomUtils.class);
	public static SecureRandom random = new SecureRandom();
	public static String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
}