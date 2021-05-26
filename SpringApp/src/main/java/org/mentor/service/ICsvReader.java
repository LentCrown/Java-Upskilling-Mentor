package org.mentor.service;

import java.util.List;

public interface ICsvReader {
    List<String[]> readRawLines(String source);
}
