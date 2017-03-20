package com.domain.userManagement;

import com.common.typeHandler.CommaSeparatedTypeHandler;
import com.common.typeHandler.JsonMapTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.typeHandler.RoleListTypeHandler;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mybatis.annotations.*;
import org.springframework.data.mybatis.annotations.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

import static org.apache.ibatis.type.JdbcType.*;
import static org.springframework.data.mybatis.annotations.Id.GenerationType.ASSIGNATION;

@Entity(table = "oauth_client_details")
public class OAuthClient implements ClientDetails, Persistable<String> {

	public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
	public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

	public static final String SCOPE_READ = "read";
	public static final String SCOPE_WRITE = "write";
	public static final String SCOPE_TRUST = "trust";

	@Id(strategy = ASSIGNATION)
	@JdbcType(VARCHAR)
	@Column(name = "client_id")
	@NotNull
	private String clientId;

	@JdbcType(VARCHAR)
	@Column(name = "resource_ids")
	@TypeHandler(CommaSeparatedTypeHandler.class)
	@NotNull
	private List<String> resourceIdList;

	@JdbcType(VARCHAR)
	@Column(name = "scope")
	@TypeHandler(CommaSeparatedTypeHandler.class)
	@NotNull
	private List<String> scopeList;

	@JdbcType(VARCHAR)
	@Column(name = "client_secret")
	@NotNull
	private String clientSecret;

	@JdbcType(VARCHAR)
	@Column(name = "authorized_grant_types")
	@TypeHandler(CommaSeparatedTypeHandler.class)
	@NotNull
	private List<String> authorizedGrantTypeList;

	@JdbcType(VARCHAR)
	@Column(name = "web_server_redirect_uri")
	@TypeHandler(CommaSeparatedTypeHandler.class)
	@NotNull
	private List<String> registeredRedirectUriList;

	@JdbcType(VARCHAR)
	@Column(name = "authorities")
	@TypeHandler(RoleListTypeHandler.class)
	@NotNull
	private List<Role> roleList;

	@JdbcType(INTEGER)
	@Column(name = "access_token_validity")
	@NotNull
	private Integer accessTokenValiditySeconds;


	@JdbcType(INTEGER)
	@Column(name = "refresh_token_validity")
	@NotNull
	private Integer refreshTokenValiditySeconds;

	@JdbcType(VARCHAR)
	@Column(name = "additional_information")
	@TypeHandler(JsonMapTypeHandler.class)
	@JsonIgnore
	private Map<String, Object> additionalInformationMap;

	@JdbcType(VARCHAR)
	@Column(name = "autoapprove")
	@NotNull
	private Boolean isAutoApprove;

	@JdbcType(BIGINT)
	@OneToOne
	@JoinColumn(name = "MANAGER_ID")
	@NotNull
	private Manager manager;

	@Version
	@NotNull
	@JdbcType(BIGINT)
	@Column(name = "VERSION")
	private Integer version;

	@CreatedDate
	@NotNull
	@JdbcType(TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Instant createdDate;

	@LastModifiedDate
	@NotNull
	@JdbcType(TIMESTAMP)
	@Column(name = "LAST_MODIFIED_DATE")
	private Instant lastModifiedDate;

	@CreatedBy
	@Column(name = "CREATED_BY")
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


	@Override
	public String getId() {
		return getClientId();
	}

	@Override
	public boolean isNew() {
		return null == getCreatedDate();
	}


	@Override
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}


	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}


	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}


	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public Map<String, Object> getAdditionalInformationMap() {
		return additionalInformationMap;
	}

	public void setAdditionalInformationMap(Map<String, Object> additionalInformationMap) {
		this.additionalInformationMap = additionalInformationMap;
	}

	public Long getLastModifiedById() {
		return lastModifiedById;
	}

	public void setLastModifiedById(Long lastModifiedById) {
		this.lastModifiedById = lastModifiedById;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getAutoApprove() {
		return isAutoApprove;
	}

	public void setAutoApprove(Boolean autoApprove) {
		isAutoApprove = autoApprove;
	}


	public List<String> getScopeList() {
		return scopeList;
	}

	public void setScopeList(List<String> scopeList) {
		this.scopeList = scopeList;
	}

	public List<String> getAuthorizedGrantTypeList() {
		return authorizedGrantTypeList;
	}

	public void setAuthorizedGrantTypeList(List<String> authorizedGrantTypeList) {
		this.authorizedGrantTypeList = authorizedGrantTypeList;
	}

	public List<String> getRegisteredRedirectUriList() {
		return registeredRedirectUriList;
	}

	public void setRegisteredRedirectUriList(List<String> registeredRedirectUriList) {
		this.registeredRedirectUriList = registeredRedirectUriList;
	}

	public List<String> getResourceIdList() {
		return resourceIdList;
	}

	public void setResourceIdList(List<String> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}


	@Override
	public Set<String> getResourceIds() {
		return new HashSet<>(getResourceIdList());
	}

	@Override
	public Set<String> getScope() {
		return new HashSet<>(getScopeList());
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return new HashSet<>(getAuthorizedGrantTypeList());
	}


	@Override
	public Set<String> getRegisteredRedirectUri() {
		return new HashSet<>(getRegisteredRedirectUriList());
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return getAdditionalInformationMap();
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return getAutoApprove();
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public boolean isScoped() {
		return true;
	}


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<Role> roleList = getRoleList();
		List<GrantedAuthority> authorities = new ArrayList<>(roleList.size());

		for (Role role : roleList) {
			authorities.add(
					new SimpleGrantedAuthority(role.toString())
			);
		}

		return authorities;
	}
}
