package com.boca.grabswebservice.model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "truckrequest")
@Where(clause="deleted_by = 0")
public class TruckRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="price")
    private BigDecimal price;

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
    @NotEmpty(message = "Loading Point cannot be blank")
    @Column(name = "start_address")
    private String start_address;


    @NotNull
    @NotEmpty(message = "Destination  cannot be blank")
    @Column(name = "dest_address")
    private String dest_address;

    @NotNull
    @NotEmpty(message = "Truck Weight cannot be blank")
    @Column(name = "truck_weight")
    private String truck_weight;

    @NotNull
    @NotEmpty(message = "Truck Type cannot be blank")
    @Column(name = "truck_type")
    private String truck_type;

    @Column(name = "truck_sub_type")
    private String truck_sub_type;


    @NotNull
    @NotEmpty(message = "Purpose cannot be blank, We need to know what you are carrying")
    @Column(name = "purpose")
    private String purpose;

    @NotNull
    @NotEmpty(message = "Purpose cannot be blank")
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
    @Column(name = "dateOfReg")
    private Date dateOfReg;


    @ManyToOne
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    public TruckRequest() {
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

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getDest_address() {
        return dest_address;
    }

    public void setDest_address(String dest_address) {
        this.dest_address = dest_address;
    }


    public String getTruck_type() {
        return truck_type;
    }

    public void setTruck_type(String truck_type) {
        this.truck_type = truck_type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public Date getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(Date dateOfReg) {
        this.dateOfReg = dateOfReg;
    }


    public String getTruck_weight() {
        return truck_weight;
    }

    public void setTruck_weight(String truck_weight) {
        this.truck_weight = truck_weight;
    }

    public String getTruck_sub_type() {
        return truck_sub_type;
    }

    public void setTruck_sub_type(String truck_sub_type) {
        this.truck_sub_type = truck_sub_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }


}
