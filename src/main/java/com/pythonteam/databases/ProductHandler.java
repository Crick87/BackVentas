package com.pythonteam.databases;

import com.pythonteam.dao.EmployeeDao;
import com.pythonteam.dao.ProductDao;
import com.pythonteam.models.Employee;
import com.pythonteam.models.Product;

import java.nio.file.Path;
import java.util.List;

public class ProductHandler implements BaseHandler<Product,Integer> {
    public static void loadImage(String path, int id) {
        Database.getJdbi().withExtension(ProductDao.class, dao -> dao.createImage(path,id));
    }

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
        return Database.getJdbi().withExtension(ProductDao.class,dao -> {
            dao.deletePrice(id);
            return dao.delete(id);
        });
    }

    @Override
    public Product update(Product product) {
        return Database.getJdbi().withExtension(ProductDao.class, dao -> {
            dao.createPrice(product.getId(),product.getPrice());
            return dao.update(product.getId(),product.getName(), product.getDescription());
        });
    }

    @Override
    public Product create(Product product) {
        product.setId(Database.getJdbi().withExtension(ProductDao.class, dao -> dao.create(product.getName(), product.getDescription())));
        Database.getJdbi().withExtension(ProductDao.class, dao -> dao.createPrice(product.getId(), product.getPrice()));
        return product;
    }
    }
