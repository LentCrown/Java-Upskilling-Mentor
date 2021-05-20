package org.mentor.dao;

import org.junit.After;
import org.junit.Test;
import org.mentor.config.CSVConfig;
import org.mentor.entity.Answer;
import org.mentor.entity.Question;
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
    private CSVFile csvFile;

    public CSVFileTest(){
        MockitoAnnotations.openMocks(this);
        this.csvFile = new CSVFile(csvConfig);
    }

    @Test
    public void testGetQuestions_CorrectPath() {
        given(csvConfig.getSeparator()).willReturn(';');
        given(csvConfig.isIgnore_quotations()).willReturn(false);

        List<Question> questionList = csvFile.getQuestions(RESOURCE_FILE);

        assertThat(questionList).isNotNull();
        assertThat(questionList.size()).isEqualTo(10);
        assertThat(questionList.get(0).getTitle()).isEqualTo("Сколько океанов на Земле");
    }

    @Test
    public void testGetQuestions_WrongPath() {
        given(csvConfig.getSeparator()).willReturn(';');
        given(csvConfig.isIgnore_quotations()).willReturn(false);

        List<Question> questionList = csvFile.getQuestions(WRONG_PATH);

        assertThat(questionList).isNull();
    }

    @Test
    public void testGetAnswers_CorrectPath() {
        given(csvConfig.getSeparator()).willReturn(';');
        given(csvConfig.isIgnore_quotations()).willReturn(false);

        List<Answer> answerList = csvFile.getAnswers(RESOURCE_FILE);

        assertThat(answerList).isNotNull();
        assertThat(answerList.size()).isEqualTo(10);
        assertThat(answerList.get(0).getRight_answer()).isEqualTo("5");
    }

    @Test
    public void testGetAnswers_WrongPath() {
        given(csvConfig.getSeparator()).willReturn(';');
        given(csvConfig.isIgnore_quotations()).willReturn(false);

        List<Answer> answerList = csvFile.getAnswers(WRONG_PATH);

        assertThat(answerList).isNull();
    }

    @Test
    public void testReadCSV_CorrectPath() {
        given(csvConfig.getSeparator()).willReturn(';');
        given(csvConfig.isIgnore_quotations()).willReturn(false);

        List<String[]> data = csvFile.readCSV(RESOURCE_FILE);

        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(11);
        assertThat(data.get(0).length).isEqualTo(2);
        assertThat(data.get(0)[0]).isEqualTo("Question");
    }

    @Test
    public void testReadCSV_WrongPath() {
        given(csvConfig.getSeparator()).willReturn(';');
        given(csvConfig.isIgnore_quotations()).willReturn(false);

        List<String[]> data = csvFile.readCSV(WRONG_PATH);

        assertThat(data).isNull();
    }
}