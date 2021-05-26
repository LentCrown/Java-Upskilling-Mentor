package org.mentor.dao;

import org.mentor.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll(String source);
}
