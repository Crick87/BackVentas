package com.pythonteam.dao;

import com.pythonteam.models.Employee;
import com.pythonteam.models.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;

public interface ProductDao {
    @SqlQuery("select id, name, description, image, price from products join productPrices on id=productId\n" +
            "where date = (SELECT\n" +
            "                  MAX(date)\n" +
            "                from productPrices\n" +
            "                where id = productId and date <= now());")
    @RegisterBeanMapper(Product.class)
    ArrayList<Product> list();

    @SqlQuery("select id, name, description, image, price from products join productPrices on id=productId\n" +
            "where date = (SELECT\n" +
            "                  MAX(date)\n" +
            "                from productPrices\n" +
            "                where id = productId and date <= now()); where :id = id")
    @RegisterBeanMapper(Product.class)
    Product findOne(@Bind("id") int id);


    @SqlUpdate("INSERT INTO products(name, description) VALUES (:name,:description);")
    @GetGeneratedKeys("id")
    int create(@Bind("name") String name, @Bind("description") String description);

    @SqlUpdate("insert into productPrices(productId, date, price) values (:productID,now(),:prices);")
    int createPrice(@Bind("productId") int productID, @Bind("price") double price);

    @SqlUpdate("delete from products where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("delete from productPrices where productId = :id")
    boolean deletePrice(@Bind("id") int id);

    @SqlQuery("update products set name = :name, description = :description where id = :id")
    @RegisterBeanMapper(Product.class)
    Product update(@Bind("id") int id, @Bind("name") String name, @Bind("description") String description);

}
