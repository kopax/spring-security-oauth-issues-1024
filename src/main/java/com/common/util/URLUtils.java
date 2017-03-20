package com.common.util;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {


	/**
	 * Return the base url (eg: http://localhost:8080)
	 * @param request
	 * @return String
	 * @throws MalformedURLException
	 */
	public static String getURLBase(HttpServletRequest request) throws MalformedURLException {
		URL requestURL = new URL(request.getRequestURL().toString());
		String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
		return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	}

}
