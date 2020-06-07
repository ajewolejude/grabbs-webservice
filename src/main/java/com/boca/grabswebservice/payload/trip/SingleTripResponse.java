package com.boca.grabswebservice.payload.trip;

import com.boca.grabswebservice.model.Process;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SingleTripResponse {


    private Long id;
    private String  dest_type;


    private String  dest_address;


    private String  start_type;

    private String  start_address;

    private String remark;

    private Long driver_id;

    private Long mate_id;


    private String date_of_reg_string;

    private Date date_of_reg;

    private String date_of_assign_string;

    private Date date_of_assign;

    private Long truck_id;


    private List<Process> process;


    private String status;

    private BigDecimal charge;

    private Integer deleted_by;

    private Date deleted_at;

    public SingleTripResponse() {
    }

    public SingleTripResponse(Long id, String dest_type, String dest_address, String start_type, String start_address, String remark, Long driver_id, Long mate_id, String date_of_reg_string, Date date_of_reg, String date_of_assign_string, Date date_of_assign, Long truck_id, List<Process> process, String status, BigDecimal charge, Integer deleted_by, Date deleted_at) {
        this.id = id;
        this.dest_type = dest_type;
        this.dest_address = dest_address;
        this.start_type = start_type;
        this.start_address = start_address;
        this.remark = remark;
        this.driver_id = driver_id;
        this.mate_id = mate_id;
        this.date_of_reg_string = date_of_reg_string;
        this.date_of_reg = date_of_reg;
        this.date_of_assign_string = date_of_assign_string;
        this.date_of_assign = date_of_assign;
        this.truck_id = truck_id;
        this.process = process;
        this.status = status;
        this.charge = charge;
        this.deleted_by = deleted_by;
        this.deleted_at = deleted_at;
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




