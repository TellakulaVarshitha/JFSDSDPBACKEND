package com.klef.jfsd.sdp.controller;

import java.sql.Blob;

import java.time.LocalTime;
import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.sdp.model.Appointment;
import com.klef.jfsd.sdp.model.Patient;
import com.klef.jfsd.sdp.service.PatientService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PatientController 
{
    @Autowired
	private PatientService patientService;
     
     @PostMapping("/patientreg")
     @ResponseBody
     public String patientReg(HttpServletRequest request)
     {   
    	
    	String name=request.getParameter("name");
    	String dob=request.getParameter("dateofbirth");
    	String gender=request.getParameter("gender");
    	String location=request.getParameter("location");
    	String email=request.getParameter("email");
    	String contact=request.getParameter("contact");
    	String password=request.getParameter("password");
    	String bloodgroup=request.getParameter("bloodgroup");
    	Patient p=new Patient();
    	
    	p.setName(name);
    	p.setDateofbirth(dob);
    	p.setGender(gender);
    	p.setLocation(location);
    	p.setEmail(email);
    	p.setContact(contact);
    	p.setPassword(password);
    	p.setBloodgroup(bloodgroup);
    	 return patientService.patientRegistration(p);
    
     }
     
     @PostMapping("uploadimage")
     @ResponseBody
     public String uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
         try {
             int pid = Integer.parseInt(request.getParameter("id"));
             Patient p = patientService.viewPatientById(pid);

             // Check if file is not empty
             if (file.isEmpty()) {
                 return "No file selected.";
             }

             // Convert MultipartFile to byte array
             byte[] bytes = file.getBytes();
             
             // Store the image in the patient's 'image' field (assuming it's a Blob in the DB)
             Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
             p.setImage(blob);

             // Save the patient object with the image
             patientService.patientRegistration(p);

             return "Image uploaded successfully";
         } catch (Exception e) {
             return "Error uploading image: " + e.getMessage();
         }
     }

     
     
     
     @GetMapping("displaypatientimage")
     public ResponseEntity<byte[]> displayprodimagedemo(@RequestParam("id") int id) throws Exception
     {
       Patient p =  patientService.viewPatientById(id);
       byte [] imageBytes = null;
       imageBytes = p.getImage().getBytes(1,(int) p.getImage().length());

       return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
       
     // Response Body, HTTP Status Code, Headers
     }
     
     @PostMapping("/patientlogin")
     @ResponseBody
     public Patient patientLogin(HttpServletRequest request) {
         
             String email = request.getParameter("email");
             String pwd = request.getParameter("password");
            
             Patient p = patientService.checkPatientLogin(email, pwd);
             
             if(p!=null)
             {
             Patient p1=new Patient();
             p1.setId(p.getId());
             p1.setName(p.getName());
             p1.setContact(p.getContact());
             p1.setDateofbirth(p.getDateofbirth());
             p1.setLocation(p.getLocation());
             p1.setGender(p.getGender());
             p1.setEmail(p.getEmail());

             return p1;}
             else
            	 return null;
         
     }
     
     @GetMapping("/viewappointmenttimings")
     @ResponseBody
     public List<LocalTime> viewAppointmentTimings(@RequestParam("did") int did,@RequestParam("date") String date)
     {
    	 
    	
    	 return patientService.getAppointmentsByDateAndTime(did, date);
     }

     
     @PostMapping("/patientappointment")
     @ResponseBody
     public String patientAppointment(HttpServletRequest request)
     {
    	 int pid=Integer.parseInt(request.getParameter("patientid"));
    	 String pemail=request.getParameter("email");
    	 String specalist=request.getParameter("specalist");
    	 int did=Integer.parseInt(request.getParameter("doctorid"));
    	 String date=request.getParameter("date");
    	 String time=request.getParameter("time");
    	 System.out.println(date);
    	 Appointment a=new Appointment();
    	 a.setPatientid(pid);
    	 a.setEmail(pemail);
    	 a.setDoctorid(did);
    	 a.setDate(date);
    	 a.setSpecalist(specalist);
    	 a.setTime(time);
    	 a.setDoctorstatus("Pending");
    	 a.setStatus("Doctor Approval Pending");
    	 a.setPrescription("");
    	 a.setFees(200);
    	 String name=patientService.getDoctorName(did);
    	 a.setDoctorname(name);
    	 String currentTimestamp = java.time.LocalDateTime.now().toString();
         a.setAppointmenttimestamp(currentTimestamp);
    	 String msg=patientService.saveAppointments(a);
    	 return msg;
     }
     
     
     @GetMapping("/viewappointments")
     @ResponseBody
     public List<Appointment> viewAppointments(@RequestParam("pid") int pid)
     {
    	 return patientService.viewAppointments(pid);
     }
     
     @GetMapping("/deleteaccount")
     @ResponseBody
     public String deleteAccount(int pid)
     {
    	 return patientService.deleteAccount(pid);
     }
     
     @PostMapping("/cancelappointment")
     @ResponseBody
     public void cancelAppointment(@RequestParam("aid") int aid)
     {
    	 patientService.cancelAppointment(aid);
     }
     
     @GetMapping("/totalappointments")
     @ResponseBody
     public long totalAppointments(@RequestParam("pid") int pid)
     {
    	 System.out.println(patientService.totalAppointments(pid));
    	 return patientService.totalAppointments(pid);
     }
}
