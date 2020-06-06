package com.boca.grabswebservice.model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "merchant")
@Where(clause="deleted_by = 0")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Phone number cannot be blank")
    @Column(name = "phone_number_one")
    private String phone_number_one;


    @Column(name = "phone_number_two")
    private String phone_number_two;


    @NotNull
    @NotEmpty(message = "Address cannot be blank")
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at", columnDefinition = "int default 0")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="merchant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TruckRequest> truckRequest;

    public Merchant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone_number_one() {
        return phone_number_one;
    }

    public void setPhone_number_one(String phone_number_one) {
        this.phone_number_one = phone_number_one;
    }

    public String getPhone_number_two() {
        return phone_number_two;
    }

    public void setPhone_number_two(String phone_number_two) {
        this.phone_number_two = phone_number_two;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(Integer deleted_by) {
        this.deleted_by = deleted_by;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Merchant(@NotEmpty(message = "Phone number cannot be blank") String phone_number_one, String phone_number_two, @NotEmpty(message = "Address cannot be blank") String address, String status, Integer deleted_by, Date deleted_at, Date date_of_reg, User user) {
        this.phone_number_one = phone_number_one;
        this.phone_number_two = phone_number_two;
        this.address = address;
        this.status = status;
        this.deleted_by = deleted_by;
        this.deleted_at = deleted_at;
        this.date_of_reg = date_of_reg;
        this.user = user;
    }

    public List<TruckRequest> getTruckRequest() {
        return truckRequest;
    }

    public void setTruckRequest(List<TruckRequest> truckRequest) {
        this.truckRequest = truckRequest;
    }


}
