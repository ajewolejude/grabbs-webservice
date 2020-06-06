package com.boca.grabswebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mode_of_transport")
    private String mode_of_transport;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false)
    private Trip trip;



    @OneToMany(mappedBy="process", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expense;

    @Column(name = "date_of_reg_string")
    private String date_of_reg_string;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;


    @Column(name = "status")
    private String status;

    public Process() {
    }

    public Process(String mode_of_transport, String type, Trip trip, String status, Date date_of_reg, String date_of_reg_string) {
        this.mode_of_transport = mode_of_transport;
        this.type = type;
        this.trip = trip;
        this.status = status;
        this.date_of_reg = date_of_reg;
        this.date_of_reg_string = date_of_reg_string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMode_of_transport() {
        return mode_of_transport;
    }

    public void setMode_of_transport(String mode_of_transport) {
        this.mode_of_transport = mode_of_transport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
