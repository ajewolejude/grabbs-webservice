package com.boca.grabswebservice.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "truck")
@Where(clause="deleted_by = 0")
public class Truck {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Plate number cannot be blank")
    @Size(min=8, max=10, message = "Characters cannot be less than 8 or greater than 10")
    @Column(name = "license_no", nullable = false, unique = true)
    public String license_no;

    @NotEmpty(message = "truck model cannot be blank")
    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "madein")
    private String madein;

    @Column(name = "price")
    private float price;


    @NotNull()
    @NotEmpty(message = "Chassis Number model cannot be blank")
    @Column(name = "chassis_number")
    private String chassis_number;

    @NotNull()
    @NotEmpty(message = "Engine number cannot be blank")
    @Column(name = "engine_number")
    private String engine_number;

    @NotNull()
    @NotEmpty(message = "Vehicle License Number cannot be blank")
    @Column(name = "vehicle_license_number")
    private String vehicle_license_number;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_vehicle_license_issuance")
    private Date date_of_vehicle_license_issuance;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_vehicle_license_expiry")
    private Date date_of_vehicle_license_expiry;


    @NotNull()
    @NotEmpty(message = "Heavy Duty Number cannot be blank")
    @Column(name = "heavy_duty_number")
    private String heavy_duty_number;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_heavy_duty_issuance")
    private Date date_of_heavy_duty_issuance;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_heavy_duty_expiry")
    private Date date_of_heavy_duty_expiry;

    @Column(name = "date_of_heavy_duty_issuance_string")
    private String date_of_heavy_duty_issuance_string;

    @Column(name = "date_of_heavy_duty_expiry_string")
    private String date_of_heavy_duty_expiry_string;

    @Column(name = "date_of_vehicle_license_expiry_string")
    private String date_of_vehicle_license_expiry_string;

    @Column(name = "date_of_vehicle_license_issuance_string")
    private String date_of_vehicle_license_issuance_string;

    @Column(name = "date_of_reg_string")
    private String date_of_reg_string;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;

    @OneToMany(mappedBy="truck", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tyre> tyre;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobCard> jobCards;


    @Column(name = "trip_status")
    private String trip_status;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "owner_id")
    private Long owner_id;

    @Column(name = "owner_role")
    private String owner_role;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Truck() {
    }

    public Truck(@NotEmpty(message = "Plate number cannot be blank") @Size(min = 8, max = 10, message = "Characters cannot be less than 8 or greater than 10") String license_no, @NotEmpty(message = "truck model cannot be blank") String model, String brand, String madein, float price, @NotEmpty(message = "Chassis Number model cannot be blank") String chassis_number, @NotEmpty(message = "Engine number model cannot be blank") String engine_number, @NotEmpty(message = "Vehicle License Number cannot be blank") String vehicle_license_number, Date date_of_vehicle_license_issuance, Date date_of_vehicle_license_expiry, @NotEmpty(message = "Heavy Duty Number cannot be blank") String heavy_duty_number, Date date_of_heavy_duty_issuance, Date date_of_heavy_duty_expiry, String status, Integer deleted_by, Long owner_id, String owner_role) {
        this.license_no = license_no;
        this.model = model;
        this.brand = brand;
        this.madein = madein;
        this.price = price;
        this.chassis_number = chassis_number;
        this.engine_number = engine_number;
        this.vehicle_license_number = vehicle_license_number;
        this.date_of_vehicle_license_issuance = date_of_vehicle_license_issuance;
        this.date_of_vehicle_license_expiry = date_of_vehicle_license_expiry;
        this.heavy_duty_number = heavy_duty_number;
        this.date_of_heavy_duty_issuance = date_of_heavy_duty_issuance;
        this.date_of_heavy_duty_expiry = date_of_heavy_duty_expiry;
        this.status = status;
        this.deleted_by = deleted_by;
        this.owner_role = owner_role;
        this.owner_id = owner_id;
    }

    public String getChassis_number() {
        return chassis_number;
    }

    public void setChassis_number(String chassis_number) {
        this.chassis_number = chassis_number;
    }

    public String getEngine_number() {
        return engine_number;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public String getVehicle_license_number() {
        return vehicle_license_number;
    }

    public void setVehicle_license_number(String vehicle_license_number) {
        this.vehicle_license_number = vehicle_license_number;
    }

    public Date getDate_of_vehicle_license_issuance() {
        return date_of_vehicle_license_issuance;
    }

    public void setDate_of_vehicle_license_issuance(Date date_of_vehicle_license_issuance) {
        this.date_of_vehicle_license_issuance = date_of_vehicle_license_issuance;
    }

    public Date getDate_of_vehicle_license_expiry() {
        return date_of_vehicle_license_expiry;
    }

    public void setDate_of_vehicle_license_expiry(Date date_of_vehicle_license_expiry) {
        this.date_of_vehicle_license_expiry = date_of_vehicle_license_expiry;
    }

    public String getHeavy_duty_number() {
        return heavy_duty_number;
    }

    public void setHeavy_duty_number(String heavy_duty_number) {
        this.heavy_duty_number = heavy_duty_number;
    }

    public Date getDate_of_heavy_duty_issuance() {
        return date_of_heavy_duty_issuance;
    }

    public void setDate_of_heavy_duty_issuance(Date date_of_heavy_duty_issuance) {
        this.date_of_heavy_duty_issuance = date_of_heavy_duty_issuance;
    }

    public Date getDate_of_heavy_duty_expiry() {
        return date_of_heavy_duty_expiry;
    }

    public void setDate_of_heavy_duty_expiry(Date date_of_heavy_duty_expiry) {
        this.date_of_heavy_duty_expiry = date_of_heavy_duty_expiry;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(Integer deleted_by) {
        this.deleted_by = deleted_by;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate_of_heavy_duty_issuance_string() {
        return date_of_heavy_duty_issuance_string;
    }

    public void setDate_of_heavy_duty_issuance_string(String date_of_heavy_duty_issuance_string) {
        this.date_of_heavy_duty_issuance_string = date_of_heavy_duty_issuance_string;
    }

    public String getDate_of_heavy_duty_expiry_string() {
        return date_of_heavy_duty_expiry_string;
    }

    public void setDate_of_heavy_duty_expiry_string(String date_of_heavy_duty_expiry_string) {
        this.date_of_heavy_duty_expiry_string = date_of_heavy_duty_expiry_string;
    }

    public String getDate_of_vehicle_license_expiry_string() {
        return date_of_vehicle_license_expiry_string;
    }

    public void setDate_of_vehicle_license_expiry_string(String date_of_vehicle_license_expiry_string) {
        this.date_of_vehicle_license_expiry_string = date_of_vehicle_license_expiry_string;
    }

    public String getDate_of_vehicle_license_issuance_string() {
        return date_of_vehicle_license_issuance_string;
    }

    public void setDate_of_vehicle_license_issuance_string(String date_of_vehicle_license_issuance_string) {
        this.date_of_vehicle_license_issuance_string = date_of_vehicle_license_issuance_string;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }

    public String getDate_of_reg_string() {
        return date_of_reg_string;
    }

    public void setDate_of_reg_string(String date_of_reg_string) {
        this.date_of_reg_string = date_of_reg_string;
    }

    public List<Tyre> getTyre() {
        return tyre;
    }

    public void setTyre(List<Tyre> tyre) {
        this.tyre = tyre;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_role() {
        return owner_role;
    }

    public void setOwner_role(String owner_role) {
        this.owner_role = owner_role;
    }

    public String getTrip_status() {
        return trip_status;
    }

    public void setTrip_status(String trip_status) {
        this.trip_status = trip_status;
    }
}
