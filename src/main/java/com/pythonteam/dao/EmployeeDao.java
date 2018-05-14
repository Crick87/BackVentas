package com.pythonteam.dao;

import com.pythonteam.models.Employee;
import com.pythonteam.models.Route;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;

public interface EmployeeDao {
    @SqlQuery("SELECT * FROM employees  order by id")
    @RegisterBeanMapper(Employee.class)
    ArrayList<Employee> list();

    @SqlQuery("SELECT * FROM employees where :id = id")
    @RegisterBeanMapper(Employee.class)
    Employee findOne(@Bind("id") int id);

    @SqlQuery("select idPath, latlong from employees e join routes join customers c on routes.idCustomer = c.id on e.id = routes.idEmployee where idEmployee = :id;")
    @RegisterBeanMapper(Route.class)
    ArrayList<Route>findRoutes(@Bind("id") int id);

    @SqlUpdate("INSERT INTO employees(name, paternalName, maternalName, birthday, email, userId) VALUES (:name,:paternalName,:maternalName,:birthday,:userId);")
    @GetGeneratedKeys("id")
    int create(@Bind("name") String name, @Bind("paternalName") String paternalName, @Bind("maternalName") String maternalName, @Bind("email") String email, @Bind("userId") int userId);

    @SqlUpdate("delete from employees where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("update employees set name = :name, paternalName = :paternalName, maternalName = :maternalName, birthday = :birthday, email = :email, userId = :userId where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(Employee.class)
    Employee update(@Bind("id") int id, @Bind("name") String name, @Bind("paternalName") String paternalName, @Bind("maternalName") String maternalName, @Bind("email") String email,@Bind("userId") int userId);

}
