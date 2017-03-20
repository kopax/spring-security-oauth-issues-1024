package com.myapp.api.userManagement;

import com.common.store.HttpPathStore;
import com.domain.userManagement.Manager;
import com.myapp.api.userManagement.projection.ManagerProjection;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(collectionResourceRel = "managers", path = HttpPathStore.REPO_PATH_MANAGERS, excerptProjection = ManagerProjection.class)
public interface ManagerRepository extends MybatisRepository<Manager, Long> {

//	@PreAuthorize("permitAll")
	public Manager findByLogin(String login);

}