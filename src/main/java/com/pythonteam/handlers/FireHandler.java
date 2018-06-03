package com.pythonteam.handlers;

import com.pythonteam.dao.FireDao;
import com.pythonteam.database.Database;
import com.pythonteam.models.Token;

public class FireHandler  {

    public boolean add(Token token) {
        return Database.getJdbi().withExtension(FireDao.class, dao -> dao.add(token.getToken()));
    }
}
