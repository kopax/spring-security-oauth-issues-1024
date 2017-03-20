/*
 * Kopax Ltd Copyright (c) 2016.
 */

package com.myapp.web;

import com.common.config.ResourceServerConfig;
import com.common.store.HttpPathStore;
import com.domain.userManagement.OAuthClient;
import com.myapp.api.userManagement.OAuthClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class LoginController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private OAuthClientRepository oAuthClientRepository;

	/**
	 * The client SHOULD verify if the key is correct
	 *
	 * @param clientId
	 * @param locale
	 * @return the client_id + "_" + resource_id;
	 */
	@RequestMapping(value = HttpPathStore.LOGIN_OAUTH_CLIENT_CB, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public
	@ResponseBody
	ResponseEntity cb(
			@PathVariable(value = HttpPathStore.PARAM_CLIENT_VALUE) final String clientId,
			final Locale locale) throws OAuth2AccessDeniedException {
		try {
			OAuthClient client = oAuthClientRepository.findByClientId(clientId);
			return ResponseEntity.ok("\"" + client.getClientId() + "_" + ResourceServerConfig.RESOURCE_ID + "\"");
		} catch (Exception e) {
			String errorMsg = messageSource.getMessage("api.auth.login.failure", null, locale);
			throw new OAuth2AccessDeniedException(errorMsg);
		}
	}

	    @RequestMapping(
		    value = HttpPathStore.LOGIN,
		    method = RequestMethod.GET
	    )
        public ResponseEntity nullLogin() {
		    return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
}
