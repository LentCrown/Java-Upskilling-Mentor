package org.mentor.service;

import org.mentor.config.SourcePathConfig;
import org.mentor.dao.QuestionDao;
import org.mentor.domain.User;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    @Mock
    private SourcePathConfig sourcePathConfig;
    @Mock
    private QuestionDao questionDAO;
    @Mock
    private User user;
    private SurveyService surveyService;

    public ApplicationTest(){
    }

}