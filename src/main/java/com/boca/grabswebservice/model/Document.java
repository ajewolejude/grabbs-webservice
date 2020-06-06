package com.boca.grabswebservice.model;


import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {

    @Column(name = "id")
    private Long id;

    @Column(name = "document_category")
    private String document_category;


    @Column(name = "owner_id" )
    private Long owner_id;

    @Column(name = "document_path" )
    private String document_path;

    @Column(name = "document_name" )
    private String document_name;

    @Column(name = "document_alias" )
    private String document_alias;

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public Document(String document_category, Long owner_id, String document_path, String document_name, String document_alias) {
        this.document_category = document_category;
        this.owner_id = owner_id;
        this.document_path = document_path;
        this.document_name = document_name;
        this.document_alias = document_alias;
    }

    public Document() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_category() {
        return document_category;
    }

    public void setDocument_category(String document_category) {
        this.document_category = document_category;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public String getDocument_alias() {
        return document_alias;
    }

    public void setDocument_alias(String document_alias) {
        this.document_alias = document_alias;
    }
}