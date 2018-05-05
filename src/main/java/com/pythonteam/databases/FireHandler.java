package com.pythonteam.databases;

import com.pythonteam.dao.FireDao;
import com.pythonteam.dao.UserDao;
import com.pythonteam.models.User;

import java.util.List;

public class FireHandler  {

    public int add(String token) {
        return Database.getJdbi().withExtension(FireDao.class, dao -> dao.add(token));
    }
}
