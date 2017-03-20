package com.domain.userManagement;

import com.common.model.VersionId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.typeHandler.RoleListTypeHandler;
import org.springframework.data.mybatis.annotations.Column;
import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.annotations.JdbcType;
import org.springframework.data.mybatis.annotations.TypeHandler;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

@Entity(table = "MANAGER")
public class Manager extends VersionId implements UserDetails, CredentialsContainer {

	@JdbcType(VARCHAR)
	@Column(name = "LOGIN")
	private String login;

	@JsonIgnore
	@JdbcType(VARCHAR)
	@Column(name = "PASSWORD")
	private String password;

	@JdbcType(VARCHAR)
	@TypeHandler(RoleListTypeHandler.class)
	@Column(name = "AUTHORITIES")
	@NotNull
	private List<Role> roleList;

	protected Manager() {
	}

	public Manager(String login, String password, List<Role> roleList) {
		this.login = login;
		this.password = password;
		this.roleList = roleList;
	}

	public Manager(String login) {
		setLogin(login);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isEnabled() {
		return getActive();
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roleList = getRoleList();
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roleList) {
			authorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		ArrayList<Role> roleList = new ArrayList<>();
		for (GrantedAuthority authority : authorities) {
			roleList.add(Role.valueOf(authority.getAuthority()));
		}
		this.setRoleList(roleList);
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return getLogin();
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public void eraseCredentials() {
		setPassword(null);
	}
}
