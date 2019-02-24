# Spring Elixir

All components or only parts of Spring Elixir can be used by a Spring Boot application. Several things are turned on by default, but
can easily be disabled.  See `src/main/resources/META-INF/spring.factories` for a list of auto-configuration in Spring Elixir. Refer to 
[Creating Your Own Auto-configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html) for
more information.

Spring Elixir provides auto configuration for the following things:

- Swagger using SpringFox for API documentation (http://springfox.github.io/springfox/) **Auto-configuration=ON by default**
- Status endpoint **Auto-configuration=ON by default**
- Standard Spring controller advice **Auto-configuration=ON by default**

### Status Endpoint Auto-configuration

These properties are manged in `StatusEndpointProperties.java`.  It is enabled by default.

To disable set this Spring application property as follows:
    
    statusendpoint.enabled=false
    
As an environment variable:
    
    STATUSENDPOINT_ENABLED=false

The status endpoint also requires that an application name is provided. It easiest to set this in application.properties and override in
application-<environment>.properties if you want the name to appear differently across environments. (Prod, QA, etc.)

    statusendpoint.app-name=My Cool App

### Standard Spring Controller Advice Auto-configuration

These properties are manged in `ControllerAdviceProperties.java`. It is enabled by default. It create sa standard exception handling 
with proper HTTP status codes (401, 422, 403, 500, etc.).  Exceptions will also be returned in consistent JSON format.  The standard 
controller advice is configured to use Raygun to report exceptions.

To disable set this Spring application property as follows:
    
    controlleradvice.enabled=false
    
As an environment variable:
    
    CONTROLLERADVICE_ENABLED=false

### Swagger Auto-configuration

These properties are managed in `SwaggerProperties.java`.  Swagger is disabled by default.  
Its auto-configuration will be ignored if Swagger is not found on the classpath.

To disable set this Spring application property as follows:
    
    swagger.enabled=true
    
It's best to enable it selectively in application-<environment>.properties files.  
    
While the following Swagger/SpringFox properties can be declared in application-dev.properties, on the command line, or 
as as environment variable it makes the most sense to include in an application.properties because they won't change 
often. See the JavaDoc in `SwaggerProperties.java` for more details.

These Spring application properties will need to be set accordingly in order for Swagger to document your Spring Controller endpoints. 
Here is an example:

    swagger.base-package=net.migov
    swagger.title=MI AMAR API
    swagger.description=Endpoints available in the MI AMAR API
    swagger.version-file-path=artifact-version
    swagger.ignored-parameter-types=org.springframework.security.core.annotation.AuthenticationPrincipal
    swagger.enabled=false
    
**NOTE** Swaggers docs are accessible from <HOST>:<PORT>/swagger-ui.html.  For example, http://localhost:4242/swagger-ui.html.