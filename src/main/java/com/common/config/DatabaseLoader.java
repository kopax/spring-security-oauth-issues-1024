package com.common.config;

import com.common.store.I18nStore;
import com.domain.companyManagement.Company;
import com.domain.security.SiteContent;
import com.domain.security.SiteFunction;
import com.domain.security.SiteService;
import com.domain.userManagement.Manager;
import com.domain.userManagement.OAuthClient;
import com.domain.userManagement.Role;
import com.myapp.api.common.siteServices.SiteContentRepository;
import com.myapp.api.common.siteServices.SiteFunctionRepository;
import com.myapp.api.common.siteServices.SiteServiceRepository;
import com.myapp.api.companyManagement.CompanyRepository;
import com.myapp.api.userManagement.ManagerRepository;
import com.myapp.api.userManagement.OAuthClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Configuration
@Profile({"default"})
@EnableConfigurationProperties
public class DatabaseLoader implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	private Environment environment;

	private final ManagerRepository managerRepository;
	private final CompanyRepository companyRepository;

	private final SiteServiceRepository siteServiceRepository;
	private final SiteFunctionRepository siteFunctionRepository;
	private final SiteContentRepository siteContentRepository;

	private final OAuthClientRepository oAuthClientRepository;

	@Autowired
	public DatabaseLoader(
			ManagerRepository managerRepository,
			CompanyRepository companyRepository,
			SiteServiceRepository siteServiceRepository,
			OAuthClientRepository oAuthClientRepository,
			SiteFunctionRepository siteFunctionRepository,
			SiteContentRepository siteContentRepository
	) {
		this.managerRepository = managerRepository;
		this.companyRepository = companyRepository;
		this.siteServiceRepository = siteServiceRepository;
		this.oAuthClientRepository = oAuthClientRepository;
		this.siteFunctionRepository = siteFunctionRepository;
		this.siteContentRepository = siteContentRepository;
	}


	@Override
	public void run(String... strings) throws Exception {
		try {

			String ROLE_ADMIN = Role.ROLE_ADMIN.name();
			String ROLE_MANAGER = Role.ROLE_MANAGER.name();
			String ROLE_USER = Role.ROLE_USER.name();
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken("install", "doesn't matter",
							AuthorityUtils.createAuthorityList(ROLE_ADMIN, ROLE_MANAGER, ROLE_USER)));

			// Forced reset
			if (isInstalled()) {
				return;
			}

			if (environment.acceptsProfiles("default")) {
				logger.debug("!!!!!!!!! populate h2 database !!!!!!!!!");
			}
			// Manager DKA/AJT/AGD
			Manager admin = installAdmin();

			// Site services
			installUserManagementService();
			installCompanyManagement();
			installCustomerManagement();
			installAppManagement();

			Company testCompany = installTestCompany();
			installClientApp(admin, testCompany);

			SecurityContextHolder.clearContext();

		} catch (Exception e) {
			logger.error("Can't load database data because of " + e.getMessage());
			throw e;
		}

	}

	@NotNull
	private Company installTestCompany() {
		Company testCompany = new Company();
		testCompany.setAddress("10 mcdonald street");
		testCompany.setName("testCompany");
		testCompany.setFoundingDate(LocalDate.of(2016, 6, 28));
		this.companyRepository.save(testCompany);
		return testCompany;
	}

	private void installAppManagement() {
		SiteService appManagement = new SiteService();
		appManagement.setName("App management");
		appManagement.setMessageId(I18nStore.SITE_SERVICE_APP_MANAGEMENT);
		this.siteServiceRepository.save(appManagement);
	}

	private void installCustomerManagement() {
		SiteService customerManagement = new SiteService();
		customerManagement.setName("Customer management");
		customerManagement.setMessageId(I18nStore.SITE_SERVICE_CUSTOMER_MANAGEMENT);
		this.siteServiceRepository.save(customerManagement);
	}

	private void installCompanyManagement() {
		SiteService companyManagement = new SiteService();
		companyManagement.setName("Company management");
		companyManagement.setMessageId(I18nStore.SITE_SERVICE_COMPANY_MANAGEMENT);
		this.siteServiceRepository.save(companyManagement);
	}

	private void installUserManagementService() {
		SiteService userManagementService = new SiteService();
		userManagementService.setName("User management");
		userManagementService.setMessageId(I18nStore.SITE_SERVICE_USER_MANAGEMENT);
		this.siteServiceRepository.save(userManagementService);

		SiteFunction userManagerFunction = new SiteFunction();
		userManagerFunction.setName("User management");
		userManagerFunction.setMessageId("api.myapp.com.userManagement.name");
		userManagerFunction.setSiteService(userManagementService);

		SiteContent viewManagerContent = new SiteContent();
		viewManagerContent.setName("view");
		viewManagerContent.setMessageId("api.myapp.com.userManagement.manager.view");
		viewManagerContent.setSiteFunction(userManagerFunction);

		this.siteServiceRepository.save(userManagementService);
		this.siteFunctionRepository.save(userManagerFunction);
		this.siteContentRepository.save(viewManagerContent);
	}

	private void installClientApp(Manager user, Company company) {
		String clientId = "myfirstapp";
		OAuthClient clientApp = new OAuthClient();
		clientApp.setClientId(clientId);
		clientApp.setClientSecret(passwordEncoder.encode("test"));

		ArrayList<String> authorizedGrandTypeList = new ArrayList<>();
		authorizedGrandTypeList.add(OAuthClient.GRANT_TYPE_AUTHORIZATION_CODE);
		authorizedGrandTypeList.add(OAuthClient.GRANT_TYPE_REFRESH_TOKEN);
		clientApp.setAuthorizedGrantTypeList(authorizedGrandTypeList);

		ArrayList<Role> roleList = new ArrayList<>();
		roleList.add(Role.ROLE_CLIENT);
		clientApp.setRoleList(roleList);

		ArrayList<String> scopeList = new ArrayList<>();
		scopeList.add(OAuthClient.SCOPE_READ);
		scopeList.add(OAuthClient.SCOPE_WRITE);
		scopeList.add(OAuthClient.SCOPE_TRUST);
		clientApp.setScopeList(scopeList);
		clientApp.setAutoApprove(true);


		Integer oneMinuteInSeconds = 60;

		ArrayList<String> registeredRedirectUriList = new ArrayList<>();
		registeredRedirectUriList.add("http://localhost:8081/");
		clientApp.setAccessTokenValiditySeconds(oneMinuteInSeconds * 1); // 1 min
		clientApp.setRefreshTokenValiditySeconds(oneMinuteInSeconds * 2); // 2 min

		clientApp.setRegisteredRedirectUriList(registeredRedirectUriList);

		HashMap<String, Object> additionalInformationMap = new HashMap<>();
		additionalInformationMap.put("companyId", company.getId());
		additionalInformationMap.put("companyName", company.getName());
		clientApp.setAdditionalInformationMap(additionalInformationMap);

		ArrayList<String> resourceIdList = new ArrayList<>();
		resourceIdList.add(ResourceServerConfig.RESOURCE_ID);
		clientApp.setResourceIdList(resourceIdList);

		clientApp.setManager(user);
		this.oAuthClientRepository.save(clientApp);
	}

	@NotNull
	private Manager installAdmin() {
		ArrayList<Role> adminRoleList = new ArrayList<>();
		adminRoleList.add(Role.ROLE_MANAGER);
		adminRoleList.add(Role.ROLE_ADMIN);
		adminRoleList.add(Role.ROLE_USER);
		Manager admin = new Manager("admin", passwordEncoder.encode("verysecret"), adminRoleList);
		this.managerRepository.save(admin);
		return admin;
	}

	private Boolean isInstalled() {
		Manager admin = this.managerRepository.findByLogin("admin");
		return null != admin;
	}

}