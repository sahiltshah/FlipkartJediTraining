package com.flipkart.handler;

import com.flipkart.bean.SpecialUser;
import com.flipkart.service.UserModificationSystem;
import com.flipkart.utils.ScreenWork;

import static com.flipkart.utils.ScannerInput.input_obj;

public class UserHandler {


    UserModificationSystem userModificationSystem;
//modules complete
    public void showMainLoginMenu(){
        UserModificationSystem userModificationSystem = new UserModificationSystem();
        int count=100; // very large number
        int choice;
        int userReturn;
        SpecialUser specialUser;
        while(count-- >0){
            ScreenWork.clearScreen();
            System.out.println(
                    "---------------------------------------------------------\n"+
                    "Enter 1 to login as user and 2 to register as a student: \n"+
                    "---------------------------------------------------------\n"
            );
            choice= input_obj.nextInt();
            System.out.println(choice);
            switch (choice){
                case 1: specialUser = userModificationSystem.login();
                        switch(specialUser.type){
                            case -1: break; //invalid
                            case 0: AdminHandler adminHandler=new AdminHandler();
                                    adminHandler.adminMenu();
                                    break;
                            case 1: StudentHandler studentHandler=new StudentHandler();
                                    studentHandler.studentMenu(specialUser.id);
                                    break;
                            case 2: ProfessorHandler professorHandler=new ProfessorHandler();
                                    professorHandler.professorMenu(specialUser.id);
                                    break;
                            case 3: NewUserHandler newUserHandler = new NewUserHandler();
                                    newUserHandler.newUserMenu(specialUser.id);
                                    break;
                            default:
                                System.out.println("Wrong choice");
                        }

                        break;
                case 2: NewUserHandler newUserHandler=new NewUserHandler();
                        newUserHandler.registerNewUser();
                        break;
                default: break;
            }

        }

    }









}
