package com.pythonteam.databases;

import com.pythonteam.dao.FireDao;
import com.pythonteam.dao.TokenDao;
import com.pythonteam.dao.UserDao;
import com.pythonteam.models.Token;
import com.pythonteam.models.User;

import java.util.List;

public class FireHandler  {

    public boolean add(Token token) {
        return Database.getJdbi().withExtension(TokenDao.class, dao -> dao.create(token.getToken()));
    }
}
