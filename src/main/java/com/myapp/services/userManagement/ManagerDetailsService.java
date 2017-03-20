/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp.services.userManagement;


import com.domain.userManagement.Manager;
import com.myapp.api.userManagement.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ManagerDetailsService implements UserDetailsService {

	@Autowired
	private final ManagerRepository managerRepository;

	@Autowired
	public ManagerDetailsService(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}

	@Override
	public Manager loadUserByUsername(String login) throws UsernameNotFoundException {
		Manager manager = this.managerRepository.findByLogin(login);
		if( null == manager ){
			throw new UsernameNotFoundException("User not found.");
		}
		return manager;
	}
}
