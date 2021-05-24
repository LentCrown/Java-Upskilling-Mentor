package org.mentor.dao;

import org.mentor.entity.Answer;
import org.mentor.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll(String source);
}
