package com.flipkart.bean.wrappers;

import com.flipkart.bean.Grade;

import java.util.ArrayList;

public class GradesWrapper {
    public GradesWrapper() {
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public ArrayList<Grade> grades;

    public GradesWrapper(ArrayList<Grade> grades){
        this.grades=grades;
    }
}
