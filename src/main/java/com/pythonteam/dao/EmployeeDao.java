package com.pythonteam.dao;

import com.pythonteam.models.Employee;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Date;
import java.util.ArrayList;

public interface EmployeeDao {
    @SqlQuery("SELECT * FROM employees")
    @RegisterBeanMapper(Employee.class)
    ArrayList<Employee> list();

    @SqlQuery("SELECT * FROM employees where :id = id")
    @RegisterBeanMapper(Employee.class)
    Employee findOne(@Bind("id") int id);

    @SqlUpdate("INSERT INTO employees(name, paternalName, maternalName, birthday, email, userId) VALUES (:name,:paternalName,:maternalName,:birthday,:userId);")
    boolean create(@Bind("name") String name, @Bind("paternalName") String paternalName, @Bind("maternalName") String maternalName, @Bind("birthday") Date birthday,@Bind("email") String email,@Bind("userId") int userId);

    @SqlUpdate("delete from employees where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlQuery("update employees set name = :name, paternalName = :paternalName, maternalName = :maternalName, birthday = :birthday, email = :email, userId = :userId where id = :id")
    @RegisterBeanMapper(Employee.class)
    Employee update(@Bind("id") int id, @Bind("name") String name, @Bind("paternalName") String paternalName, @Bind("maternalName") String maternalName, @Bind("birthday") Date birthday,@Bind("email") String email,@Bind("userId") int userId);

}
