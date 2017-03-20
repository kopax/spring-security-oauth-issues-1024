package com.common.store;

public final class HttpPathStore {

	/**
	 * This is the context path
	 */
	public static final String CONTEXT_PATH = "/";


	/**
	 * This is the ping endpoint
	 */
	public static final String PING = CONTEXT_PATH + "ping";

	/**
	 * This is are requests available parameters
	 */
	public static final String PARAM_ID_VALUE = "id";
	public static final String PARAM_ID = "/{" + PARAM_ID_VALUE + "}";
	public static final String PARAM_CLIENT_VALUE = "client_id";
	public static final String PARAM_CLIENT = "/{" + PARAM_CLIENT_VALUE + "}";

	/**
	 * These are authentication endpoints
	 */
	public static final String LOGIN = CONTEXT_PATH + "login";
	public static final String LOGOUT = CONTEXT_PATH + "logout";
	public static final String LOGIN_FROM_ERROR = LOGIN + "?error";
	public static final String LOGIN_FROM_LOGOUT = LOGIN + "?logout";
	public static final String LOGIN_OAUTH_CLIENT_CB = CONTEXT_PATH + "cb" + PARAM_CLIENT;
	public static final String ERROR = CONTEXT_PATH + "error";

	/**
	 * These are our spring-hateoas repositories endpoints
	 */
	public static final String REST_PATH = CONTEXT_PATH;

	/**
	 * These are our spring repositories path
	 */
	public static final String REPO_PATH_MANAGERS = "/managers";
	public static final String REPO_PATH_CUSTOMERS = "/customers";
	public static final String REPO_PATH_OAUTH_CLIENT = "/oAuthClients";
	public static final String REPO_PATH_COMPANIES = "/companies";
	public static final String REPO_PATH_SITE_SERVICES = "/siteServices";
	public static final String REPO_PATH_SITE_FUNCTIONS = "/siteFunctions";
	public static final String REPO_PATH_SITE_CONTENTS = "/siteContents";
}
