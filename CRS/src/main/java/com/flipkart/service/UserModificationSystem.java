package com.flipkart.service;
import com.flipkart.Exception.AuthenticationException.UserDoesNotExistException;
import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.dao.DB;
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
    public void change_password(int studentId) {
        try {
            AuthenticationOperations authenticationOperations = new AuthenticationOperations();
            Scanner sc = new Scanner(System.in);
            String userName = authenticationOperations.getUserNameFromStudentId(studentId);
            if (userName.equals(""))
                throw new UserDoesNotExistException();

            System.out.println("Enter the new password: ");
            String newPassword = sc.nextLine();

            authenticationOperations.changePassword(userName, newPassword);
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        }

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
