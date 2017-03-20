
package com.myapp.api.common.siteServices.projection;

import com.domain.security.SiteContent;
import com.domain.security.SiteService;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by dka on 1/13/17.
 */
@Projection(name = "siteFunctionList", types = { SiteService.class })
public interface SiteFunctionDefaultProjection {

//	String getTranslateMessage();

	String getName();

	SiteService getSiteService();

	List<SiteContent> getSiteContentList();

}
