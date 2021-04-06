package com.flipkart.handler;


import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.DB;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.service.NewUserRegistrationSystem;
import com.flipkart.temporaryDB.OldDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static com.flipkart.utils.ScannerInput.input_obj;

public class AdminHandler {
    public static final Logger logger = LogManager.getLogger(AdminHandler.class);
    public void adminMenu(){
        logger.info("At AdminMenu");
        boolean getOutOfThisMenu = true;
        int choice;
        while (getOutOfThisMenu) {
            System.out.println(
                    "-------------------------------------------\n"+
                    "Enter:\n" +
                    "1 to approve/reject a new student on portal\n" +
                    "2 to add a professor\n" +
                    "3 to add a course\n" +
                    "4 to assign all courses based on preferences\n" +
                    "0 to logout of Admin Menu\n"+
                    "-------------------------------------------");
            choice = input_obj.nextInt();
            switch (choice) {
                case 1: new NewUserRegistrationSystem().peruseStudentRegistration();
                    break;
                case 2: new NewUserRegistrationSystem().addProfessor();
                    break;
                case 3:
                    break;
                case 4:
                    CourseCatalogSystem courseCatalogSystem = new CourseCatalogSystem();
                    //courseCatalogSystem.assignAllCourses();
                    break;
                case 0:
                    getOutOfThisMenu = false;
                    break;
            }
        }
    }






}
