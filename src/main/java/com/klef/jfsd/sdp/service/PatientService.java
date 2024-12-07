package com.klef.jfsd.sdp.service;

import java.time.LocalTime;
import java.util.List;

import com.klef.jfsd.sdp.model.Appointment;
import com.klef.jfsd.sdp.model.Patient;

public interface PatientService {
	
	public String patientRegistration(Patient p);
	public Patient checkPatientLogin(String email,String pwd);
	public String saveAppointments(Appointment a);
	public List<Appointment> viewAppointments(int pid);
	public String deleteAccount(int pid);
	
	public Patient viewPatientById(int pid);
	public List<LocalTime> getAppointmentsByDateAndTime(int did,String date);
	
	public String getDoctorName(int id);
	public void cancelAppointment(int aid);
	public long totalAppointments(int pid);
	

}
