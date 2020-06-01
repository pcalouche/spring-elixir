package com.pcalouche.springelixir.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SpringDocProperties.class)
@ConditionalOnClass(OpenAPI.class)
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true")
public class SpringDocAutoConfiguration {

    @Bean
    public OpenAPI openAPI(SpringDocProperties springDocProperties) {
        return new OpenAPI()
                .info(new Info()
                        .title(springDocProperties.getTitle())
                        .description(springDocProperties.getDescription())
                        .version(springDocProperties.getVersion())
                        .contact(new Contact()
                                .name(springDocProperties.getContactName())
                                .url(springDocProperties.getContactUrl())
                        )
                );
    }
}
