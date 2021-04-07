package com.flipkart.client;

import com.flipkart.dao.DB;
import com.flipkart.handler.ProfessorHandler;
import com.flipkart.handler.StudentHandler;
import com.flipkart.handler.UserHandler;
import com.flipkart.service.AccountingSystem;
import com.flipkart.utils.Initializer;
import com.flipkart.utils.ScreenWork;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Runner {

    public static final Logger logger = LogManager.getLogger(Runner.class);
    public static void main(String[] args) {


        try {
            logger.info("About to initialize");
            ScreenWork.mainHeadingPrint();

            Initializer initializer = new Initializer();
            initializer.initialize();



            UserHandler userHandler=new UserHandler();
            logger.info("About to go to Main Menu");
            userHandler.showMainLoginMenu();


        }
        catch (Exception ex){
            ex.getMessage();
        }
        finally {
            DB x = DB.getInstance();
            x.closeConnection();
        }

    }
}


