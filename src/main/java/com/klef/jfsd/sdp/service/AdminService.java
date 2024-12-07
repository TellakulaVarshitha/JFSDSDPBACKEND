package com.klef.jfsd.sdp.service;

import java.util.List;

import com.klef.jfsd.sdp.model.Admin;
import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;

public interface AdminService
{
    public List<Doctor> viewAllDoctors();
    public Admin checkAdminLogin(String uname,String pwd);
    public List<Patient> viewAllPatients();
    public void updateDoctorStatus(String email,String pwd);
    public String deleteDoctor(int docid);
    public long totalAdminAppointments();
    public long totalDoctors();
    public long totalPatients();
}
