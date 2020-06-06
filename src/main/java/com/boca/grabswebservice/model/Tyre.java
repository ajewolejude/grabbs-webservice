package com.boca.grabswebservice.model;

import javax.persistence.*;

@Entity
@Table(name = "tyre")
public class Tyre {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "size")
    private String size;

    @Column(name = "serial_number")
    private String serial_number;

    @ManyToOne
    @JoinColumn(name="truck_id", nullable=false)
    private Truck truck;


    @Column(name = "status")
    private String status;

    public Tyre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
