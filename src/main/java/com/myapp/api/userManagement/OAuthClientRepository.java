package com.myapp.api.userManagement;

import com.common.store.HttpPathStore;
import com.domain.userManagement.OAuthClient;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "oAuthClients", path = HttpPathStore.REPO_PATH_OAUTH_CLIENT)
public interface OAuthClientRepository extends MybatisRepository<OAuthClient, Long> {

	public OAuthClient findByClientId(String clientId);

}
