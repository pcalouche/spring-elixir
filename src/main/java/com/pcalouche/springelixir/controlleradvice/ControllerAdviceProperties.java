package com.pcalouche.springelixir.controlleradvice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "controlleradvice")
public class ControllerAdviceProperties {
    /**
     * Enables standard controller advice or not
     */
    private boolean enabled = true;
}
