package com.flipkart.service;
import com.flipkart.exception.AuthenticationException.UserDoesNotExistException;
import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.validator.AuthenticationValidator;
import org.apache.log4j.Logger;

import java.util.Scanner;


public class UserModificationSystem implements serviceInterface.userFunctions {

    public static final Logger logger = Logger.getLogger(UserModificationSystem.class);



    @Override
    public SpecialUser login() {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        return(AuthenticationValidator.authenticate(userName,password));
    }

    public SpecialUser login(String username,String password) {

        return(AuthenticationValidator.authenticate(username,password));
    }


    @Override
    public void change_password(int studentId, String newPassword) {
        try {
            AuthenticationOperations authenticationOperations = new AuthenticationOperations();
            Scanner sc = new Scanner(System.in);
            String userName = authenticationOperations.getUserNameFromStudentId(studentId);
            if (userName.equals(""))
                throw new UserDoesNotExistException();



            authenticationOperations.changePassword(userName, newPassword);
        } catch (UserDoesNotExistException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
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
