package com.pythonteam.databases;

import com.pythonteam.dao.ProductDao;
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
        return Database.getJdbi().withExtension(ProductDao.class,dao -> {
            dao.deletePrice(id);
            return dao.delete(id);
        });
    }

    @Override
    public Product update(Product product) {
        return Database.getJdbi().withExtension(ProductDao.class, dao -> {
            Product p = dao.findOne(product.getId());
            if (p.getPrice() != product.getPrice())
                dao.createPrice(product.getId(),product.getPrice());
            else if(p.getStock() != product.getStock())
            {
                int diff = product.getStock() - p.getStock();
                return dao.update(product.getId(),product.getName(), product.getDescription(), product.getStock(),p.getAvailable()+diff);
            }
            return dao.update(product.getId(),product.getName(), product.getDescription(), product.getStock(), product.getAvailable());
        });
    }

    @Override
    public Product create(Product product) {
        product.setId(Database.getJdbi().withExtension(ProductDao.class, dao -> dao.create(product.getName(), product.getDescription(),product.getStock())));
        Database.getJdbi().withExtension(ProductDao.class, dao -> dao.createPrice(product.getId(), product.getPrice()));
        return product;
    }
    }
