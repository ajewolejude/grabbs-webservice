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
@Table(name = "trip")
@Where(clause="deleted_by = 0")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Destination Type cannot be blank")
    @Column(name = "dest_type")
    private String  dest_type;


    @Column(name = "dest_address")
    private String  dest_address;


    @Column(name = "start_type")
    private String  start_type;

    @Column(name = "start_address")
    private String  start_address;

    @NotNull
    @NotEmpty(message = "Remark cannot be blank")
    @Column(name = "remark")
    private String remark;

    @Column(name = "driver_id")
    private Long driver_id;

    @Column(name = "mate_id")
    private Long mate_id;


    @Column(name = "date_of_reg_string")
    private String date_of_reg_string;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;

    @Column(name = "date_of_assign_string")
    private String date_of_assign_string;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_assign")
    private Date date_of_assign;

    @NotNull()
    @Column(name="truck_id")
    private Long truck_id;


    @OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Process> process;


    @NotNull
    @Column(name = "status")
    private String status;

    @Column(name = "charge")
    private BigDecimal charge;

    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at", columnDefinition = "int default 0")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    public Trip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDest_type() {
        return dest_type;
    }

    public void setDest_type(String dest_type) {
        this.dest_type = dest_type;
    }

    public String getDest_address() {
        return dest_address;
    }

    public void setDest_address(String dest_address) {
        this.dest_address = dest_address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate_of_reg_string() {
        return date_of_reg_string;
    }

    public void setDate_of_reg_string(String date_of_reg_string) {
        this.date_of_reg_string = date_of_reg_string;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }

    public String getDate_of_assign_string() {
        return date_of_assign_string;
    }

    public void setDate_of_assign_string(String date_of_assign_string) {
        this.date_of_assign_string = date_of_assign_string;
    }

    public Date getDate_of_assign() {
        return date_of_assign;
    }

    public void setDate_of_assign(Date date_of_assign) {
        this.date_of_assign = date_of_assign;
    }

    public List<Process> getProcess() {
        return process;
    }

    public void setProcess(List<Process> process) {
        this.process = process;
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

    public String getStart_type() {
        return start_type;
    }

    public void setStart_type(String start_type) {
        this.start_type = start_type;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Long getMate_id() {
        return mate_id;
    }

    public void setMate_id(Long mate_id) {
        this.mate_id = mate_id;
    }

    public Long getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(Long truck_id) {
        this.truck_id = truck_id;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
}
