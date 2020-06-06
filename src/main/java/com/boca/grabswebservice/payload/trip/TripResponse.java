package com.boca.grabswebservice.payload.trip;

import com.boca.grabswebservice.model.Trip;

import java.util.List;

public class TripResponse {

    private List<Trip> trips;

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public TripResponse(List<Trip> trips) {
        this.trips = trips;
    }
}
