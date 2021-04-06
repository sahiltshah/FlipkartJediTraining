package com.flipkart.handler;

import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.DB;
import com.flipkart.service.NewUserRegistrationSystem;
import com.flipkart.validator.AuthenticationValidator;

import java.util.Scanner;

public class NewUserHandler {
    public void registerNewUser(){
        new NewUserRegistrationSystem().registerNewUser();
    }

    public void newUserMenu(int x) {
        System.out.println("You aren't registered yet. Kindly wait until you are registered");
    }
}
