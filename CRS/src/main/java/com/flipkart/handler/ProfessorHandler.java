package com.flipkart.handler;

import com.flipkart.Exception.CourseException.CourseAlreadyHasFaculty;
import com.flipkart.Exception.CourseException.CourseIndexInvalidException;
import com.flipkart.bean.Course;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.DB;
import com.flipkart.service.AccountingSystem;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.service.UserModificationSystem;
import com.flipkart.utils.DebitCardInput;

import java.util.Scanner;

import static com.flipkart.utils.ScannerInput.input_obj;

public class ProfessorHandler {
    public void professorMenu(int professorId){
        boolean getOutOfThisMenu = true;
        int choice;
        while (getOutOfThisMenu) {
            System.out.println(
                    "------------------------------------\n"+
                    "Enter: \n" +
                    "1 to take up teaching a subject\n" +
                    "2 to stop teaching a subject\n" +
                    "3 to add grades for a subject\n" +
                    "4 to view grades for a subject\n" +
                    "5 to change your password\n"+
                    "0 to logout and go to portal menu\n"+
                    "------------------------------------\n");
            choice = input_obj.nextInt();
            CourseCatalogSystem courseCatalogSystem = new CourseCatalogSystem();

            switch (choice) {
                case 1:
                    courseCatalogSystem.facultyAddCourse(professorId);
                    break;
                case 2:
                    courseCatalogSystem.facultyDropCourse(professorId);
                    break;
                case 3:
                    courseCatalogSystem.addGrades(professorId);
                    break;
                case 4:
                    courseCatalogSystem.viewGrades(professorId);
                    break;
                case 5:
                    break;
                case 0:
                    getOutOfThisMenu = false;
                    break;
            }
        }

    }
}
