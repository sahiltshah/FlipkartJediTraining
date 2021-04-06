package com.flipkart.client;

import com.flipkart.dao.DB;
import com.flipkart.handler.ProfessorHandler;
import com.flipkart.handler.StudentHandler;
import com.flipkart.handler.UserHandler;
import com.flipkart.service.AccountingSystem;
import com.flipkart.utils.Initializer;
import com.flipkart.utils.ScreenWork;

public class Runner {
    public static void main(String[] args) {
        try {

            ScreenWork.mainHeadingPrint();

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


