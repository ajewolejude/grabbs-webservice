package com.boca.grabswebservice.payload;

import com.boca.grabswebservice.dom.Dashboard;
import com.boca.grabswebservice.model.Trip;

import java.util.List;

public class JWTLoginSucessReponse {
    private Long id;
    private boolean success;
    private String token;
    private String role;
    private String lastname;
    private String firstname;
    private String email;
    private Dashboard dashboard;
    private List<Trip> trips;

    public Dashboard getDashboard() {
        return dashboard;
    }


    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public JWTLoginSucessReponse(boolean success, String token, String role, String email, String firstname,  String lastname,String status, Long id, Dashboard dashboard,List<Trip> trips ) {
        this.success = success;
        this.token = token;
        this.id = id;
        this.role = role;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.status = status;
        this.dashboard = dashboard;
        this.trips = trips;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "id=" + id +
                ", success=" + success +
                ", token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
