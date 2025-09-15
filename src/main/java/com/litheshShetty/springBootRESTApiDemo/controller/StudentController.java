package com.litheshShetty.springBootRESTApiDemo.controller;


import com.litheshShetty.springBootRESTApiDemo.entity.Student;
import com.litheshShetty.springBootRESTApiDemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>( "Hello there, welcome to student college",HttpStatus.OK);
    }

    @GetMapping("/students")
    @PreAuthorize("hasRole('USER')")
    public List<Student> getAll(){

        return studentRepo.findAll();
    }

    @GetMapping("/students/{id}")
    @PreAuthorize("hasRole('USER')")
    public Student getStudent(@PathVariable int id){
        return studentRepo.findById(id).get();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        Student std = studentRepo.save(student);
        return std;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(@PathVariable int id){
        Student student = studentRepo.findById(id).get();
        student.setName("Chirag");
        student.setPercent(88.5f);
        studentRepo.save(student);
        return student;
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@PathVariable int id){
        Student student = studentRepo.findById(id).get();
        studentRepo.delete(student);
    }
}
