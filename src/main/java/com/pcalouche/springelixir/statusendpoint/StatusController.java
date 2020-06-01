package com.pcalouche.springelixir.statusendpoint;

import com.pcalouche.springelixir.util.ElixirEndpoints;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    private final StatusEndpointProperties statusEndpointProperties;

    public StatusController(StatusEndpointProperties statusEndpointProperties) {
        this.statusEndpointProperties = statusEndpointProperties;
    }

    @Operation(description = "Status check endpoint for application")
    @GetMapping(ElixirEndpoints.STATUS)
    public StatusDto status() {
        return new StatusDto(statusEndpointProperties.getAppName(), statusEndpointProperties.getVersion());
    }
}
