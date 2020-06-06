package com.boca.grabswebservice.model;


import java.util.Date;


public class Profile {

    private Long id;


    private String license_no;


    private String  first_name;


    private String last_name;

    private String email;


    private String phone_number_one;

    private String phone_number_two;


    private Date dob;


    private Date date_of_license_issuance;

    private Date date_of_license_expiry;


    private String state_of_origin;


    private String address;

    private String relationship;

    private String guarantor_first_name;

    private String guarantor_last_name;


    private String guarantor_phone_number;

    private String guarantor_address;

    private String status;

    private Integer deleted_by;

    private Date deleted_at;

    private String dobString;

    private String dateOfIssuanceString;

    private String dateOfExpiryString;

    private String dateOfRegString;

    private Date dateOfReg;

    private User user;

    public Profile() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
