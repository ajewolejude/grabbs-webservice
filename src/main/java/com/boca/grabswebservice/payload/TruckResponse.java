package com.boca.grabswebservice.payload;

import com.boca.grabswebservice.model.Truck;

import java.util.List;

public class TruckResponse {


    private List<Truck> trucks;

    public TruckResponse(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }
}


