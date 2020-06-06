package com.boca.grabswebservice.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "repair")
@Where(clause="deleted_by = 0")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "Fault cannot be blank")
    @Column(name = "fault")
    private String fault;

    @Column(name = "fault_type")
    private String fault_type;

    @NotNull
    @NotEmpty(message = "Required Action cannot be blank")
    @Column(name = "action")
    private String action;

    @Column(name = "remark")
    private String remark;


    @Column(name = "technician")
    private String technician;

    @Column(name = "status")
    private String status;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_start")
    private Date date_of_start;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_end")
    private Date date_of_end;




    @NotNull()
    @Temporal(TemporalType.DATE)
    @Column(name = "time_spent")
    private Date time_spent;

    @ManyToOne
    @JoinColumn(name = "jobcard_id", nullable = false)
    private JobCard jobCard;

    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    public Repair() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getFault_type() {
        return fault_type;
    }

    public void setFault_type(String fault_type) {
        this.fault_type = fault_type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_of_start() {
        return date_of_start;
    }

    public void setDate_of_start(String date_of_start) {

        try {
            this.date_of_start = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDate_of_end() {
        return date_of_end;
    }

    public void setDate_of_end(String date_of_end) {
        try {
            this.date_of_end = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getTime_spent() {
        return time_spent;
    }

    public void setTime_spent(Date time_spent) {
        this.time_spent = time_spent;
    }

    public JobCard getJobCard() {
        return jobCard;
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

    public void setJobCard(JobCard jobCard) {
        this.jobCard = jobCard;
    }
}
