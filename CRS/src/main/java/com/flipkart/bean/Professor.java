package com.flipkart.bean;

import java.util.ArrayList;
import static com.flipkart.temporaryDB.DB.globalFacultyId;
public class Professor extends User{ //extends User implements ProfessorFunctions
    public Integer getFacultyId() {
        return facultyId;
    }

    public ArrayList<Integer> getSubjectsUndertaken() {
        return subjectsUndertaken;
    }

    void setFacultyId(Integer facultyId){
        this.facultyId=facultyId;
    }


    public Professor() {
        this.facultyId = globalFacultyId++;
    }

    Integer facultyId;
    public ArrayList<Integer> subjectsUndertaken;


}
