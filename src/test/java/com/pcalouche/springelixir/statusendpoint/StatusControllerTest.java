package com.pcalouche.springelixir.statusendpoint;

import com.pcalouche.springelixir.AbstractControllerTest;
import com.pcalouche.springelixir.util.ElixirEndpoints;
import com.pcalouche.springelixir.util.FileUtils;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StatusController.class)
public class StatusControllerTest extends AbstractControllerTest {

    @Test
    public void testStatus() throws Exception {
        mockMvc.perform(get(ElixirEndpoints.STATUS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("application").value("Spring Elixir"))
                .andExpect(jsonPath("version").value(FileUtils.readClassPathResource("artifact-version.txt")));
    }
}
