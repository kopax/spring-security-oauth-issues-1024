
package com.myapp.api.userManagement.projection;

import com.domain.userManagement.Manager;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;

/**
 * Created by dka on 1/13/17.
 */
@Projection(name = "managerDefault", types = { Manager.class })
public interface ManagerProjection {

	Long getId();

	String getUsername();

	Instant getCreatedDate();

}
