package com.litheshShetty.springBootRESTApiDemo.controller;


import com.litheshShetty.springBootRESTApiDemo.entity.Student;
import com.litheshShetty.springBootRESTApiDemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @RequestMapping("/welcome")
    public String welcome(){
        return "Hello there, welcome to student college";
    }

    @GetMapping("/students")
    public List<Student> getAll(){

        return studentRepo.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        return studentRepo.findById(id).get();
    }

    @PostMapping("/student/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student){
        studentRepo.save(student);
    }

    @PutMapping("/student/update/{id}")
    public Student updateStudent(@PathVariable int id){
        Student student = studentRepo.findById(id).get();
        student.setName("Chirag");
        student.setPercent(88.5f);
        studentRepo.save(student);
        return student;
    }

    @DeleteMapping("/student/delete/{id}")
    public void deleteStudent(@PathVariable int id){
        Student student = studentRepo.findById(id).get();
        studentRepo.delete(student);
    }
}
