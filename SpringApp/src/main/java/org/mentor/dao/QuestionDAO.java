package org.mentor.dao;

import org.mentor.entity.Answer;
import org.mentor.entity.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question> getQuestions(String source);
    List<Answer> getAnswers(String source);
}
