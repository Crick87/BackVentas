package com.pythonteam.dao;

import com.pythonteam.models.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;

public interface UserDao {

    @SqlQuery("SELECT * FROM users  order by id")
    @RegisterBeanMapper(User.class)
    ArrayList<User> list();

    @SqlQuery("SELECT * FROM users where :id = id")
    @RegisterBeanMapper(User.class)
    User findOne(@Bind("id") int id);

    @SqlUpdate("INSERT INTO users(username, password) VALUES (:users,:password);")
    @GetGeneratedKeys("id")
    int create(@Bind("users") String username, @Bind("password") String password);

    @SqlUpdate("delete from users where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("update users set username = :username, password = :password where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(User.class)
    User update(@Bind("id") int id, @Bind("username") String username, @Bind("password") String password);


    @SqlQuery("select * from users where username = :username and password = :password")
    @RegisterBeanMapper(User.class)
    User check(@Bind("username") String username, @Bind("password") String password);

}
