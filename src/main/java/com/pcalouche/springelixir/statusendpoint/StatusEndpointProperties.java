package com.pcalouche.springelixir.statusendpoint;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "status-endpoint")
public class StatusEndpointProperties {

	/**
	 * Enables status endpoint or not
	 */
	private boolean enabled = true;

	/**
	 * The application name to display is status response
	 */
	@NotEmpty
	private String appName;

	/**
	 * The application version to display is status response
	 */
	@NotEmpty
	private String version;

}
