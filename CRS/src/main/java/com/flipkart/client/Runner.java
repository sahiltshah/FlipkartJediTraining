package com.flipkart.client;

import com.flipkart.handler.UserHandler;
import com.flipkart.utils.Initializer;

public class Runner {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        initializer.initialize();

        UserHandler userHandler=new UserHandler();
        userHandler.showMainLoginMenu();
    }
}
