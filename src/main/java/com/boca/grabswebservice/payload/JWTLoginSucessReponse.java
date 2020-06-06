package com.boca.grabswebservice.payload;

public class JWTLoginSucessReponse {
    private Long id;
    private boolean success;
    private String token;
    private String role;
    private String lastname;
    private String firstname;
    private String email;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public JWTLoginSucessReponse(boolean success, String token, String role, String email, String firstname,  String lastname,String status, Long id) {
        this.success = success;
        this.token = token;
        this.id = id;
        this.role = role;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.status = status;
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
        return "TripResponse{" +
                "id=" + id +
                ", success=" + success +
                ", token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
