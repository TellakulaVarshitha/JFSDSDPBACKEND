package com.klef.jfsd.sdp.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "doctor_table")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int id;

    @Column(name = "doctor_name", length = 50, nullable = false)
    private String name;

    @Column(name = "doctor_dob", nullable = false)
    private String dateofbirth;

    @Column(name = "doctor_gender", length = 10, nullable = false)
    private String gender;

    @Column(name = "doctor_specialization", length = 30, nullable = false)
    private String specialization;

    @Column(name = "doctor_location", length = 80, nullable = false)
    private String location;

    @Column(name = "doctor_email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "doctor_contact", length = 50, nullable = false, unique = true)
    private String contact;

    @Column(name = "doctor_status", length = 20, nullable = false)
    private String status;
    
    @JsonIgnore
    @Column(name = "doctor_image", nullable = false)
    private Blob image; 

    @Column(name = "doctor_password", length = 10, nullable = false)
    private String password;

   

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
  public Blob getImage() {
    return image;
  }

  public void setImage(Blob image) {
    this.image = image;
  }

  
}