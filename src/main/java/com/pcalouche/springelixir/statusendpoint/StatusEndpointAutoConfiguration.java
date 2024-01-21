package com.pcalouche.springelixir.statusendpoint;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "status-endpoint", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(StatusEndpointProperties.class)
public class StatusEndpointAutoConfiguration {

	@Bean
	public StatusController statusController(StatusEndpointProperties statusEndpointProperties) {
		return new StatusController(statusEndpointProperties);
	}

}
