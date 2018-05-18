package com.pythonteam.dao;

import com.pythonteam.models.Route;
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

    @SqlUpdate("INSERT INTO users(username, password, name, paternalName, maternalName, email) VALUES (:users,:password,:name,:paternalName,:maternalName,:email);")
    @GetGeneratedKeys("id")
    int create(@Bind("users") String username, @Bind("password") String password, @Bind("name") String name, @Bind("paternalName") String paternalName, @Bind("maternalName") String maternalName, @Bind("email") String email);

    @SqlUpdate("delete from users where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("update users set username = :username, password = :password, name = :name, paternalName = :paternalName, maternalName = :maternalName, email = :email  where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(User.class)
    User update(@Bind("id") int id, @Bind("username") String username, @Bind("password") String password,@Bind("name") String name, @Bind("paternalName") String paternalName, @Bind("maternalName") String maternalName, @Bind("email") String email);


    @SqlQuery("select password from users where username = :username")
    @RegisterBeanMapper(User.class)
    User check(@Bind("username") String username);

    @SqlQuery("select idPath, latlong from users e join routes join customers c on routes.idCustomer = c.id on e.id = routes.idEmployee where idEmployee = :id;")
    @RegisterBeanMapper(Route.class)
    ArrayList<Route>findRoutes(@Bind("id") int id);

}
