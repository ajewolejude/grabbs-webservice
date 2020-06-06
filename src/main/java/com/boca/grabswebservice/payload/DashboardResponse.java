package com.boca.grabswebservice.payload;

import com.boca.grabswebservice.dom.Dashboard;

public class DashboardResponse {

    private Dashboard dashboard;

    public DashboardResponse(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}



