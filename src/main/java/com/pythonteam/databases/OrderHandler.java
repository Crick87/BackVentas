package com.pythonteam.databases;

import com.pythonteam.dao.OrderDao;
import com.pythonteam.models.Order;
import com.pythonteam.models.Product;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class OrderHandler implements BaseHandler<Order,Integer> {

    String selectAll = "select orderid o_orderid, customerId o_customerid, status o_status, orderdate o_orderdate,quantity p_quantity, p.id p_id, p.name p_name, p.description p_description, price p_price from orders o" +
            "                   join customer_order on o.id = customer_order.orderId" +
            "                   join products p on customer_order.productId = p.id" +
            "                   join productPrices on p.id=productPrices.productId" +
            "                   where date = (SELECT" +
            "                   MAX(date)" +
            "                   from productPrices" +
            "                   where p.id = productPrices.productId and date <= now())";

    String selectOne = selectAll + " and orderid = :id";

    @Override
    public List<Order> findAll() {
        return Database.getJdbi().withHandle(handle ->
           handle.createQuery(selectAll)
                   .registerRowMapper(BeanMapper.factory(Order.class, "o"))
                   .registerRowMapper(BeanMapper.factory(Product.class, "p"))
                   .<Integer, Order>reduceRows(((map, rowView) -> {
                       Order order  = map.computeIfAbsent(
                               rowView.getColumn("o_orderid", Integer.class),
                               id -> rowView.getRow(Order.class));

                       if (rowView.getColumn("p_id", Integer.class) != null) {
                           order.addProduct(rowView.getRow(Product.class));
                       }
                   })).collect(toList()));
    }

    @Override
    public Order findOne(Integer orderid) {
        Optional<Order> orders = Database.getJdbi().withHandle(handle ->
                handle.createQuery(selectOne)
                        .bind("id", orderid)
                        .registerRowMapper(BeanMapper.factory(Order.class, "o"))
                        .registerRowMapper(BeanMapper.factory(Product.class, "p"))
                        .<Integer, Order>reduceRows(((map, rowView) -> {
                            Order order = map.computeIfAbsent(
                                    rowView.getColumn("o_orderid", Integer.class),
                                    id -> rowView.getRow(Order.class));

                            if (rowView.getColumn("p_id", Integer.class) != null) {
                                order.addProduct(rowView.getRow(Product.class));
                            }
                        })).findFirst());

        return orders.get();
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
