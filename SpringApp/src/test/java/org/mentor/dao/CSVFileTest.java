package org.mentor.dao;

import org.junit.Test;
import org.mentor.config.CSVConfig;
import org.mentor.entity.Answer;
import org.mentor.entity.Question;
import org.mentor.service.parser.CsvFileParser;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class CSVFileTest {
    private final String RESOURCE_FILE = "Example1.csv";
    private final String WRONG_PATH = "Example123.csv";

    @Mock
    private CSVConfig csvConfig;
    private CsvFileParser csvFile;

    public CSVFileTest(){
    }

    @Test
    public void testGetQuestions_CorrectPath() {
    }

    @Test
    public void testGetQuestions_WrongPath() {
    }

    @Test
    public void testGetAnswers_CorrectPath() {
    }

    @Test
    public void testGetAnswers_WrongPath() {
    }

    @Test
    public void testReadCSV_CorrectPath() {
    }

    @Test
    public void testReadCSV_WrongPath() {
    }
}