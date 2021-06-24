package org.mentor.service;

import org.mentor.model.User;

import java.util.List;

public interface UserService {
    void create(List<String> input);
    User getById(Integer user_id);
}
