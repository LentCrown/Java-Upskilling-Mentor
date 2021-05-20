package org.mentor.utils;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mentor.utils.FileReader.*;

public class FileReaderTest {
    private final String RESOURCE_FILE = "Example1.csv";
    private final String WRONG_PATH = "Example123.csv";

    @Test
    public void testGetResourceSuccessFileExists() {
        String resource_path = getResource(RESOURCE_FILE);
        assertThat(resource_path).isNotEmpty();
        assertThat(resource_path).containsPattern(Pattern.compile("C:\\.*"));
    }

    @Test
    public void testGetResourceFailureNoSuchFile() {
        String resource_path = getResource(WRONG_PATH);
        assertThat(resource_path).isNull();
    }

    @Test
    public void testReadFileSuccess() throws IOException {
        Reader reader = readFile(getResource(RESOURCE_FILE));
        assertThat(reader.read()).isNotNull();
    }
}