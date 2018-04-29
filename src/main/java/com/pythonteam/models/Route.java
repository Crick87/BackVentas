package com.pythonteam.models;

import org.postgresql.geometric.PGpoint;

public class Route {
    private int idPath;
    private PGpoint latLong;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getIdPath() {
        return idPath;
    }

    public void setIdPath(int idPath) {
        this.idPath = idPath;
    }

    public PGpoint getLatLong() {
        return latLong;
    }

    public void setLatLong(PGpoint latLong) {
        this.latLong = latLong;
    }
}
