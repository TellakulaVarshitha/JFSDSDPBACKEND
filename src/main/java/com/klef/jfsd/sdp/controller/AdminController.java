package com.klef.jfsd.sdp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klef.jfsd.sdp.model.Admin;
import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;
import com.klef.jfsd.sdp.service.AdminService;

@Controller
public class AdminController 
{
     @Autowired
	private AdminService adminService;
     
     @GetMapping("/viewdoctors")
     @ResponseBody
     public List<Doctor> viewDoctors()
     {
    	 return adminService.viewAllDoctors();
     }
     
     @PostMapping("/adminlogin")
     @ResponseBody 
     public String adminLogin(@RequestBody Admin a)
     {
    	 String uname=a.getUsername();
    	 String pwd=a.getPassword();
    	 Admin ad=adminService.checkAdminLogin(uname, pwd);
    	 if(ad!=null)
    		 return "Admin Logged in successfully";
    	 else
    		 return"Admin login failed";
     }
     
     @GetMapping("/viewpatients")
     @ResponseBody
     public List<Patient> viewPatients()
     {
    	 List<Patient>p= adminService.viewAllPatients();
    	 List<Patient>temp=new ArrayList<Patient>();
    	 if(p!=null)
    	 {
    	 for(Patient pt:p)
    	 {
    		 Patient p1=new Patient();
    		 p1.setId(pt.getId());
    		 p1.setName(pt.getName());
    		 p1.setEmail(pt.getEmail());
    		 p1.setDateofbirth(pt.getDateofbirth());
    		 p1.setGender(pt.getGender());
    		 p1.setContact(pt.getContact());
    		 p1.setLocation(pt.getLocation());
    		 p1.setPassword(pt.getPassword());
    		 temp.add(p1);
    		 
    	 }
    	 }
    	 return temp;
     }
     
     @PutMapping("/updatedocstatus")
     @ResponseBody
     public String updateStatus(@RequestParam String email,@RequestParam String status)
     {
    	 adminService.updateDoctorStatus(email,status);
    	 return "Updated";
     }
     
     @GetMapping("/deletedoctor")
     @ResponseBody
     public String deleteDoctor(@RequestParam int docid)
     {
    	return  adminService.deleteDoctor(docid);
     }
     
     @GetMapping("/totalapp")
     @ResponseBody
     public ResponseEntity<Long> totalAppointments()
     {
    	 long totalAppointments= adminService.totalAdminAppointments();
    	
    	 return ResponseEntity.ok(totalAppointments);
     }
     
     @GetMapping("/totaldoc")
     @ResponseBody
     public ResponseEntity<Long> totalDoctors()
     {
    	 long totalAppointments= adminService.totalDoctors();
    	 
    	 return ResponseEntity.ok(totalAppointments);
     }
     
     @GetMapping("/totalpatient")
     @ResponseBody
     public ResponseEntity<Long> totalPatients()
     {
    	 long totalAppointments= adminService.totalPatients();
    	 
    	 return ResponseEntity.ok(totalAppointments);
     }
     
     
     
}
