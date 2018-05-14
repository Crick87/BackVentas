package com.pythonteam.models;

import org.postgresql.geometric.PGpoint;

public class Route {
    private int idPath;
    private PGpoint latLong;

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
