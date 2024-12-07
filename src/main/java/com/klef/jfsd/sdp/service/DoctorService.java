package com.klef.jfsd.sdp.service;

import java.util.List;

import com.klef.jfsd.sdp.model.Appointment;
import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;

public interface DoctorService 
{
	public String doctorRegistration(Doctor d);
     public Doctor doctorProfile(String email);
     public Doctor checkDoctorLogin(String email,String password);
     public List<Appointment> viewDoctorAppointments(int docid);
     public List<Doctor> getDocBySpecalisation(String s);
     Doctor getDoctorProfileById(int id);
     public void updateAppointmentStatus(int id,String status);
     public void addPrescription(int id,String prescription);
     public List<Patient> viewDocPatients(int pid);
}
