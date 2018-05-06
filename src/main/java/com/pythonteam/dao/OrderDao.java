package com.pythonteam.dao;

import com.pythonteam.models.Order;
import com.pythonteam.models.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterJoinRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;

public interface OrderDao {

    @SqlUpdate("INSERT INTO orders(customerId, orderDate) VALUES (:customerId,now());")
    @GetGeneratedKeys("id")
    int create(@Bind("customerId") int customerID);

    @SqlUpdate("insert into customer_order(orderId, productId, quantity) VALUES (:orderId, :productId, :quantity);")
    int addProduct(@Bind(":orderId") int orderId, @Bind("productId") int productID, @Bind("quantity") int quantity);

    @SqlUpdate("delete from orders where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("delete from customer_order where orderId = :id")
    boolean deleteOrder(@Bind("id") int id);


    // TODO
    @SqlUpdate("update products set name = :name, description = :description where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(Order.class)
    Order update(@Bind("id") int id, @Bind("name") String name, @Bind("description") String description);

}
