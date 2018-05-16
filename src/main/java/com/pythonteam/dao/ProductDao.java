package com.pythonteam.dao;

import com.pythonteam.models.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.ArrayList;

public interface ProductDao {
    @SqlQuery("select id, name, description, price, stock from products join productPrices on id=productId" +
            " where date = (SELECT MAX(date) from productPrices" +
            " where id = productId and date <= now()) order by id;")
    @RegisterBeanMapper(Product.class)
    ArrayList<Product> list();

    @SqlQuery("select id, name, description, price, stock from products join productPrices on id=productId" +
            " where date = (SELECT" +
            " MAX(date)" +
            " from productPrices" +
            " where id = productId and date <= now()) and id = :id")
    @RegisterBeanMapper(Product.class)
    Product findOne(@Bind("id") int id);


    @SqlUpdate("INSERT INTO products(name, description, stock) VALUES (:name,:description,:stock);")
    @GetGeneratedKeys("id")
    int create(@Bind("name") String name, @Bind("description") String description, @Bind("stock") int stock);

    @SqlUpdate("update products set image = :image where id = :id;")
    int createImage(@Bind("image") String image, @Bind("id") int id);


    @SqlUpdate("insert into productPrices(productId, date, price) values (:productId,now(),:price);")
    int createPrice(@Bind("productId") int productID, @Bind("price") double price);

    @SqlUpdate("delete from products where id = :id")
    boolean delete(@Bind("id") int id);

    @SqlUpdate("delete from productPrices where productId = :id")
    boolean deletePrice(@Bind("id") int id);

    @SqlUpdate("update products set name = :name, description = :description, stock = :stock where id = :id")
    @GetGeneratedKeys
    @RegisterBeanMapper(Product.class)
    Product update(@Bind("id") int id, @Bind("name") String name, @Bind("description") String description, @Bind("stock") int stock);

}
