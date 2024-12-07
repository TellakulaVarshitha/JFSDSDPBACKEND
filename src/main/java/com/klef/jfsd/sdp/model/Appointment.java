package com.klef.jfsd.sdp.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="appointment_table")
public class Appointment {

	@Id
	@Column(name = "appointment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "doctor_id",nullable = false)
	private int doctorid;
	@Column(name = "patient_id",nullable = false)
	private int patientid;
	@Column(name = "p_email",nullable = false)
	private String email;
	@Column(name = "appointement_specalist",nullable = false)
	private String specalist;
	@Column(name = "doctor_name",nullable = false)
	private String doctorname;
    @Column(name = "appointment_date",nullable = false)
	private String date;
	@Column(name = "appointment_time",nullable = false)
	private String time;
	@Column(name = "appointment_docstatus",nullable = false)
	private String doctorstatus;
	@Column(name = "appointment_status",nullable = false)
	private String status;
	@Column(name = "appointment_prescription",nullable = false)
	private String prescription;
	@Column(name = "appointment_timestamp",nullable = false)	
	private String appointmenttimestamp;
	@Column(name="appointment_fees",nullable = false)
	private int fees;
	@Column(name = "appointment_reports")
	private Blob reports;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSpecalist() {
		return specalist;
	}
	public void setSpecalist(String specalist) {
		this.specalist = specalist;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDoctorstatus() {
		return doctorstatus;
	}
	public void setDoctorstatus(String doctorstatus) {
		this.doctorstatus = doctorstatus;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getAppointmenttimestamp() {
		return appointmenttimestamp;
	}
	public void setAppointmenttimestamp(String appointmenttimestamp) {
		this.appointmenttimestamp = appointmenttimestamp;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public Blob getReports() {
		return reports;
	}
	public void setReports(Blob reports) {
		this.reports = reports;
	}
	
}
