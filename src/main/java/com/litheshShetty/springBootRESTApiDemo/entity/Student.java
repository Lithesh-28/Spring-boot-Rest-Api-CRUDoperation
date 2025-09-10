package com.litheshShetty.springBootRESTApiDemo.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    @Column
    private String name;

    @Column
    private float percent;

    @Column
    private String branch;

    public int getRollNo() {
        return rollNo;
    }

    public Student(){}

    public Student(String name, float percent, String branch) {
        this.name = name;
        this.percent = percent;
        this.branch = branch;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Student[" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", percent=" + percent +
                ", branch='" + branch + '\'' +
                ']';
    }
}
