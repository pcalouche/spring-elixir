package com.pcalouche.springelixir.statusendpoint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StatusDto {
    private final String appName;
    private final String version;
}
