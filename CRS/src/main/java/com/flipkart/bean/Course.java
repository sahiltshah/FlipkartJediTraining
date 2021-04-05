package com.flipkart.bean;

public class Course {
    String courseName;
    Integer courseId;
    Integer courseStrength;
    float courseCost;
    Integer facultyId;

    public Course(String courseName, int courseId, int courseStrength, float courseCost, int facultyId) {
        this.courseCost=courseCost;
        this.courseId=courseId;
        this.courseName=courseName;
        this.courseStrength=courseStrength;
        this.facultyId=facultyId;
    }

    public Course(){
        this.courseId=-1;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public float getcourseCost() {
        return courseCost;
    }

    public void setcourseCost(float courseCost) {
        this.courseCost = courseCost;
    }

    public String getcourseName() {
        return courseName;
    }

    public void setcourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getcourseId() {
        return courseId;
    }

    public void setcourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public int getcourseStrength() {
        return courseStrength;
    }

    public void setcourseStrength(int courseStrength) {
        this.courseStrength = courseStrength;
    }
    public void printCourse(){
        System.out.println(this.courseId + " | " + this.courseName + " | " + this.courseStrength + " | " + this.courseCost);
    }


}
