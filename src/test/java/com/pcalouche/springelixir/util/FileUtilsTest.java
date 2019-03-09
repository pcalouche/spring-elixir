package com.pcalouche.springelixir.util;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

    @Test
    public void readClassPathResource() throws IOException {
        String expectedVersion = "1.1.0";
        assertThat(FileUtils.readClassPathResource("artifact-version.txt")).isEqualTo(expectedVersion);
    }

}
