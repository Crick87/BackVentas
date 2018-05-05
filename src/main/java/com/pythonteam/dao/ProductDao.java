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
    @SqlQuery("select id, name, description, price, image from products join productPrices on id=productId" +
            " where date = (SELECT MAX(date) from productPrices" +
            " where id = productId and date <= now())  order by id;")
    @RegisterBeanMapper(Product.class)
    ArrayList<Product> list();

    @SqlQuery("select id, name, description, image, price from products join productPrices on id=productId" +
            " where date = (SELECT" +
            " MAX(date)" +
            " from productPrices" +
            " where id = productId and date <= now()) and id = :id")
    @RegisterBeanMapper(Product.class)
    Product findOne(@Bind("id") int id);


    @SqlUpdate("INSERT INTO products(name, description) VALUES (:name,:description);")
    @GetGeneratedKeys("id")
    int create(@Bind("name") String name, @Bind("description") String description);

    @SqlUpdate("INSERT INTO products(image) VALUES (:image);")
    int createImage(@Bind("image") String image);


    @SqlUpdate("insert into productPrices(productId, date, price) values (:productId,now(),:price);")
    int createPrice(@Bind("productId") int productID, @Bind("price") double price);

    @SqlUpdate("delete from products where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("delete from productPrices where productId = :id")
    boolean deletePrice(@Bind("id") int id);

    @SqlUpdate("update products set name = :name, description = :description where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(Product.class)
    Product update(@Bind("id") int id, @Bind("name") String name, @Bind("description") String description);

}
