package com.flipkart.bean;

import java.io.Serializable;

public class CourseMap implements Serializable {
    public CourseMap() {

    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer studentId;
    public Integer courseId;

    public CourseMap(int studentId, int courseId) {
        this.studentId=studentId;
        this.courseId=courseId;
    }



}
