package com.flipkart.bean;

public class Course {
    public Course(String courseName, Integer courseId, Integer courseStrength, float courseCost, Integer facultyId) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseStrength = courseStrength;
        this.courseCost = courseCost;
        this.facultyId = facultyId;
    }

    public Course(int courseId, String courseName, int courseStrength, float courseCost, int facultyId) {
        this.courseCost=courseCost;
        this.courseId=courseId;
        this.courseName=courseName;
        this.courseStrength=courseStrength;
        this.facultyId=facultyId;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseStrength() {
        return courseStrength;
    }

    public void setCourseStrength(Integer courseStrength) {
        this.courseStrength = courseStrength;
    }

    public float getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(float courseCost) {
        this.courseCost = courseCost;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    String courseName;
    Integer courseId;
    Integer courseStrength;
    float courseCost;
    Integer facultyId;


    public Course(){

    }


    public void printCourse(){
        System.out.println("ID: " + this.courseId + " | CourseName: " + this.courseName + " | Strength: " + this.courseStrength + " | Cost: " + this.courseCost + " | Faculty: " + this.facultyId);
    }

}
