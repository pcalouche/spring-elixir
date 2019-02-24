package com.pcalouche.springelixir.statusendpoint;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "statusendpoint")
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
}
