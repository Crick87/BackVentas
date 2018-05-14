package com.pythonteam.databases;

import com.pythonteam.dao.CustomerDao;
import com.pythonteam.models.Customer;
import com.pythonteam.models.Employee;

import java.util.List;

public class CustomerHandler implements BaseHandler<Customer,Integer> {
    @Override
    public List<Customer> findAll() {
        return Database.getJdbi().withExtension(CustomerDao.class, CustomerDao::list);
    }

    @Override
    public Customer findOne(Integer id) {
        return Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.findOne(id));
    }

    @Override
    public boolean delete(Integer id) {
        return Database.getJdbi().withExtension(CustomerDao.class,dao -> {
            dao.deleteRoute(id);
            return dao.delete(id);
        }
        );
    }

    @Override
    public Customer update(Customer customer) {
        return Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.update(customer.getId(),customer.getName(), customer.getPhone(), customer.getEmail(),customer.getLatlong().x,customer.getLatlong().y));
    }

    @Override
    public Customer create(Customer customer) {
        customer.setId(Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.create(customer.getName(), customer.getPhone(), customer.getEmail(),customer.getLatlong().x,customer.getLatlong().y)));
        //Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.createRoute(1,customer.getId(),));
        return customer;
    }
    }
