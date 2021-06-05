package org.mentor.dao;

import org.mentor.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    User findById(Integer id);
    List<User> findAll();
    void delete(User user);

}
