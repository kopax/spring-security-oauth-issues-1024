
package com.myapp.api.common.siteServices;

import com.common.store.HttpPathStore;
import com.domain.security.SiteService;
import com.myapp.api.common.siteServices.projection.SiteServiceDefaultProjection;
import org.springframework.data.mybatis.repository.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(collectionResourceRel = "siteServices", path = HttpPathStore.REPO_PATH_SITE_SERVICES, excerptProjection = SiteServiceDefaultProjection.class)
public interface SiteServiceRepository extends CrudRepository<SiteService, Long> {

	List<SiteService> findAll();

	@Query("getAll")
	List<SiteService> getAll();


}