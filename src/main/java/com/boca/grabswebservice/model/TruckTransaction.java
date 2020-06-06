package com.boca.grabswebservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trucktrans")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property="id")
public class TruckTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "truck_id")
    private Long truck_id;

    @Column(name = "driver_id")
    private Long driver_id;

    @Column(name = "mate_id")
    private Long mate_id;

    @Column(name = "date_of_trans_string")
    private String date_of_trans_string;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_trans")
    private Date date_of_trans;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "truckTransaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Accessory> accessory;


    public TruckTransaction() {
    }


    public List<Accessory> getAccessory() {
        return accessory;
    }

    public void setAccessory(List<Accessory> accessory) {
        this.accessory = accessory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDate_of_trans_string() {
        return date_of_trans_string;
    }

    public void setDate_of_trans_string(String date_of_trans_string) {
        this.date_of_trans_string = date_of_trans_string;
    }

    public Date getDate_of_trans() {
        return date_of_trans;
    }

    public void setDate_of_trans(Date date_of_trans) {
        this.date_of_trans = date_of_trans;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(Long truck_id) {
        this.truck_id = truck_id;
    }
}
