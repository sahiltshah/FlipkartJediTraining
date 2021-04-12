package com.flipkart.handler;

import com.flipkart.service.CourseCatalogSystem;

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
                    courseCatalogSystem.showAllCourses();
                    System.out.println("Enter a courseId that does not have already have a faculty");
                    Scanner sc= new Scanner(System.in);
                    int courseId = sc.nextInt();
                    courseCatalogSystem.facultyAddCourse(professorId,courseId);
                    break;
                case 2:
                    System.out.println("Enter a courseId that you would like to opt out of teaching: ");
                    Scanner sc2= new Scanner(System.in);
                    int courseId2 = sc2.nextInt();
                    courseCatalogSystem.facultyDropCourse(professorId,courseId2);
                    break;
                case 3:
                    courseCatalogSystem.addGrades(professorId);
                    break;
                case 4:
                    System.out.println("Enter a courseId that you would like to see+search the grade of: ");
                    Scanner sc1= new Scanner(System.in);
                    int courseId1 = sc1.nextInt();
                    courseCatalogSystem.viewGrades(professorId,courseId1);
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
