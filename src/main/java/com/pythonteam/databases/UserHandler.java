package com.pythonteam.databases;

import com.pythonteam.dao.UserDao;
import com.pythonteam.models.User;

import java.util.List;

public class UserHandler implements BaseHandler<User> {
    @Override
    public List<User> findAll() {
        return Database.getJdbi().withExtension(UserDao.class, UserDao::list);
    }

    @Override
    public User findOne(int id) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.findOne(id));
    }

    @Override
    public boolean delete(int id) {
        return Database.getJdbi().withExtension(UserDao.class, dao-> dao.delete(id));
    }

    @Override
    public User update(User user) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.update(user.getId(), user.getUsername(),user.getPassword()));
    }

    @Override
    public boolean create(User user) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.create(user.getUsername(),user.getPassword()));
    }

    public User checkPass(User user) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.check(user.getUsername(), user.getPassword()));
    }
}
