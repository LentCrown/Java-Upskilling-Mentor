package org.mentor.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mentor.utils.Parser.*;
import static org.mentor.utils.FileReader.*;



public class ParserTest {
    private final String RESOURCE_FILE = getResource("Example1.csv");
    private final String TXT_FILE = getResource("Lorem_Ipsum.txt");
    private final char SEPARATOR = ';';
    private final boolean IGNORE_QUOTATIONS = true;
    private final List<String[]> rawData = Arrays.asList(
            new String[]{"Question","Answer"},
            new String[]{"Question1","Answer1"},
            new String[]{"Question2","Answer2"}
    );

    @Test
    public void testGetCsvReaderSuccess() throws IOException, CsvException {
        Reader reader = readFile(RESOURCE_FILE);
        CSVReader csvReader = getCsvReader(reader,SEPARATOR, IGNORE_QUOTATIONS);
        assertThat(csvReader).isNotNull();
        assertThat(csvReader.readAll()).isNotNull();
    }

    @Test
    public void testGetCsvReaderSuccessProcessSimpleText() throws IOException, CsvException {
        Reader reader = readFile(TXT_FILE);
        CSVReader csvReader = getCsvReader(reader,SEPARATOR, IGNORE_QUOTATIONS);
        assertThat(csvReader).isNotNull();
        assertThat(csvReader.readAll()).isNotNull();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetCsvReaderFailedReaderIsNull() throws IOException, CsvException {
        Reader reader = null;
        CSVReader csvReader = Parser.getCsvReader(reader,SEPARATOR, IGNORE_QUOTATIONS);
        assertThat(csvReader).isNotNull();
        assertThat(csvReader.readAll()).isNull();
    }

    @Test
    public void testGetColumnSuccessful() {
        List<String> questionList = Parser.getColumn(rawData,"Question");
        assertThat(questionList).isNotNull();
        assertThat(questionList.size()).isEqualTo(3);
        assertThat(questionList.get(0)).isEqualTo("Question");
    }
}