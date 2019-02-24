package com.pcalouche.springelixir.statusendpoint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StatusDto {
    private final String application;
    private final String version;
}
