package com.boca.grabswebservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "privateowner")
@Where(clause="deleted_by = 0")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property="id")
public class PrivateOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "First Name cannot be blank")
    @Column(name = "first_name")
    private String  first_name;

    @NotNull
    @NotEmpty(message = "Last name cannot be blank")
    @Column(name = "last_name")
    private String last_name;

    @NotNull
    @Email(message = "Must be email format")
    @NotEmpty(message = "Email cannot be blank")
    @Column(name = "email")
    private String email;

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

    @Column(name = "dateOfRegString")
    private String dateOfRegString;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfReg")
    private Date dateOfReg;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public PrivateOwner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDateOfRegString() {
        return dateOfRegString;
    }

    public void setDateOfRegString(String dateOfRegString) {
        this.dateOfRegString = dateOfRegString;
    }

    public Date getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(Date dateOfReg) {
        this.dateOfReg = dateOfReg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

