package org.mentor.service;

import org.mentor.domain.Question;

import java.util.List;

public interface ICsvParser {
    List<Question> findAll(String source);
}
