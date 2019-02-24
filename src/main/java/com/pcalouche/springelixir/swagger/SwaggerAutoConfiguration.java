package com.pcalouche.springelixir.swagger;

import com.pcalouche.springelixir.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@Configuration
@ConditionalOnClass(EnableSwagger2.class)
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);

    @Bean
    public Docket docket(SwaggerProperties swaggerProperties) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.isEnabled())
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .build()
                .enableUrlTemplating(true);
        // Add any additional ignored parameters that would have come from the client config
        if (swaggerProperties.getIgnoredParameterTypes() != null) {
            for (Class ignoredParameterType : swaggerProperties.getIgnoredParameterTypes()) {
                docket.ignoredParameterTypes(ignoredParameterType);
            }
        }
        return docket;
    }

    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        String version = "UNKNOWN";
        if (swaggerProperties.getVersionFilePath() != null) {
            try {
                version = FileUtils.readClassPathResource(swaggerProperties.getVersionFilePath());
            } catch (IOException e) {
                logger.error("Unable to process version file path at->" + swaggerProperties.getVersionFilePath() +
                        " so API version of Swagger Documentation is UNKNOWN.  " +
                        "Check your swagger.version-file-path property value.");
            }
        }
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(version)
                .contact(new Contact(
                        "Tax Management Associates",
                        "https://www.tma1.com/",
                        ""))
                .build();
    }
}