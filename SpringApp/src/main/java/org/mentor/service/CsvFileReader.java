package org.mentor.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.mentor.config.CSVConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvFileReader implements ICsvReader {
    private final CSVConfig csvConfig;

    public CsvFileReader(CSVConfig config){
        this.csvConfig = config;
    }

    @Override
    public List<String[]> readRawLines(String source){
        ClassPathResource csvResource = new ClassPathResource(source);
        if (csvResource.exists()) {
            try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(csvResource.getInputStream()))
                    .withSkipLines(1)
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(csvConfig.getSeparator())
                            .withIgnoreQuotations(csvConfig.isIgnore_quotations())
                            .build())
                    .build()) {
                return new ArrayList<>(reader.readAll());
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
