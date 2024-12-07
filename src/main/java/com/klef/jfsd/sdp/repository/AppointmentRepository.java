package com.klef.jfsd.sdp.repository;

import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.klef.jfsd.sdp.model.Appointment;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	 /*@Query("select a from Appointment a where a.patient_id=pid")
	  public List<Appointment> viewAppointments(int pid);*/
	 
	 public List<Appointment>  findByPatientid(int patientid);
	 
	 public List<Appointment> findByDoctorid(int doctorid);
	 
	 @Query("select a.time from Appointment a where a.doctorid=?1 and a.date=?2 and a.status=?3")
	public List<LocalTime> viewAppointmentsByDateandTime(int did,String date,String status);
	 
	 @Query("select d.name from Doctor d where d.id=?1")
		public String getDoctorName(int id);
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE Appointment a SET a.status =?1 WHERE a.id =?2")
	 public void cancelAppointment(String status,int aid);
	 
	 @Query("select a from Appointment a where a.doctorid=?1 and a.patientid=?2")
	  public List<Appointment> checkAppointment(int docid, int pid);
	 
	 @Query("select a from Appointment a where a.id=?1")
	  public Appointment checkPatientByAppointmentId(int apid);
	 
	 @Query("select count(a) from Appointment a where a.patientid = ?1")
	 public long totalAppointments(int pid);
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE Appointment a SET a.status = ?2 WHERE a.id = ?1")
	 public void updateAppointmentStatus(int id,String status);
	 
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE Appointment a SET a.prescription=?2 WHERE a.id=?1")
	 public void addPrescription(int id,String prescription);
	 
	 @Query("select count(a) from Appointment a")
	 public long totalAdminAppointments();
	

}
