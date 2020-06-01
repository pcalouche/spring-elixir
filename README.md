# Spring Elixir

All components or only parts of Spring Elixir can be used by a Spring Boot application. Several things are turned on by default, but
can easily be disabled.  See `src/main/resources/META-INF/spring.factories` for a list of auto-configuration in Spring Elixir.

See my [Spring Skeleton](https://github.com/pcalouche/spring-skeleton) for an example of using Spring Elixir's features in a Spring
Boot application.

Refer to [Creating Your Own Auto-configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html) for
more information.

Spring Elixir provides auto configuration for the following things:

- Swagger using Spring Doc Open API for API documentation (https://github.com/springdoc/springdoc-openapi) **Auto-configuration=ON by default**
- Status endpoint **Auto-configuration=ON by default**
- Standard Spring controller advice **Auto-configuration=ON by default**

### Status Endpoint Auto-configuration

These properties are manged in `StatusEndpointProperties.java`.  It is enabled by default.

To disable set this Spring application property as follows:
    
    status-endpoint.enabled=false

The status endpoint also requires that an application name is provided. It easiest to set this in application.properties and override in
application-<environment>.properties if you want the name to appear differently across environments. (Prod, QA, etc.)

    status-endpoint.app-name=My Cool App

### Standard Spring Controller Advice Auto-configuration

These properties are manged in `ControllerAdviceProperties.java`. It is enabled by default. It creates standard exception handling 
with proper HTTP status codes (401, 422, 403, 500, etc.).  Exceptions will be returned in JSON format using `JsonExceptionResponse`.

To disable set this Spring application property as follows:
    
    controlleradvice.enabled=false

### Swagger Auto-configuration

These properties are managed in `SpringDocProperties.java`.  Its auto-configuration will be ignored if 
Spring Doc Open APi is not found on the classpath.

To disable set this Spring application property as follows:
    
    springdoc.api-docs.enabled=true
    
It's best to enable it selectively in application-<environment>.properties files.  
    
These Spring application properties will need to be set accordingly in order for Swagger to document your Spring Controller endpoints. 
Here is an example:

    springdoc.api-docs.enabled=true
    springdoc.api-docs.title=Skeleton API
    springdoc.api-docs.description=Endpoints available in the Spring Skeleton API
    springdoc.api-docs.version=@project.version@
    springdoc.api-docs.contact-name=Philip Calouche
    springdoc.api-docs.contact-url=https://github.com/pcalouche
    
**NOTE** Open API docs are accessible from <HOST>:<PORT>/swagger-ui.html. For example, if your application runs on 
http://localhost:4232 then the URL will be http://localhost:4242/swagger-ui.html.