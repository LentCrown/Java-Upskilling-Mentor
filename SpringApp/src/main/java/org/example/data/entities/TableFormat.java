package org.example.data.entities;

import java.util.List;

interface TableFormat {
    List<String[]> readRawLines();
    List<String> readColumn(String colName);
    String read();
}
