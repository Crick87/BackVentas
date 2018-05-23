package com.pythonteam.models;

import java.util.ArrayList;

public class OrderGet {
    private int orderid;
    private int customerId;
    private int employeeId;
    private boolean status;
    private ArrayList<Product> productList = new ArrayList<>();;

    public int getEmployeeId() {
        return employeeId;
    }

    public void addProduct(Product product)
    {
        productList.add(product);
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public int getOrderId() {
        return orderid;
    }

    public void setOrderId(int orderid) {
        this.orderid = orderid;
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
}
