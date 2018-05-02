package com.pythonteam.databases;

import com.pythonteam.dao.OrderDao;
import com.pythonteam.dao.ProductDao;
import com.pythonteam.models.Order;
import com.pythonteam.models.Product;

import java.util.List;

public class OrderHandler implements BaseHandler<Order,Integer> {
    @Override
    public List<Order> findAll() {
        return Database.getJdbi().withExtension(OrderDao.class, OrderDao::list);
    }

    @Override
    public Order findOne(Integer id) {
        return Database.getJdbi().withExtension(OrderDao.class, dao -> dao.findOne(id));
    }

    @Override
    public boolean delete(Integer id) {
        return Database.getJdbi().withExtension(OrderDao.class,dao -> {
            dao.deleteOrder(id);
            return dao.delete(id);
        });
    }

    @Override
    public Order update(Order order) {
        return Database.getJdbi().withExtension(OrderDao.class, dao -> {
            return null;
        });
    }

    @Override
    public Order create(Order order) {
        order.setId(Database.getJdbi().withExtension(OrderDao.class, dao -> dao.create(order.getCustomerId())));
        return order;
    }
    }
