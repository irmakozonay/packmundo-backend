package com.code.packmundo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "box_types")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private String description;
    private Integer mainTypeId;
    private String fieldsJson;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMainTypeId() {
        return mainTypeId;
    }

    public String getFieldsJson() {
        return fieldsJson;
    }

}