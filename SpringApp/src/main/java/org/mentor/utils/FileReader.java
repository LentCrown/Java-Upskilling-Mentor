package org.mentor.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Component
public class FileReader {

    public static Reader readFile(String path) throws IOException {
        return Files.newBufferedReader(Paths.get(path));
    }

    public static String getResource(String path){
        try {
            return Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                    .getResource(path)).toURI()).toString();
        } catch (URISyntaxException | NullPointerException e) {
            return null;
        }
    }
}
