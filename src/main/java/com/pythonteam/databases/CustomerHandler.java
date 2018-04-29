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
        return Database.getJdbi().withExtension(CustomerDao.class,dao -> dao.delete(id));
    }

    @Override
    public boolean update(Customer customer) {
        return Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.update(customer.getId(),customer.getName(), customer.getPhone(), customer.getEmail(),customer.getLatlong()));
    }

    @Override
    public Customer create(Customer customer) {
        customer.setId(Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.create(customer.getName(), customer.getPhone(), customer.getEmail(),customer.getLatlong())));
        return customer;
    }
    }
