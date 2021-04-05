package com.flipkart.client;

import com.flipkart.dao.DB;
import com.flipkart.handler.UserHandler;
import com.flipkart.utils.Initializer;

public class Runner {
    public static void main(String[] args) {
        try {
            Initializer initializer = new Initializer();
            initializer.initialize();

            UserHandler userHandler=new UserHandler();
            userHandler.showMainLoginMenu();

        }
        catch (Exception ex){
            ex.getMessage();
        }
        finally {
            DB.closeConnection();
        }

    }
}
