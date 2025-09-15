package com.litheshShetty.springBootRESTApiDemo.controller;

import com.litheshShetty.springBootRESTApiDemo.entity.Student;
import com.litheshShetty.springBootRESTApiDemo.repository.StudentRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    StudentRepo studentRepo;

    @InjectMocks
    StudentController studentController;

    @BeforeAll
    public static void init(){
        System.out.println("BeforeAll is called");
    }

    @BeforeEach
    public void initForEach(){
        System.out.println("BeforeEach is called");
    }

    @Test
    void creatStudentShouldAddStudentsSuccessfully(){

        Student student = new Student();
        student.setRollNo(1);
        student.setName("Lithesh");
        student.setPercent(86.6f);
        student.setBranch("BCA");

        Mockito.when(studentRepo.save(student)).thenReturn(student);

        Student addedStudent = studentController.createStudent(student);

        Assertions.assertEquals(student.getRollNo(),addedStudent.getRollNo());
        Assertions.assertNotNull(addedStudent);
        Assertions.assertTrue(addedStudent.getRollNo()==1);
        Assertions.assertEquals(student.getName(),addedStudent.getName());

        System.out.println("My First test");
    }

    @Test
    public void deleteByIdShouldDeleteSuccessfully() {
        Student student = new Student();
        student.setRollNo(1);
        student.setName("Test Student");
        student.setPercent(85.0f);
        student.setBranch("BCA");

        // Mock findById to return the student
        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(student));

        // Mock delete to do nothing
        Mockito.doNothing().when(studentRepo).delete(student);

        // Call controller method
        studentController.deleteStudent(1);

        // Verify delete was called once
        Mockito.verify(studentRepo, Mockito.times(1)).delete(student);
    }

    @AfterEach
    public void cleanUp(){
        System.out.println("AfterEach is called");
    }

    @AfterAll
    public static void destroy(){
        System.out.println("AfterAll is called ");
    }

}