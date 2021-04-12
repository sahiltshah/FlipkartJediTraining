package com.flipkart.handler;

import com.flipkart.global.GlobalVariables;
import com.flipkart.service.NewUserRegistrationSystem;

public class NewUserHandler {
    public void registerNewUser(){
        new NewUserRegistrationSystem().registerNewUser();
    }

    public void newUserMenu(int x) {
        //System.out.println("You aren't registered yet. Kindly wait until you are registered");
        GlobalVariables.appendException("You aren't registered yet. Kindly wait until you are registered");
    }
}
