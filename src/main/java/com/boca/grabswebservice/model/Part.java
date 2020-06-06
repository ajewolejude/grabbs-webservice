package com.boca.grabswebservice.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "part")
@Where(clause="deleted_by = 0")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Item Name cannot be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Long quantity;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reg")
    private Date date_of_reg;


    @Column(name = "amount")
    private BigDecimal amount;



    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;


    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "jobcard_id", nullable = false)
    private JobCard jobCard;

    public Part() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public JobCard getJobCard() {
        return jobCard;
    }

    public void setJobCard(JobCard jobCard) {
        this.jobCard = jobCard;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
