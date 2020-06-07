package com.boca.grabswebservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "driver")
@Where(clause="deleted_by = 0")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property="id")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Licence number cannot be blank")
    @Size(min=8, max=10, message = "Characters cannot be less than 8 or greater than 10")
    @Column(name = "license_no",  nullable = false, unique = true)
    private String license_no;

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

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;


    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_license_issuance")
    private Date date_of_license_issuance;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_license_expiry")
    private Date date_of_license_expiry;

    @NotNull
    @NotEmpty(message = "State of origin cannot be blank")
    @Column(name = "state_of_origin")
    private String state_of_origin;

    @NotNull
    @NotEmpty(message = "Address cannot be blank")
    @Column(name = "address")
    private String address;

    @NotNull
    @NotEmpty(message = "Relationship cannot be blank")
    @Column(name = "relationship")
    private String relationship;

    @NotNull
    @NotEmpty(message = "Guarantor's first name cannot be blank")
    @Column(name = "guarantor_first_name")
    private String guarantor_first_name;

    @NotNull
    @NotEmpty(message = "Guarantor's last name cannot be blank")
    @Column(name = "guarantor_last_name")
    private String guarantor_last_name;

    @NotNull
    @NotEmpty(message = "Guarantor's phone number cannot be blank")
    @Column(name = "guarantor_phone_number")
    private String guarantor_phone_number;

    @NotNull
    @NotEmpty(message = "Guarantor's address cannot be blank")
    @Column(name = "guarantor_address")
    private String guarantor_address;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at", columnDefinition = "int default 0")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @Column(name = "dobString")
    private String dobString;

    @Column(name = "dateOfIssuanceString")
    private String dateOfIssuanceString;

    @Column(name = "dateOfExpiryString")
    private String dateOfExpiryString;

    @Column(name = "dateOfRegString")
    private String dateOfRegString;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfReg")
    private Date dateOfReg;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Driver() {
    }

    public Driver(@NotEmpty(message = "Licence number cannot be blank") @Size(min = 8, max = 10, message = "Characters cannot be less than 8 or greater than 10") String license_no, @NotEmpty(message = "First Name cannot be blank") String first_name, @NotEmpty(message = "Last name cannot be blank") String last_name, @Email(message = "Must be email format") @NotEmpty(message = "Email cannot be blank") String email, @NotEmpty(message = "Phone number cannot be blank") String phone_number_one, String phone_number_two, Date dob, Date date_of_license_issuance, Date date_of_license_expiry, @NotEmpty(message = "State of origin cannot be blank") String state_of_origin, @NotEmpty(message = "Address cannot be blank") String address, @NotEmpty(message = "Relationship cannot be blank") String relationship, @NotEmpty(message = "Guarantor's first name cannot be blank") String guarantor_first_name, @NotEmpty(message = "Guarantor's last name cannot be blank") String guarantor_last_name, @NotEmpty(message = "Guarantor's phone number cannot be blank") String guarantor_phone_number, @NotEmpty(message = "Guarantor's address cannot be blank") String guarantor_address, String status) {
        this.license_no = license_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number_one = phone_number_one;
        this.phone_number_two = phone_number_two;
        this.dob = dob;
        this.date_of_license_issuance = date_of_license_issuance;
        this.date_of_license_expiry = date_of_license_expiry;
        this.state_of_origin = state_of_origin;
        this.address = address;
        this.relationship = relationship;
        this.guarantor_first_name = guarantor_first_name;
        this.guarantor_last_name = guarantor_last_name;
        this.guarantor_phone_number = guarantor_phone_number;
        this.guarantor_address = guarantor_address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDate_of_license_issuance() {
        return date_of_license_issuance;
    }

    public void setDate_of_license_issuance(Date date_of_license_issuance) {
        this.date_of_license_issuance = date_of_license_issuance;
    }

    public Date getDate_of_license_expiry() {
        return date_of_license_expiry;
    }

    public void setDate_of_license_expiry(Date date_of_license_expiry) {
        this.date_of_license_expiry = date_of_license_expiry;
    }

    public String getState_of_origin() {
        return state_of_origin;
    }

    public void setState_of_origin(String state_of_origin) {
        this.state_of_origin = state_of_origin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getGuarantor_first_name() {
        return guarantor_first_name;
    }

    public void setGuarantor_first_name(String guarantor_first_name) {
        this.guarantor_first_name = guarantor_first_name;
    }

    public String getGuarantor_last_name() {
        return guarantor_last_name;
    }

    public void setGuarantor_last_name(String guarantor_last_name) {
        this.guarantor_last_name = guarantor_last_name;
    }

    public String getGuarantor_phone_number() {
        return guarantor_phone_number;
    }

    public void setGuarantor_phone_number(String guarantor_phone_number) {
        this.guarantor_phone_number = guarantor_phone_number;
    }

    public String getGuarantor_address() {
        return guarantor_address;
    }

    public void setGuarantor_address(String guarantor_address) {
        this.guarantor_address = guarantor_address;
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

    public String getDobString() {
        return dobString;
    }

    public void setDobString(String dobString) {
        this.dobString = dobString;
    }

    public String getDateOfIssuanceString() {
        return dateOfIssuanceString;
    }

    public void setDateOfIssuanceString(String dateOfIssuanceString) {
        this.dateOfIssuanceString = dateOfIssuanceString;
    }

    public String getDateOfExpiryString() {
        return dateOfExpiryString;
    }

    public void setDateOfExpiryString(String dateOfExpiryString) {
        this.dateOfExpiryString = dateOfExpiryString;
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

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }
}
