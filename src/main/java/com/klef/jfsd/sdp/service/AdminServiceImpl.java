package com.klef.jfsd.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.sdp.model.Admin;
import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;
import com.klef.jfsd.sdp.repository.AdminRepository;
import com.klef.jfsd.sdp.repository.AppointmentRepository;
import com.klef.jfsd.sdp.repository.DoctorRepository;
import com.klef.jfsd.sdp.repository.PatientRepository;

@Service
public class AdminServiceImpl implements AdminService
{
  @Autowired
	private DoctorRepository doctorRepository;
  
  @Autowired
  private AdminRepository adminRepository;
  
  @Autowired
  private PatientRepository patientRepository;
  
  @Autowired
  private AppointmentRepository appointmentRepository;
  
	@Override
	public List<Doctor> viewAllDoctors() {
		
		return doctorRepository.findAll();
	}

	@Override
	public Admin checkAdminLogin(String uname, String pwd) {
		
		return adminRepository.checkAdminLogin(uname, pwd);
	}

	@Override
	public List<Patient> viewAllPatients() {
		
		return patientRepository.findAll();
	}

	@Override
	public void updateDoctorStatus(String email, String pwd) {
		doctorRepository.updateDoctorStatus(email, pwd);
		
	}

	@Override
	public String deleteDoctor(int docid) {
		doctorRepository.deleteById(docid);
		return "Deleted Successfully";
	}

	@Override
	public long totalAdminAppointments() {
		return appointmentRepository.totalAdminAppointments();
	}

	@Override
	public long totalDoctors() {
		return adminRepository.totaldoctors();
	}

	@Override
	public long totalPatients() {
		return adminRepository.totalpatients();
	}

	
      
}
