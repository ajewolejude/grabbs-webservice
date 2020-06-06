package com.boca.grabswebservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "companyowner")
@Where(clause="deleted_by = 0")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property="id")
public class CompanyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "RC number cannot be blank")
    @Size(max = 12, message = "Characters cannot be greater than 12")
    @Column(name = "rc_no",  nullable = false, unique = true)
    private String rc_no;

    @NotNull
    @NotEmpty(message = "Company Name cannot be blank")
    @Column(name = "company_name")
    private String  company_name;

    @NotNull
    @Email(message = "Must be email format")
    @NotEmpty(message = "Email cannot be blank")
    @Column(name = "email")
    private String email;

    @NotNull
    @NotEmpty(message = "Phone number cannot be blank")
    @Column(name = "phone_number_one")
    private String phone_number_one;


    @Column(name = "phone_number_two")
    private String phone_number_two;

    @NotNull
    @NotEmpty(message = "Address cannot be blank")
    @Column(name = "address")
    private String address;

    @NotNull
    @NotEmpty(message = "Contact's first name cannot be blank")
    @Column(name = "contact_first_name")
    private String contact_first_name;

    @NotNull
    @NotEmpty(message = "Contact's last name cannot be blank")
    @Column(name = "contact_last_name")
    private String contact_last_name;

    @NotNull
    @NotEmpty(message = "Contact's phone number cannot be blank")
    @Column(name = "contact_phone_number")
    private String contact_phone_number;

    @NotNull
    @NotEmpty(message = "Contact's email cannot be blank")
    @Column(name = "contact_email")
    private String contact_email;

    @NotNull
    @NotEmpty(message = "Contact's Position cannot be blank")
    @Column(name = "contact_position")
    private String contact_position;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "deleted_by", columnDefinition = "int default 0")
    private Integer deleted_by;

    @Column(name = "deleted_at", columnDefinition = "int default 0")
    @Temporal(TemporalType.DATE)
    private Date deleted_at;

    @Column(name = "dateOfRegString")
    private String dateOfRegString;

    @NotNull()
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfReg")
    private Date dateOfReg;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public CompanyOwner() {
    }


    public CompanyOwner(@NotEmpty(message = "RC number cannot be blank") @Size( max = 12, message = "Characters cannot be greater than 12") String rc_no, @NotEmpty(message = "Company Name cannot be blank") String company_name, @Email(message = "Must be email format") @NotEmpty(message = "Email cannot be blank") String email, @NotEmpty(message = "Phone number cannot be blank") String phone_number_one, String phone_number_two, @NotEmpty(message = "Address cannot be blank") String address, @NotEmpty(message = "Contact's first name cannot be blank") String contact_first_name, @NotEmpty(message = "Contact's last name cannot be blank") String contact_last_name, @NotEmpty(message = "Contact's phone number cannot be blank") String contact_phone_number, @NotEmpty(message = "Contact's email cannot be blank") String contact_email, String status, Integer deleted_by, Date deleted_at, String dateOfRegString, Date dateOfReg) {
        this.rc_no = rc_no;
        this.company_name = company_name;
        this.email = email;
        this.phone_number_one = phone_number_one;
        this.phone_number_two = phone_number_two;
        this.address = address;
        this.contact_first_name = contact_first_name;
        this.contact_last_name = contact_last_name;
        this.contact_phone_number = contact_phone_number;
        this.contact_email = contact_email;
        this.status = status;
        this.deleted_by = deleted_by;
        this.deleted_at = deleted_at;
        this.dateOfRegString = dateOfRegString;
        this.dateOfReg = dateOfReg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRc_no() {
        return rc_no;
    }

    public void setRc_no(String rc_no) {
        this.rc_no = rc_no;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number_one() {
        return phone_number_one;
    }

    public void setPhone_number_one(String phone_number_one) {
        this.phone_number_one = phone_number_one;
    }

    public String getPhone_number_two() {
        return phone_number_two;
    }

    public void setPhone_number_two(String phone_number_two) {
        this.phone_number_two = phone_number_two;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_first_name() {
        return contact_first_name;
    }

    public void setContact_first_name(String contact_first_name) {
        this.contact_first_name = contact_first_name;
    }

    public String getContact_last_name() {
        return contact_last_name;
    }

    public void setContact_last_name(String contact_last_name) {
        this.contact_last_name = contact_last_name;
    }

    public String getContact_phone_number() {
        return contact_phone_number;
    }

    public void setContact_phone_number(String contact_phone_number) {
        this.contact_phone_number = contact_phone_number;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
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

    public String getDateOfRegString() {
        return dateOfRegString;
    }

    public void setDateOfRegString(String dateOfRegString) {
        this.dateOfRegString = dateOfRegString;
    }

    public Date getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(Date dateOfReg) {
        this.dateOfReg = dateOfReg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContact_position() {
        return contact_position;
    }

    public void setContact_position(String contact_position) {
        this.contact_position = contact_position;
    }
}
