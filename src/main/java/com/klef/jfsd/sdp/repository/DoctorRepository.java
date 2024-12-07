package com.klef.jfsd.sdp.repository;

import java.sql.Blob;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;

import jakarta.transaction.Transactional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> 
{
	
	@Query("select d from Doctor d where d.email=?1")
    public Doctor doctorProfile(String email);
	
	@Query("select d from Doctor d where d.email=?1 and d.password=?2")
    public Doctor checkDoctorLogin(String email,String pwd);
	
	@Modifying
    @Transactional
    @Query("UPDATE Doctor d SET d.status=?2 WHERE d.email =?1")
	public void updateDoctorStatus(String email,String status);
	 
	@Query("select d from Doctor d where d.specialization=?1 and d.status=?2")
	public List<Doctor> getDoctorBySpecialization(String specialization,String reg);
	
	@Query("SELECT d FROM Doctor d WHERE d.id = ?1")
    Doctor findDoctorDetailsById(int id);
	
	@Query("SELECT p FROM Patient p WHERE p.id = ?1")
	public List<Patient> viewDocPatients(int pid);
	
	
	
	
}
