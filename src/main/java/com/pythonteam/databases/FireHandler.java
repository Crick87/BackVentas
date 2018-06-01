package com.pythonteam.databases;

import com.pythonteam.dao.FireDao;
import com.pythonteam.models.Token;

public class FireHandler  {

    public boolean add(Token token) {
        return Database.getJdbi().withExtension(FireDao.class, dao -> dao.add(token.getToken()));
    }
}
