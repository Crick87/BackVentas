package com.pythonteam.models;

import org.postgresql.geometric.PGpoint;

public class Route {
    private int idRoute;
    private PGpoint latLong;

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public PGpoint getLatLong() {
        return latLong;
    }

    public void setLatLong(PGpoint latLong) {
        this.latLong = latLong;
    }
}
