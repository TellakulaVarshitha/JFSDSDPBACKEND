package com.klef.jfsd.sdp.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.model.Appointment;
import com.klef.jfsd.sdp.model.Patient;
import com.klef.jfsd.sdp.repository.AppointmentRepository;
import com.klef.jfsd.sdp.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Override
	public String patientRegistration(Patient p) {
		patientRepository.save(p);
		return "Registered Successfully";
	}
	@Override
	public Patient checkPatientLogin(String email, String pwd) {
		
		
		return  patientRepository.checkPatientLogin(email, pwd);

		
	}
	@Override
	public String saveAppointments(Appointment a) {
		appointmentRepository.save(a);
		return "Appointment Booked Successfully";
	}
	@Override
	public List<Appointment> viewAppointments(int pid) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByPatientid(pid);
		
	}
	@Override
	public String deleteAccount(int pid) {
     patientRepository.deleteById(pid);
		return "Deleted Successfully";
	}
	@Override
	public Patient viewPatientById(int pid) {
		return patientRepository.findById(pid).get();
	}
	@Override
	public List<LocalTime> getAppointmentsByDateAndTime(int did,String date) {
		return appointmentRepository.viewAppointmentsByDateandTime(did,date, "Registered");
	}
	@Override
	public String getDoctorName(int id) {
		return appointmentRepository.getDoctorName(id);
	}
	@Override
	public void cancelAppointment(int aid) {
		appointmentRepository.cancelAppointment("Cancel", aid);
		
	}
	@Override
	public long totalAppointments(int pid) {
		return appointmentRepository.totalAppointments(pid);
	}
	
	

}
