
package com.myapp.api.common.siteServices.projection;

import com.domain.security.SiteFunction;
import com.domain.security.SiteService;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by dka on 1/13/17.
 */
@Projection(name = "siteFunctionList", types = { SiteService.class })
public interface SiteServiceDefaultProjection {

//	String getTranslateMessage();

	String getName();

	List<SiteFunction> getSiteFunctionList();

}
