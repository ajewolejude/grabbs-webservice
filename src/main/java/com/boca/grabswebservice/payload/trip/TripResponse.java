package com.boca.grabswebservice.payload.trip;

import com.boca.grabswebservice.dom.Dashboard;
import com.boca.grabswebservice.model.Trip;

import java.util.List;

public class TripResponse {


    private Dashboard dashboard;
    private List<Trip> trips;

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public TripResponse(Dashboard dashboard, List<Trip> trips) {
        this.dashboard = dashboard;
        this.trips = trips;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
