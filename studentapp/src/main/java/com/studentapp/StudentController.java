package com.studentapp;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
	
	private final StudentRepository repo;

	public StudentController(StudentRepository repo) {
		super();
		this.repo = repo;
	}
	
	@GetMapping
	public List<Student> getAllStudents() {
		return repo.findAll();
	}
	
	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return repo.save(student);
	}
	
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student studentd) {
		return repo.findById(id).map(student -> {
			student.setName(studentd.getName());
			student.setEmail(studentd.getEmail());
			return repo.save(student);
		}).orElseThrow(() -> new RuntimeException("student not found with id: "+ id));
	}
	
	@DeleteMapping("/id/{id}")
	public void deleteStudent(@PathVariable Long id) {
		repo.deleteById(id);
	}
}
