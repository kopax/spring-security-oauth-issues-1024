package com.myapp.api.customerManagement;

import com.common.store.HttpPathStore;
import com.domain.customerManagement.Customer;
import org.springframework.data.mybatis.repository.support.MybatisRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('MANAGER')")
@RepositoryRestResource(collectionResourceRel = "customers", path = HttpPathStore.REPO_PATH_CUSTOMERS)
public interface CustomerRepository extends MybatisRepository<Customer, Long> {

	public Customer findByLastName(String lastName);

}