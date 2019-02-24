package com.pcalouche.springelixir.swagger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
public class SwaggerAutoConfigurationTest {

    @Test
    public void testSwaggerHandlesNoVersionFilePathGracefully() {
        SwaggerProperties swaggerProperties = new SwaggerProperties();
        swaggerProperties.setEnabled(true);
        SwaggerAutoConfiguration swaggerAutoConfiguration = new SwaggerAutoConfiguration();

        assertThatCode(() -> swaggerAutoConfiguration.docket(swaggerProperties))
                .doesNotThrowAnyException();
    }
}
