package org.mentor.dao;

import org.mentor.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    User findById(Integer id);
    List<User> findAll();
    List<User> findAdmins();
    List<User> findClients();
    void update(User user);
    void delete(User user);

}
