package org.example.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * @param path absolute path to a file
     * @return Reader
     * @throws IOException throw error if some error occurred when we opened file
     */
    public static Reader readFile(String path) throws IOException {
        return Files.newBufferedReader(Paths.get(path));
    }

    public static CSVReader getCsvReader(Reader reader, char separator, boolean ignore_quotations){
        CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).withIgnoreQuotations(ignore_quotations).build();
        return new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(csvParser).build();
    }

}
