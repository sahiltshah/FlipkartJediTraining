package com.flipkart.service;
import com.flipkart.bean.SpecialUser;
import com.flipkart.validator.AuthenticationValidator;

import java.util.Scanner;

import static com.flipkart.utils.ScannerInput.input_obj;


public class UserModificationSystem implements serviceInterface.userFunctions {


    @Override
    public SpecialUser login() {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        return(AuthenticationValidator.authenticate(userName,password));
    }

    @Override
    public void change_password() {


    }

    @Override
    public void forgot_password() {

    }

    @Override
    public void deleteStudent(int studentRollNumber) {

    }

    @Override
    public void deleteProfessor(int facultyId) {

    }


}
