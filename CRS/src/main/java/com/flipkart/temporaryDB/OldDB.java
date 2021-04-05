package com.flipkart.temporaryDB;

import com.flipkart.bean.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DB {
    //temporary DB equivalent tables
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Professor> professors = new ArrayList<>();
    public static Admin admin = new Admin();

    public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    public static ArrayList<DebitCard> debitCards = new ArrayList<>();
    public static ArrayList<CourseMap> courseMaps = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();
    public static HashMap<String,String> loginDetails = new HashMap<>();
    public static HashMap<String,SpecialUser> loginMap = new HashMap<>();

    //Global variables
    public static int globalTransactionId = 1;
    public static int globalRollNumber = 101;
    public static int globalFacultyId = 9001;


}
