package com.pythonteam.databases;

import com.pythonteam.dao.UserDao;
import com.pythonteam.models.Customer;
import com.pythonteam.models.Route;
import com.pythonteam.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
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
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.update(user.getId(), user.getUsername(),user.getPassword(),user.getName(),user.getPaternalName(),user.getMaternalName(),user.getEmail()));
    }

    @Override
    public User create(User user) {
        user.setId(Database.getJdbi().withExtension(UserDao.class, dao -> dao.create(user.getUsername(),BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12)),user.getName(),user.getPaternalName(),user.getMaternalName(),user.getEmail())));
        return user;
    }

    public ArrayList<Route> findRoute(int id) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.findRoutes(id));
    }

    public User checkPass(User user) {
        User u = Database.getJdbi().withExtension(UserDao.class, dao -> dao.check(user.getUsername()));
        if (BCrypt.checkpw(user.getPassword(), u.getPassword()))
            return u;
        else
            return null;
    }

    public ArrayList<Route> findRoutes() {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.findAllRoutes());
    }

    public ArrayList<Customer> readCustomers() {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.readCustomers());
    }

    public boolean delRoute(int idEmployee, int idCustomer) {
        return Database.getJdbi().withExtension(UserDao.class, dao -> dao.deleteRoute(idEmployee,idCustomer));
    }

    public Route createRoute(Route route) {
        Database.getJdbi().withExtension(UserDao.class, dao-> dao.createRoute(route.getIdRoute(),route.getIdCustomer(),route.getIdEmployee()) );
        return route;
    }

    public Route updateRoute(Route route) {
        Database.getJdbi().withExtension(UserDao.class, dao-> dao.updateRoute(route.getIdRoute(),route.getIdCustomer(),route.getIdEmployee()) );
        return route;
    }
}
