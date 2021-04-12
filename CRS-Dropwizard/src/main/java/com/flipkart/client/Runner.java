package com.flipkart.client;

import com.flipkart.bean.CourseMap;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.utils.DB;
import com.flipkart.handler.UserHandler;
import com.flipkart.utils.Initializer;
import com.flipkart.utils.ScreenWork;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Runner {

    public static final Logger logger = Logger.getLogger(Runner.class);
    public static void main(String[] args) {


        try {

            logger.error("");
            logger.error("");
            logger.error("");
            logger.error("");
            logger.error("");
            logger.error("");
            logger.error("");

            logger.info("About to initialize");
            ScreenWork.mainHeadingPrint();

            Initializer initializer = new Initializer();
            initializer.initialize();

            System.out.println("CourseMaps: ");
            ArrayList<CourseMap> courseMaps = new CourseCatalogDBOperations().getAllCourseMaps();
            for(CourseMap courseMap : courseMaps){
                System.out.print(courseMap.courseId + " " + courseMap.studentId);
            }

            UserHandler userHandler=new UserHandler();
            logger.info("About to go to Main Menu");
            userHandler.showMainLoginMenu();


        }
        catch (Exception ex){
            ex.getMessage();
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        }
        finally {
            DB x = DB.getInstance();
            x.closeConnection();
        }

    }
}


