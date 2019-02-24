package com.pcalouche.springelixir.statusendpoint;

import com.pcalouche.springelixir.util.ElixirEndpoints;
import com.pcalouche.springelixir.util.FileUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StatusController {

    private final StatusEndpointProperties statusEndpointProperties;

    public StatusController(StatusEndpointProperties statusEndpointProperties) {
        this.statusEndpointProperties = statusEndpointProperties;
    }

    @ApiOperation(value = "Status check endpoint for application")
    @GetMapping(ElixirEndpoints.STATUS)
    public StatusDto status() {
        try {
            return new StatusDto(statusEndpointProperties.getAppName(), FileUtils.readClassPathResource("artifact-version.txt"));
        } catch (IOException e) {
            return new StatusDto(statusEndpointProperties.getAppName(), "Unknown: Run Gradle build to generate artifact-version.txt");
        }
    }
}
