package org.mentor.service;

import org.junit.Test;

import org.mentor.config.CSVTestConfig;
import org.mentor.config.ReportTestConfig;
import org.mentor.config.SourcePathConfig;
import org.mentor.config.SourcePathTestConfig;
import org.mentor.dao.CSVFile;
import org.mentor.dao.QuestionDAO;
import org.mentor.entity.Answer;
import org.mentor.entity.User;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

public class ApplicationTest {
    @Mock
    private SourcePathConfig sourcePathConfig;
    @Mock
    private QuestionDAO questionDAO;
    @Mock
    private User user;
    private Application application;

    public ApplicationTest(){
        MockitoAnnotations.openMocks(this);
        this.application = new Application(sourcePathConfig,questionDAO,user);
    }

    /*@Test
    public void testRunSuccess(){
        given(sourcePathConfig.getSource()).willReturn("Example1.csv");
        given(questionDAO.getAnswers(anyString())).willReturn(Arrays.asList(
                new Answer(1,"5"),
                new Answer(2,"Тёплое"),
                new Answer(3, "Северный ледовитый"),
                new Answer(4, "109")
        ));
    }*/

}