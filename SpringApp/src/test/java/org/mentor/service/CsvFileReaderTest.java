package org.mentor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mentor.config.CSVConfig;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvFileReaderTest {
    private final String CSV_FILE = "CorrectExample1.csv";
    private final String TEXT_FILE = "Lorem_Ipsum.txt";
    private final String WRONG_PATH = "NoSuchFileInTestResources.csv";

    @Mock private CSVConfig csvConfig;

    @InjectMocks private CsvFileReader csvFileReader;

    @Before
    public void setConfigOperations(){
        BDDMockito.given(csvConfig.getSeparator()).willReturn(';');
        BDDMockito.given(csvConfig.isIgnore_quotations()).willReturn(false);
    }

    @Test
    public void readRawLines_FromExisted_Csv() {
        List<String[]> rawLines = csvFileReader.readRawLines(CSV_FILE);
        assertThat(rawLines).isNotNull();
        assertThat(rawLines.size()).isEqualTo(10);
        assertThat(rawLines.get(0).length).isEqualTo(6);
    }

    @Test
    public void readRawLines_FromExisted_Text() {
        List<String[]> rawLines = csvFileReader.readRawLines(TEXT_FILE);
        assertThat(rawLines).isNotNull();
        assertThat(rawLines.size()).isEqualTo(4);
        assertThat(rawLines.get(0).length).isEqualTo(1);
    }

    @Test()
    public void readRawLines_FromNonExistentFile() {
        List<String[]> questionList = csvFileReader.readRawLines(WRONG_PATH);
        assertThat(questionList).isNull();
    }
}