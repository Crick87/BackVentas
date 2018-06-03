package com.pythonteam.databases;

import com.pythonteam.dao.CustomerDao;
import com.pythonteam.models.Customer;
import org.postgresql.geometric.PGpoint;

import java.util.List;

public class CustomerHandler implements BaseHandler<Customer,Integer> {

    private static class SingletonHelper{
        private static final CustomerHandler INSTANCE = new CustomerHandler();
    }

    public static CustomerHandler getInstance(){
        return SingletonHelper.INSTANCE;
    }

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
        if (customer.getLatlong() == null)
        {
            customer.setLatlong(new PGpoint(20.541397,-100.816643));
        }
        customer.setId(Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.create(customer.getName(), customer.getPhone(), customer.getEmail(),customer.getLatlong().x,customer.getLatlong().y)));
        //Database.getJdbi().withExtension(CustomerDao.class, dao -> dao.createRoute(1,customer.getId(),));
        return customer;
    }
    }
