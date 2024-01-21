package com.pcalouche.springelixir;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcalouche.springelixir.controlleradvice.ControllerAdviceAutoConfiguration;
import com.pcalouche.springelixir.statusendpoint.StatusEndpointAutoConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureJson
@ContextConfiguration(classes = { ControllerAdviceAutoConfiguration.class, StatusEndpointAutoConfiguration.class })
@TestPropertySource("classpath:application-test.properties")
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

}
