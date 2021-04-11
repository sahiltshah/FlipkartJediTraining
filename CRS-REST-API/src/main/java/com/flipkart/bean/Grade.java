package com.flipkart.bean;

public class Grade {
    public Grade(int studentId, int courseId, char grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int studentId;
    public int courseId;
    public char grade;

    public void printGrade(){
        System.out.println("Course: " + this.courseId + " Grade: " + this.grade);
    }
}
