package com.boca.grabswebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "remark")
    private String remark;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="process_id", nullable=false)
    private Process process;

    @Column(name = "truck_id")
    private Long truck_id;

    @Column(name = "trip_id")
    private Long trip_id;

    @Column(name = "amount")
    private BigDecimal amount;


    @Column(name = "status")
    private String status;

    @Column(name = "date_of_reg_string")
    private String date_of_reg_string;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;


    public Expense() {
    }

    public Expense( String type, Process process, Long truck_id, BigDecimal amount, Long trip_id, String remark, String status) {
        this.type = type;
        this.process = process;
        this.truck_id = truck_id;
        this.amount= amount;
        this.trip_id = trip_id;
        this.remark = remark;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Long getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(Long truck_id) {
        this.truck_id = truck_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Long getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Long trip_id) {
        this.trip_id = trip_id;
    }
}
