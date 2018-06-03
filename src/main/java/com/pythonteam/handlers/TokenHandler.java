package com.pythonteam.handlers;

import com.pythonteam.dao.TokenDao;
import com.pythonteam.database.Database;
import com.pythonteam.models.Token;
import com.pythonteam.models.User;
import com.pythonteam.util.Hash;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TokenHandler implements BaseHandler<User,String>{

    private static class SingletonHelper{
        private static final TokenHandler INSTANCE = new TokenHandler();
    }

    public static TokenHandler getInstance(){
        return SingletonHelper.INSTANCE;
    }

    private static final long ttl = TimeUnit.DAYS.toMillis(120);
    @Override
    public List<User> findAll() {
        return Database.getJdbi().withExtension(TokenDao.class, TokenDao::list);
    }

    @Override
    public User findOne(String username) {
        try {
            User user = new UserHandler().findOne( new UserHandler().findByToken(username).getUserid());
            if (user == null){
                return null;
            }
            return user ;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


    public boolean delete(int id, String token) {
        return Database.getJdbi().withExtension(TokenDao.class, dao -> dao.delete(id,token));
    }

    @Override
    public User update(User token) {
        return null;
    }

    @Override
    public User create(User user) throws SQLException {
        user = new UserHandler().checkPass(user);
        if (user != null)
        {
            User finalUser1 = user;
            Token tok = Database.getJdbi().withExtension(TokenDao.class, dao -> dao.findOne(finalUser1.getId()));
            if(tok != null)
            {
                user.setToken(tok.getToken());
                return user;
            }
            else {
                String token = Hash.hash.encriptar(user.getUsername());
                user.setToken(token);
                User finalUser = user;
                Database.getJdbi().withExtension(TokenDao.class, dao -> dao.create(token, finalUser.getId()));
                return user;
            }
        }
        throw new SQLException("eres p");
    }

}
