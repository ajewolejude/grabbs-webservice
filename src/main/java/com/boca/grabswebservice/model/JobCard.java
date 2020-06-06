package com.boca.grabswebservice.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jobcard")
@Where(clause="deleted_by = 0")
public class JobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Driver's Name cannot be blank")
    @Column(name = "driver_name")
    private String driver_name;

    @Column(name = "fuel_level")
    private String fuel_level;

    @Column(name = "mileage_reading")
    private String mileage_reading;

    @Column(name = "workmanship")
    private BigDecimal workmanship;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;

    @Column(name = "comment")
    private String comment;


    @OneToMany(mappedBy = "jobCard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Repair> repairs;

    @OneToMany(mappedBy = "jobCard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Part> parts;


    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @Transient
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "truck_id", nullable = false)
    private Truck truck;

    public JobCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getMileage_reading() {
        return mileage_reading;
    }

    public void setMileage_reading(String mileage_reading) {
        this.mileage_reading = mileage_reading;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getFuel_level() {
        return fuel_level;
    }

    public void setFuel_level(String fuel_level) {
        this.fuel_level = fuel_level;
    }

    public BigDecimal getWorkmanship() {
        return workmanship;
    }

    public void setWorkmanship(BigDecimal workmanship) {
        this.workmanship = workmanship;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
