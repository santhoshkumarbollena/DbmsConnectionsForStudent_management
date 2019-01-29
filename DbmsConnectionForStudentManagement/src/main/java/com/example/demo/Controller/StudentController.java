package com.example.demo.Controller;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepo;
@RestController
public class StudentController {
	
	@Autowired
	private StudentRepo str;
	
	@GetMapping(value="/getAllStudents",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Student> gettingStudent(Student p1) {
	//	System.out.println(str.findAll());
		return str.findAll();
	}
	
	@GetMapping(value="/getStudentById/{Id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Student GetStudById(@PathVariable int Id,Model m) {
		Student std=str.findById(Id).orElse(new Student());
		//System.out.println("getStudentbyid is called"+Id);
		//System.out.println(std.getName());
		return std;
	}
	 JavaMailSender jms;
	@Autowired
	 public StudentController(JavaMailSender jms) {
		 this.jms=jms;
	 }
	
	@PostMapping("/AddStudent")
	public Student AddingStudent(@RequestBody Student p1) {
		System.out.println(p1.getEmail());
		
		str.save(p1);
		//Send mail from here
		
		try {
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(p1.getEmail());
		mail.setFrom("santhoshbollena@gmail.com");
		mail.setSubject("Registration Notification");
		mail.setText("You have been registered to our student management Portal ,Tank you");
		jms.send(mail);
		
		}catch(Exception ex) {
			System.out.println("exception "+ex);
		}
		System.out.println("email");
		return p1;
	}
	
	@DeleteMapping("/deletingStudent/{id}")
	public void DeleteStudent(@PathVariable int id) {
		Student st=str.getOne(id);
		str.delete(st);
		
	}
	@PutMapping("/UpdateStudent/{id}")
	public Student UpdatingStudent(@RequestBody Student p1) {
		str.save(p1);
		return p1;
	}
	
	

}