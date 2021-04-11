package com.flipkart.handler;

import com.flipkart.bean.SpecialUser;
import com.flipkart.bean.User;
import com.flipkart.client.Runner;
import com.flipkart.service.UserModificationSystem;
import com.flipkart.utils.ScreenWork;
import org.apache.log4j.Logger;

import static com.flipkart.utils.ScannerInput.input_obj;

public class UserHandler {

    public static final Logger logger = Logger.getLogger(UserHandler.class);
    UserModificationSystem userModificationSystem;
//modules complete
    public void showMainLoginMenu(){
        userModificationSystem = new UserModificationSystem();
        int count=100; // very large number
        int choice;
        int userReturn;
        SpecialUser specialUser;
        logger.info("At Main Menu");
        while(count-- >0){
            ScreenWork.clearScreen();
            System.out.println(
                    "---------------------------------------------------------\n"+
                    "-----------------------Main Menu-------------------------\n"+
                    "\n"+
                    "Enter 1 to login as user and 2 to register as a student: \n"+
                    "---------------------------------------------------------\n"
            );
            choice= input_obj.nextInt();
            logger.info("Choice: " + choice);
            switch (choice){
                case 1: specialUser = userModificationSystem.login();
                        logger.info("Authentication complete");
                        switch(specialUser.type){
                            case -1: break; //invalid
                            case 0: logger.info("case 0");
                                    AdminHandler adminHandler=new AdminHandler();
                                    adminHandler.adminMenu();
                                    break;
                            case 1: logger.info("case 1");
                                    StudentHandler studentHandler=new StudentHandler();
                                    studentHandler.studentMenu(specialUser.id);
                                    break;
                            case 2: logger.info("case 2");
                                    ProfessorHandler professorHandler=new ProfessorHandler();
                                    professorHandler.professorMenu(specialUser.id);
                                    break;
                            case 3: logger.info("case 3");
                                    NewUserHandler newUserHandler = new NewUserHandler();
                                    newUserHandler.newUserMenu(specialUser.id);
                                    break;
                            default:
                                    logger.info("case Default");
                                    System.out.println("Wrong choice");
                        }

                        break;
                case 2: NewUserHandler newUserHandler=new NewUserHandler();
                        newUserHandler.registerNewUser();
                        logger.info("New User Handler part complete");
                        break;
                default: break;
            }

        }

    }









}
