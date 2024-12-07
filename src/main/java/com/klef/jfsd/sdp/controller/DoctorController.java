package com.klef.jfsd.sdp.controller;



import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.sdp.model.Appointment;
import com.klef.jfsd.sdp.model.Doctor;
import com.klef.jfsd.sdp.model.Patient;
import com.klef.jfsd.sdp.service.DoctorService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class DoctorController 
{  
	@Autowired
	private DoctorService doctorService;
	 
	@GetMapping("/")
	@ResponseBody
	public String Home()
	{
		return "Running";
	}
	
	@PostMapping("/docreg")
	@ResponseBody
	public String registerDoctor(HttpServletRequest request,@RequestParam("doctorimage") MultipartFile file) throws Exception
	{
	  String msg=null;
	  try {
	    String name=request.getParameter("name");
	    String dob=request.getParameter("dateofbirth");
	    String gender=request.getParameter("gender");
	    String location=request.getParameter("location");
	    String specialization=request.getParameter("specialization");
	    String email=request.getParameter("email");
	    String contact=request.getParameter("contact");
	    String password=request.getParameter("password");
	     byte[] bytes = file.getBytes();
	      Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
	      Doctor d=new Doctor();
	      d.setContact(contact);
	      d.setDateofbirth(dob);
	      d.setEmail(email);
	      d.setGender(gender);
	      d.setLocation(location);
	      d.setPassword(password);
	      d.setName(name);
	      d.setSpecialization(specialization);
	      d.setStatus("Registered");
	      d.setImage(blob);
	     
	      msg=doctorService.doctorRegistration(d);
	      return msg;
	    
	  }
	  catch(Exception e)
	  {
	    return e.getMessage();
	  }
	}
    
    @GetMapping("/docprofile")
    @ResponseBody
    public Doctor doctorProfile(@RequestParam String email)
    {
    	return doctorService.doctorProfile(email);
    }
    
    @PostMapping("/doclogin")
    @ResponseBody
    public Doctor doctorLogin(@RequestParam String email , @RequestParam String password)
    {
    	Doctor d=doctorService.checkDoctorLogin(email, password);
    	
    		Doctor temp=new Doctor();
    		if(d!=null)
    		{
    		temp.setId(d.getId());
    		temp.setName(d.getName());
    		temp.setGender(d.getGender());
    		temp.setEmail(d.getEmail());
    		temp.setDateofbirth(d.getDateofbirth());
    		temp.setContact(d.getContact());
    		temp.setLocation(d.getLocation());
    		temp.setSpecialization(d.getPassword());
    		temp.setStatus(d.getStatus());
    		return temp;
    		}
    		return d;
    	
    }
    
    @GetMapping("doctorprofile/{id}")
    public ResponseEntity<Doctor> getDoctorProfile(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorProfileById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
    
    @GetMapping("doctorprofile/image/{id}")
    public ResponseEntity<byte[]> getDoctorImage(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorProfileById(id);
        if (doctor != null && doctor.getImage() != null) {
            try {
                byte[] imageBytes = doctor.getImage().getBytes(1, (int) doctor.getImage().length());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(imageBytes);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/docappointments")
    @ResponseBody
    public List<Appointment> viewDoctorAppointments(@RequestParam int  docid)
    { 
    	return doctorService.viewDoctorAppointments(docid);
    }
    
    @GetMapping("/viewdocbyspecalisation")
    @ResponseBody
    public List<Doctor> viewDoctorBySpecalisation(@RequestParam String spec)
    {  
    	return doctorService.getDocBySpecalisation(spec);
    }
    
    @PostMapping("/updateappointmentstatus")
    @ResponseBody
    public String updateAppointmentStatus(@RequestParam("id") int id,@RequestParam("status") String status)
    {
    	 doctorService.updateAppointmentStatus(id, status);
    	 return "updated";
    	 
    }
    
    @PostMapping("/addprescription")
    @ResponseBody
    public String addPrescription(@RequestParam("id") int id,@RequestParam("prescription") String prescription)
    {
    	doctorService.addPrescription(id, prescription);
    	return "Added";
    }
    
    @GetMapping("/viewdocpatients")
    @ResponseBody
    public List<Patient> viewdocpatients(@RequestParam("id") int id)
    {
    	return doctorService.viewDocPatients(id);
    }
    
    @Autowired 
    private JavaMailSender mailSender; 
  
    @PostMapping("sendemail") 
    @ResponseBody
    public String sendEmail(HttpServletRequest request) throws Exception  
    { 
        String name = request.getParameter("name"); 
        String toemail = request.getParameter("email"); 
        String link = request.getParameter("link"); 
       
     System.out.println(toemail);
     MimeMessage mimeMessage = mailSender.createMimeMessage();
     MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

     // Set the email details
     helper.setTo(toemail);
     helper.setSubject("Online Consultation Meeting");
     helper.setFrom("tellakulavarshitha@gmail.com");

     // Create the email content
     String htmlContent =
         "<h3>Online Consultation Details</h3>" +
         "<p><strong>Doctor:</strong>  " + name + "</p>" +
         "<p><strong>Meeting Link:</strong> <a href='" + link + "'>" + link + "</a></p>";

     helper.setText(htmlContent, true); // Enable HTML content

     // Send the email
     mailSender.send(mimeMessage);  
  
            return "Mail Sent Successfully"; 
    } 
    
}
