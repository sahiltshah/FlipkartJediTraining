package com.flipkart.bean;

public class Course {
    String courseName;
    Integer courseId;
    Integer courseStrength;
    float courseCost;
    Integer facultyId;

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


}
