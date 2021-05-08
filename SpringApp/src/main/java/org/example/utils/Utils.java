package org.example.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {

    /**
     * @param path absolute path to a file
     * @return Reader object
     * @throws IOException throw error if some error occurred when we opened file
     */
    public static Reader readFile(String path) throws IOException {
        return Files.newBufferedReader(Paths.get(path));
    }

    public static CSVReader getCsvReader(Reader reader, char separator, boolean ignore_quotations){
        CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).withIgnoreQuotations(ignore_quotations).build();
        return new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(csvParser).build();
    }

    public static String getResource(String path){
        try {
            return Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                    .getResource(path)).toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getColumn(List<String[]> rawData, String columnName){
        boolean first = true;
        int column_index=0;
        List<String> column = new ArrayList<>();
        for (String[] row : rawData){
            while (first) {
                for (int i=0; i<row.length; i++){
                    if (row[i].equals(columnName)){
                        column_index = i;
                        first = false;
                        break;
                    }
                }
                continue;
            }
            column.add(row[column_index]);
        }
        return column;
    }
}
