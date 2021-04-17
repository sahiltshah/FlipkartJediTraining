package com.flipkart.service;

import com.flipkart.bean.Professor;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.temporaryDB.OldDB;
import com.flipkart.validator.AuthenticationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

import static com.flipkart.temporaryDB.OldDB.globalRollNumber;

public class NewUserRegistrationSystem {
    public static final Logger logger = LogManager.getLogger(NewUserRegistrationSystem.class);

    public void registerNewUser(){
        Scanner sc= new Scanner(System.in);
        boolean repeat = true;
        String username="";
        while(repeat) {
            System.out.println("Enter the userName: ");
            username = sc.nextLine();
            if(new AuthenticationValidator().userNameDoesNotExist(username))
                repeat=false;
        }
        System.out.println("Enter the password: ");
        String password = sc.nextLine();
        AuthenticationOperations authenticationOperations = new AuthenticationOperations();
        authenticationOperations.registerNewUserInDB(username,password);
    }

    public void peruseStudentRegistration(){
        logger.info("Perusing Students that may be unregistered");
        int choice;

        System.out.println("Printing all the names that have tried to register. if acceptable, enter 1 else enter 0: ");
        AuthenticationOperations authenticationOperations = new AuthenticationOperations();
        ArrayList<String> nonRegisteredUsers = authenticationOperations.getUnregisteredUsers();
        for(String name : nonRegisteredUsers){
            System.out.println(name + ":");
            Scanner sc= new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice){
                case 0: authenticationOperations.removeUsernameFromLoginMap(name);
                        authenticationOperations.removeUsernameFromLoginDetails(name);
                    break;
                case 1: authenticationOperations.addStudentType(name,globalRollNumber++);
                        new NotificationSystem().addStudentNotification("You have been registered",globalRollNumber-1);
                    break;
            }
        }


    }

    public void addProfessor(){
        System.out.println("Enter the professor details: ");
        int newFacultyId = OldDB.globalFacultyId++;
        Professor new_professor = new Professor(newFacultyId);
        String username,password;
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter the username for this professor: ");
            username=sc.nextLine();
            System.out.println("Enter the password for this professor: ");
            password=sc.nextLine();
            if(new AuthenticationValidator().userNameDoesNotExist(username)){
                AuthenticationOperations authenticationOperations = new AuthenticationOperations();

                authenticationOperations.registerNewUserInDB(username,password);
                authenticationOperations.addNewProfessorToLoginMap(username,newFacultyId);

            }
            else{
                System.out.println("Username input error!");
                logger.error("Username input error!");
            }


    }

}
