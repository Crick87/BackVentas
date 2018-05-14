package com.pythonteam.databases;

import com.pythonteam.dao.UserDao;
import com.pythonteam.models.User;

import java.util.List;

public class UserHandler implements BaseHandler<User,Integer> {
    @Override
    public List<User> findAll() {
        return Database.getJdbi().withExtension(UserDao.class, UserDao::list);
    }

    @Override
    public User findOne(Integer id) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.findOne(id));
    }

    @Override
    public boolean delete(Integer id) {
        return Database.getJdbi().withExtension(UserDao.class, dao-> dao.delete(id));
    }

    @Override
    public User update(User user) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.update(user.getId(), user.getUsername(),user.getPassword()));
    }

    @Override
    public User create(User user) {
        user.setId(Database.getJdbi().withExtension(UserDao.class, dao -> dao.create(user.getUsername(),user.getPassword())));
        return user;
    }

    public User checkPass(User user) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.check(user.getUsername(), user.getPassword()));
    }
}
