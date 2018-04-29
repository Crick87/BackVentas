package com.pythonteam.databases;

import com.pythonteam.dao.EmployeeDao;
import com.pythonteam.dao.ProductDao;
import com.pythonteam.models.Employee;
import com.pythonteam.models.Product;

import java.util.List;

public class ProductHandler implements BaseHandler<Product,Integer> {
    @Override
    public List<Product> findAll() {
        return Database.getJdbi().withExtension(ProductDao.class, ProductDao::list);
    }

    @Override
    public Product findOne(Integer id) {
        return Database.getJdbi().withExtension(ProductDao.class, dao -> dao.findOne(id));
    }

    @Override
    public boolean delete(Integer id) {
        return Database.getJdbi().withExtension(ProductDao.class,dao -> dao.delete(id));
    }

    @Override
    public boolean update(Product employee) {
        return Database.getJdbi().withExtension(ProductDao.class, dao -> dao.update(employee.getId(),employee.getName(), employee.getDescription()));
    }

    @Override
    public Product create(Product employee) {
        employee.setId(Database.getJdbi().withExtension(ProductDao.class, dao -> dao.create(employee.getName(), employee.getDescription())));
        return employee;
    }
    }
