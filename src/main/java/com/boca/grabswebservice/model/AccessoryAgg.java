package com.boca.grabswebservice.model;

import javax.persistence.*;

@Entity
@Table(name = "accessoryagg")
public class AccessoryAgg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "agg")
    private Long agg;

    public AccessoryAgg(String type, Long agg) {
        this.type = type;
        this.agg = agg;
    }

    public AccessoryAgg() {
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

    public Long getAgg() {
        return agg;
    }

    public void setAgg(Long agg) {
        this.agg = agg;
    }
}
