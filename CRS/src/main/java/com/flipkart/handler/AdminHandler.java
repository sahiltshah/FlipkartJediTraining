package com.flipkart.handler;


import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.temporaryDB.OldDB;

import static com.flipkart.utils.ScannerInput.input_obj;

public class AdminHandler {
    //default username is: Admin
    public void adminMenu(){
        boolean getOutOfThisMenu = true;
        int choice;
        while (getOutOfThisMenu) {
            System.out.println("Enter:" +
                    "1 to approve/reject a new student on portal\n" +
                    "2 to add a professor\n" +
                    "3 to add a course\n" +
                    "4 to assign all courses based on preferences\n" +
                    "0 to logout of Admin Menu");
            choice = input_obj.nextInt();
            switch (choice) {
                case 1: peruseStudentRegistration();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    CourseCatalogSystem courseCatalogSystem = new CourseCatalogSystem();
                    courseCatalogSystem.assignAllCourses();
                    break;
                case 0:
                    getOutOfThisMenu = false;
                    break;
            }
        }
    }

    void peruseStudentRegistration(){
        int choice;
        System.out.println("Printing all the names that have tried to register. if acceptable, enter 1 else enter 0: ");

        for (Student student : OldDB.admin.studentsBuffer ){
            System.out.println(student.getName());
            choice=input_obj.nextInt();
            if(choice==1){
                student.setRollNumber(OldDB.globalRollNumber++);
            }
            else
                OldDB.admin.studentsBuffer.remove(student);

        }

    }

    void addProfessor(){
        System.out.println("Enter the professor details: ");
        Professor new_professor = new Professor();

    }


}
