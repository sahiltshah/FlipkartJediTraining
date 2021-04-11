package com.flipkart.bean;


import java.util.ArrayList;

public class Student extends User{ //extends User implements StudentFunctions


    Integer rollNumber;
    Integer currentSubjectCount;
    public ArrayList<Integer> subjectPreferences;


    public Integer getRollNumber() {
        return this.rollNumber;
    }

    public ArrayList<Integer> getSubjectPreferences() {
        return this.subjectPreferences;
    }


    public Integer getCurrentSubjectCount() {
        return this.currentSubjectCount;
    }

    public void setCurrentSubjectCount(Integer currentSubjectCount) {
        this.currentSubjectCount = currentSubjectCount;
    }

    public void setRollNumber(Integer rollNumber){
        this.rollNumber=rollNumber;
    }

    public Student() {
        this.rollNumber = -1;
        this.currentSubjectCount=0;

    }


}
