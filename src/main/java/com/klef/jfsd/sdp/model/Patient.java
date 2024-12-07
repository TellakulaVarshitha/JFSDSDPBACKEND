package com.klef.jfsd.sdp.model;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "patient_table")
public class Patient
{     
	  @Id
	  @Column(name = "patient_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;
	  @Column(name = "patient_name",length = 50,nullable = false)
	  private String name;
	  @Column(name = "patient_dob",length = 50,nullable = false)
	  private String dateofbirth;
	  @Column(name = "patient_gender",length = 50,nullable = false)
	  private String gender;
	  @Column(name = "patient_location",length = 50,nullable = false)
	  private String location;
	  @Column(name = "patient_email",length = 50,unique = true,nullable = false)
	  private String email;
	  @Column(name="patient_bloodgroup",length = 50,nullable = false)
	  private String bloodgroup;
	  @Column(name = "patient_contact",length = 50,nullable = false,unique = true)
	  private String contact;
	  @Column(name = "patient_password",length = 50,nullable = false)
	  private String password;
	  @JsonIgnore
	  @Column(name="patient_image")
	  private Blob image; // blob - binary large object
	  
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
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	
      
}
