package com.flipkart.service;

import com.flipkart.dao.AccountingSystemDBOperations;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.dao.DB;
import com.flipkart.validator.AuthenticationValidator;

import java.util.ArrayList;
import java.util.Scanner;

import static com.flipkart.temporaryDB.OldDB.globalRollNumber;

public class NewUserRegistrationSystem {
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
            }
        }


    }

}
