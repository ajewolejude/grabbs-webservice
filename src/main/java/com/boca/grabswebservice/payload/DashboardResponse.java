package com.boca.grabswebservice.payload;

import com.boca.grabswebservice.dom.Dashboard;

import java.util.ArrayList;
import java.util.List;

public class DashboardResponse {

    private Dashboard dashboard;

    private List<Key> keys;

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public DashboardResponse(Dashboard dashboard) {
        this.dashboard = dashboard;
        List<Key> keys=new ArrayList<>();
        keys.add(new Key("Earning","earning"));
        keys.add(new Key("Truck","truckCount"));
        keys.add(new Key("Driver","driverCount"));
        keys.add(new Key("Mate","mateCount"));
        keys.add(new Key("Trip","tripCount"));
        keys.add(new Key("Completed Trip","completedTripCount"));

        this.keys = keys;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}



