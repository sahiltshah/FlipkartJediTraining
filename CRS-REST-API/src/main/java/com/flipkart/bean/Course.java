package com.flipkart.bean;

public class Course {
    public Course(String courseName, Integer courseId, Integer courseStrength, float courseCost, Integer facultyId) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseStrength = courseStrength;
        this.courseCost = courseCost;
        this.facultyId = facultyId;
    }

    String courseName;
    Integer courseId;
    Integer courseStrength;
    float courseCost;
    Integer facultyId;

    public Course( int courseId, String courseName, int courseStrength, float courseCost, int facultyId) {
        this.courseCost=courseCost;
        this.courseId=courseId;
        this.courseName=courseName;
        this.courseStrength=courseStrength;
        this.facultyId=facultyId;
    }

    public Course(){
        this.courseName = "";
        this.courseId = -1;
        this.courseStrength = 0;
        this.courseCost = 0;
        this.facultyId = -1;
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
        System.out.println("ID: " + this.courseId + " | CourseName: " + this.courseName + " | Strength: " + this.courseStrength + " | Cost: " + this.courseCost + " | Faculty: " + this.facultyId);
    }

}
