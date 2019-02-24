package com.pcalouche.springelixir.controllerAdvice;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pcalouche.springelixir.AbstractControllerTest;
import com.pcalouche.springelixir.exception.ExceptionUtils;
import com.pcalouche.springelixir.statusendpoint.StatusController;
import com.pcalouche.springelixir.util.ElixirEndpoints;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StatusController.class)
public class StandardControllerAdviceTest extends AbstractControllerTest {
    @MockBean
    private StatusController statusController;

    @Test
    public void testException() throws Exception {
        RuntimeException runtimeException = new RuntimeException("some random runtime exception");

        MockHttpServletRequest request = MockMvcRequestBuilders.get(ElixirEndpoints.STATUS)
                .contentType(MediaType.APPLICATION_JSON).buildRequest(new MockServletContext());

        ObjectNode expectedObjectNode = (ObjectNode) ExceptionUtils.buildJsonErrorObject(runtimeException, request);
        // Remove timestamp for easier comparision
        expectedObjectNode.remove("timestamp");

        given(statusController.status()).willThrow(runtimeException);

        MvcResult mvcResult = mockMvc.perform(get(ElixirEndpoints.STATUS))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andReturn();

        ObjectNode actualObjectNode = (ObjectNode) objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        // Check timestamp is not null
        assertThat(actualObjectNode.get("timestamp"))
                .isNotNull();

        // Remove timestamp for easier comparision since it will be dynamic anyways
        actualObjectNode.remove("timestamp");

        assertThat(actualObjectNode).isEqualTo(expectedObjectNode);

        assertThat(mvcResult.getResolvedException()).isInstanceOf(Exception.class);
    }
}