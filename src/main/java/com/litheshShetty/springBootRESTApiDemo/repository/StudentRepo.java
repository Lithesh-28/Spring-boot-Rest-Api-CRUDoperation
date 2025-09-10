package com.litheshShetty.springBootRESTApiDemo.repository;

import com.litheshShetty.springBootRESTApiDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {

}