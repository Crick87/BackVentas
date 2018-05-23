package com.pythonteam.dao;

import com.pythonteam.models.OrderGet;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface OrderDao {

    @SqlUpdate("INSERT INTO orders(customerId, orderDate, employeeId) VALUES (:customerId,now(),:employeeId);")
    @GetGeneratedKeys("id")
    int create(@Bind("customerId") int customerID,@Bind("employeeId") int employeeId);

    @SqlUpdate("insert into customer_order(orderid, productId, quantity) VALUES (:orderid, :productId, :quantity);")
    int addProduct(@Bind("orderid") int orderId, @Bind("productId") int productID, @Bind("quantity") int quantity);

    @SqlUpdate("delete from orders where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("delete from customer_order where orderId = :id")
    boolean deleteOrder(@Bind("id") int id);

    @SqlUpdate("delete from customer_order where productId = :id")
    boolean deleteProduct(@Bind("id") int id);

    @SqlUpdate("update orders set status = :status where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(OrderGet.class)
    OrderGet updateStatus(@Bind("id") int id, @Bind("status") boolean status);

    @SqlUpdate("update customer_order set quantity = :quantity where productId = :id and orderId = :orderId")
    @GetGeneratedKeys
    @RegisterBeanMapper(OrderGet.class)
    OrderGet updateProduct( @Bind("orderId") int orderId, @Bind("id") int id, @Bind("quantity") int quantity);


}
