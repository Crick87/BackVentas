package com.pythonteam.databases;

import com.pythonteam.dao.EmployeeDao;
import com.pythonteam.models.Employee;

import java.util.List;

public class EmployeeHandler implements BaseHandler<Employee,Integer> {
    @Override
    public List<Employee> findAll() {
        return Database.getJdbi().withExtension(EmployeeDao.class, EmployeeDao::list);
    }

    @Override
    public Employee findOne(Integer id) {
        return Database.getJdbi().withExtension(EmployeeDao.class, dao -> dao.findOne(id));
    }

    @Override
    public boolean delete(Integer id) {
        return Database.getJdbi().withExtension(EmployeeDao.class,dao -> dao.delete(id));
    }

    @Override
    public Employee update(Employee employee) {
        return Database.getJdbi().withExtension(EmployeeDao.class, dao -> dao.update(employee.getId(),employee.getName(), employee.getPaternalName(), employee.getMaternalName(), employee.getBirthday(), employee.getEmail(), employee.getUserId()));
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(Database.getJdbi().withExtension(EmployeeDao.class, dao -> dao.create(employee.getName(), employee.getPaternalName(), employee.getMaternalName(), employee.getBirthday(), employee.getEmail(), employee.getUserId())));
        return employee;
    }
    }
