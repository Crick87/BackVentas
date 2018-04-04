package com.pythonteam.databases;

import com.pythonteam.dao.TokenDao;
import com.pythonteam.models.Token;
import com.pythonteam.models.User;
import com.pythonteam.util.Hash;

import java.sql.SQLException;
import java.util.List;

public class TokenHandler implements BaseHandler<Token>{
    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public Token findOne(int id) {
        return null;
    }

    public Token findOne(String token) {
        return Database.getJdbi().withExtension(TokenDao.class, dao-> dao.findOne(token));
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public Token update(Token token) {
        return null;
    }

    @Override
    public boolean create(Token token) {
        return false;
    }

    public String create(User user) throws SQLException {
        if (new UserHandler().checkPass(user) != null)
        {
            String tokenHash = new Hash().encriptar(user.getUsername(),user.getPassword());
            Database.getJdbi().withExtension(TokenDao.class, dao -> dao.create(tokenHash));
            return "{\"token\": \""+ tokenHash + "\"}";
        }
        throw new SQLException("eres p");
    }

}
