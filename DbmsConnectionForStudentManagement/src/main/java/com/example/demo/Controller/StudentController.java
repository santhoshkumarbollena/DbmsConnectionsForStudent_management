package com.example.demo.Controller;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/getAllStudents")
	public List<Student> gettingStudent(Student p1) {
		System.out.println(str.findAll());
		return str.findAll();
	}
	
	@RequestMapping(value="/getStudentById/{Id}")
	public Optional<Student> GetStudById(@PathVariable int Id,Model m) {
		Optional<Student> std=str.findById(Id);
		return std;
	}
	
	@PostMapping("/AddStudent")
	public Student AddingStudent(@RequestBody Student p1) {
		System.out.println(p1.getId());
		str.save(p1);
		return p1;
	}
	
	@DeleteMapping("/deletingStudent/{id}")
	public void DeleteStudent(@PathVariable int id) {
		Student st=str.getOne(id);
		str.delete(st);
		
	}
	@PutMapping("/UpdateStudent")
	public Student UpdatingStudent(@RequestBody Student p1) {
		str.save(p1);
		return p1;
	}
	
	

}
