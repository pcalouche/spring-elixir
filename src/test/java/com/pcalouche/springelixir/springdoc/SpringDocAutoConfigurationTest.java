// package com.pcalouche.springelixir.springdoc;
//
// import io.swagger.v3.oas.models.OpenAPI;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// import static org.assertj.core.api.Assertions.assertThat;
//
// @ExtendWith(SpringExtension.class)
// public class SpringDocAutoConfigurationTest {
//
// @Test
// public void testOpenApi() {
// SpringDocProperties springDocProperties = new SpringDocProperties();
// springDocProperties.setDescription("My API Description");
// springDocProperties.setTitle("My API Title");
// springDocProperties.setVersion("1.0.0");
// springDocProperties.setContactName("Philip Calouche");
// springDocProperties.setContactUrl("https://github.com/pcalouche");
//
// SpringDocAutoConfiguration springDocAutoConfiguration = new
// SpringDocAutoConfiguration();
// OpenAPI openAPI = springDocAutoConfiguration.openAPI(springDocProperties);
// assertThat(openAPI.getInfo().getTitle()).isEqualTo(springDocProperties.getTitle());
// assertThat(openAPI.getInfo().getDescription()).isEqualTo(springDocProperties.getDescription());
// assertThat(openAPI.getInfo().getVersion()).isEqualTo(springDocProperties.getVersion());
// assertThat(openAPI.getInfo().getContact().getName()).isEqualTo(springDocProperties.getContactName());
// assertThat(openAPI.getInfo().getContact().getUrl()).isEqualTo(springDocProperties.getContactUrl());
// }
// }
