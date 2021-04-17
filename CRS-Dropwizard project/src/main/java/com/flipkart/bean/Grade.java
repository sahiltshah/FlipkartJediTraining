package com.flipkart.bean;

public class Grade {
    public Grade() {
    }

    public Grade(int studentId, int courseId, char grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public int studentId;
    public int courseId;
    public char grade;

    public void printGrade(){
        System.out.println("Course: " + this.courseId + " Grade: " + this.grade);
    }
}
