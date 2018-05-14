package com.pythonteam.models;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Product {
    @ColumnName("id")
    private int id;

    @ColumnName("name")
    private String name;
    @ColumnName("description")
    private String description;
    @ColumnName("price")
    private double price;
    private byte[] image;

    public double getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
