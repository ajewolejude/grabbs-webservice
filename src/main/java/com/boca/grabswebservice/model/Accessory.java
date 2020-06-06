package com.boca.grabswebservice.model;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "accessory")
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "truck_id")
    private Long truck_id;

    @Column(name = "user_role")
    private String user_role;

    @Column(name = "user_id")
    private Long user_id;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "truck_trans_id")
    private TruckTransaction truckTransaction;

    public Accessory() {
    }

    public TruckTransaction getTruckTransaction() {
        return truckTransaction;
    }

    public void setTruckTransaction(TruckTransaction truckTransaction) {
        this.truckTransaction = truckTransaction;
    }

    public Accessory(String type, @NotEmpty(message = "Quantity cannot be blank") Long quantity, Long truck_id, String user_role, Long user_id, String date_of_reg_string, Date date_of_reg, String comment) {
        this.type = type;
        this.quantity = quantity;
        this.truck_id = truck_id;
        this.user_role = user_role;
        this.user_id = user_id;
        this.date_of_reg = date_of_reg;
        this.comment = comment;
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


    public Long getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(Long truck_id) {
        this.truck_id = truck_id;
    }


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
