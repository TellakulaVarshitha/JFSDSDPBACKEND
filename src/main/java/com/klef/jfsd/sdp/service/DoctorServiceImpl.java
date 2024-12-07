package com.klef.jfsd.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.model.Appointment;
import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;
import com.klef.jfsd.sdp.repository.AppointmentRepository;
import com.klef.jfsd.sdp.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService
{
	  @Autowired
      private DoctorRepository doctorRepository;
	  
	  @Autowired
	  private AppointmentRepository appointmentRepository;

	@Override
	public String doctorRegistration(Doctor d)
	{
		doctorRepository.save(d);
		return "Registered Successfully";
	}

	@Override
	public Doctor doctorProfile(String email) {
		
		return doctorRepository.doctorProfile(email);
	}

	@Override
	public Doctor checkDoctorLogin(String email, String password) {

		return doctorRepository.checkDoctorLogin(email, password);
	}

	@Override
	public List<Appointment> viewDoctorAppointments(int docid) {
		return appointmentRepository.findByDoctorid(docid);
				
	}

	@Override
	public List<Doctor> getDocBySpecalisation(String s) {
		String reg="Accepted";
		return doctorRepository.getDoctorBySpecialization(s,reg);
	}

	@Override
    public Doctor getDoctorProfileById(int id) {
       return doctorRepository.findDoctorDetailsById(id);
   }

	@Override
	public void updateAppointmentStatus(int id, String status) {
		 appointmentRepository.updateAppointmentStatus(id, status);
	}

	@Override
	public void addPrescription(int id, String prescription) {
		appointmentRepository.addPrescription(id, prescription);
		
	}

	@Override
	public List<Patient> viewDocPatients(int pid) {
		return doctorRepository.viewDocPatients(pid);
	}
	

	  
	  
}
