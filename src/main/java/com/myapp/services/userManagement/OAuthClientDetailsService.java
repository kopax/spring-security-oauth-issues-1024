package com.myapp.services.userManagement;

import com.domain.userManagement.OAuthClient;
import com.myapp.api.userManagement.OAuthClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class OAuthClientDetailsService implements ClientDetailsService {

	@Autowired
	private final OAuthClientRepository oAuthClientRepository;

	@Autowired
	public OAuthClientDetailsService(OAuthClientRepository oAuthClientRepository) {
		this.oAuthClientRepository = oAuthClientRepository;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		// OAuthClientDetails is the domain object that stores the client information
		OAuthClient clientDetails = oAuthClientRepository.findByClientId(clientId);
		if (clientDetails != null) {
			return clientDetails;
		}
		throw new InvalidClientException("Client not found: " + clientId);
	}

}
