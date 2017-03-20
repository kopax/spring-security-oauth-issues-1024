package com.myapp.api.companyManagement;

import com.common.store.HttpPathStore;
import com.domain.companyManagement.Company;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
@RepositoryRestResource(collectionResourceRel = "companies", path = HttpPathStore.REPO_PATH_COMPANIES)
public interface CompanyRepository extends MybatisRepository<Company, Long> {

}