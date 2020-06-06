package com.boca.grabswebservice.dom;

import java.math.BigDecimal;

public class Dashboard {

    private BigDecimal earning;
    private Long driverCount;
    private long tripCount;
    private Long completedTripCount;
    private Long truckCount;
    private Long mateCount;

    public BigDecimal getEarning() {
        return earning;
    }

    public void setEarning(BigDecimal earning) {
        this.earning = earning;
    }

    public Long getDriverCount() {
        return driverCount;
    }

    public void setDriverCount(Long driverCount) {
        this.driverCount = driverCount;
    }

    public long getTripCount() {
        return tripCount;
    }

    public void setTripCount(long tripCount) {
        this.tripCount = tripCount;
    }

    public Long getCompletedTripCount() {
        return completedTripCount;
    }

    public void setCompletedTripCount(Long completedTripCount) {
        this.completedTripCount = completedTripCount;
    }

    public Long getTruckCount() {
        return truckCount;
    }

    public void setTruckCount(Long truckCount) {
        this.truckCount = truckCount;
    }

    public Long getMateCount() {
        return mateCount;
    }

    public void setMateCount(Long mateCount) {
        this.mateCount = mateCount;
    }

    public Dashboard() {
    }

    public Dashboard(Long driverCount, Long tripCount, Long completedTripCount, Long truckCount, Long mateCount, BigDecimal earning ) {
        this.earning = earning;
        this.driverCount = driverCount;
        this.tripCount = tripCount;
        this.completedTripCount = completedTripCount;
        this.truckCount = truckCount;
        this.mateCount = mateCount;
    }
}
