package com.pcalouche.springelixir.controllerAdvice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pcalouche.springelixir.AbstractControllerTest;
import com.pcalouche.springelixir.exception.ExceptionUtils;
import com.pcalouche.springelixir.exception.JsonExceptionResponse;
import com.pcalouche.springelixir.statusendpoint.StatusController;
import com.pcalouche.springelixir.util.ElixirEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = StatusController.class)
public class StandardControllerAdviceTest extends AbstractControllerTest {

	@MockBean
	private StatusController statusController;

	@Test
	public void testException() throws Exception {
		long currentTimeInMillis = System.currentTimeMillis();
		RuntimeException runtimeException = new RuntimeException("some random runtime exception");

		MockHttpServletRequest request = MockMvcRequestBuilders.get(ElixirEndpoints.STATUS)
			.contentType(MediaType.APPLICATION_JSON)
			.buildRequest(new MockServletContext());

		JsonExceptionResponse expectedJsonExceptionResponse = ExceptionUtils.buildJsonErrorResponse(runtimeException,
				request);
		// Remove timestamp for easier comparison
		expectedJsonExceptionResponse.setTimestamp(0);

		given(statusController.status()).willThrow(runtimeException);

		MvcResult mvcResult = mockMvc.perform(get(ElixirEndpoints.STATUS))
			.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
			.andReturn();

		JsonExceptionResponse actualJsonExceptionResponse = objectMapper
			.readValue(mvcResult.getResponse().getContentAsString(), JsonExceptionResponse.class);
		// Check timestamp is greater than current time
		assertThat(actualJsonExceptionResponse.getTimestamp() >= currentTimeInMillis);

		// Remove timestamp for easier comparison since it will be dynamic anyways
		actualJsonExceptionResponse.setTimestamp(0);

		assertThat(actualJsonExceptionResponse).isEqualTo(expectedJsonExceptionResponse);

		assertThat(mvcResult.getResolvedException()).isInstanceOf(Exception.class);
	}

}