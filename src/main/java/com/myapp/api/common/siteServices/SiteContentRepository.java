
package com.myapp.api.common.siteServices;

import com.common.store.HttpPathStore;
import com.domain.security.SiteContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(collectionResourceRel = "siteContents", path = HttpPathStore.REPO_PATH_SITE_CONTENTS)
public interface SiteContentRepository extends CrudRepository<SiteContent, Long> {

}