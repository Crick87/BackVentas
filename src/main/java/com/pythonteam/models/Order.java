package com.pythonteam.models;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    @ColumnName("orderid")
    private int orderid;

    @ColumnName("customerid")
    private int customerId;

    @ColumnName("employeeId")
    private int employeeId;

    @ColumnName("status")
    private boolean status;

    @ColumnName("orderdate")
    private LocalDate orderdate;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getOrderId() {
        return orderid;
    }

    public void setOrderId(int id) {
        this.orderid = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderdate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderdate = orderDate;
    }

    public void addProduct(Product product)
    {
        productList.add(product);
    }
}
