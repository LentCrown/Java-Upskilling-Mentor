package org.mentor.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mentor.config.CSVConfig;
import org.mentor.domain.Question;
import org.mentor.service.parser.CsvFileParser;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvFileParserTest {
    private final String RESOURCE_FILE = "Example1.csv";
    private final String WRONG_PATH = "Example123.csv";

    @Mock private CSVConfig csvConfig;

    @InjectMocks private CsvFileParser csvFileParser;

    @Before
    public void setConfigOperations(){
        BDDMockito.given(csvConfig.getSeparator()).willReturn(';');
        BDDMockito.given(csvConfig.isIgnore_quotations()).willReturn(false);
    }

    @Test()
    public void CsvFileParser_findAll_CorrectPathQuestionWithChoices_Success() {
        List<Question> questionList = csvFileParser.findAll(RESOURCE_FILE);
        assertThat(questionList).isNotNull();
        assertThat(questionList.size()).isEqualTo(10);
        assertThat(questionList.get(0).getChoiceList().size()).isEqualTo(4);
        assertThat(questionList.get(0).getChoiceList().get(0).getAnswer()).isEqualTo("1");
        assertThat(questionList.get(0).getCorrect_answer().getAnswer()).isEqualTo("4");
    }

    @Test
    public void CsvFileParser_findAll_CorrectPathQuestion_Success() {
        List<Question> questionList = csvFileParser.findAll(RESOURCE_FILE);
        assertThat(questionList).isNotNull();
        assertThat(questionList.size()).isEqualTo(10);
        assertThat(questionList.get(1).getChoiceList().size()).isEqualTo(0);
        assertThat(questionList.get(1).getCorrect_answer().getAnswer()).isEqualTo("Тихий");
    }

    @Test
    public void CsvFileParser_findAll_Failure() {
        List<Question> questionList = csvFileParser.findAll(WRONG_PATH);
        assertThat(questionList).isNull();
    }
}