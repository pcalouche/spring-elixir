package com.pcalouche.springelixir.controlleradvice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "controlleradvice", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(ControllerAdviceProperties.class)
public class ControllerAdviceAutoConfiguration {

    @Bean
    public StandardControllerAdvice controllerExceptionAdvice() {
        return new StandardControllerAdvice();
    }
}
