package com.pcalouche.springelixir.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * Enables Swagger or not (Default is off. Should turn off for UAT/Prod environments)
     */
    private boolean enabled;
    /**
     * The base package to  scan for Swagger API document generation.
     */
    @NotEmpty
    private String basePackage = "NOT SET";
    /**
     * Title of Swagger API documentation (e.g. MI AMAR API)
     */
    @NotEmpty
    private String title = "NOT SET";
    /**
     * Description of Swagger API documentation (e.g. Endpoints available in the MI AMAR API)
     */
    @NotEmpty
    private String description = "NOT SET";
    /**
     * Version file path for the Swagger API documentation relevant to src/main/resources
     */
    @NotEmpty
    private String versionFilePath = "artifact-version.text";
    /**
     * List of parameter types to ignore in Swagger documentation
     */
    private List<Class> ignoredParameterTypes;

}
