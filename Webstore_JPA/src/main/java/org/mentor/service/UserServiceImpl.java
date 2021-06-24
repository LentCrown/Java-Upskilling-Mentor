package org.mentor.service;

import org.mentor.dao.UserDao;
import org.mentor.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static boolean isFirstUser = true;

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(List<String> userInput) {
        User new_user = new User();
        new_user.setName(userInput.get(2));
        new_user.setSurname(userInput.get(3));
        new_user.setPhone_number(userInput.get(4));
        userDao.save(new_user);
        if (isFirstUser) isFirstUser=false;
    }

    @Override
    public User getById(Integer user_id) {
        return userDao.findById(user_id);
    }
}
