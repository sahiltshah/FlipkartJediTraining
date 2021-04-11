package com.flipkart.handler;

import com.flipkart.bean.CourseMap;
import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Student;
import com.flipkart.client.Runner;
import com.flipkart.service.AccountingSystem;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.service.NotificationSystem;
import com.flipkart.service.UserModificationSystem;
import com.flipkart.utils.DebitCardInput;
import org.apache.log4j.Logger;

import java.util.Scanner;

import static com.flipkart.utils.ScannerInput.input_obj;

public class StudentHandler {

    public static final Logger logger = Logger.getLogger(StudentHandler.class);

    public void studentMenu(int studentId) {
        logger.info("studentMenu");

        boolean getOutOfThisMenu = true;
        int choice;
        while (getOutOfThisMenu) {
            System.out.println(
                    "---------------------------------------------------------\n"+
                    "Enter: \n" +
                    "1 to put in subject preferences\n" +
                    "2 to add course post allotment/starting of courses\n" +
                    "3 to remove a course post allotment/starting of course\n" +
                    "4 to pay bills if any amount is pending\n" +
                    "5 to check grades if results declared and \n" +
                    "6 to change password\n"+
                    "0 to logout and go to portal menu\n"+
                    "---------------------------------------------------------\n");
            choice = input_obj.nextInt();
            CourseCatalogSystem courseCatalogSystem = new CourseCatalogSystem();

            switch (choice) {
                case 1:
                    //left
                    courseCatalogSystem.addCoursePreferences(studentId);
                    break;
                case 2:
                    courseCatalogSystem.showAllCourses();

                    int courseId;
                    System.out.println("Enter the courseId you wish to study: ");
                    Scanner sc= new Scanner(System.in);
                    courseId = sc.nextInt();
                    CourseMap courseMap = new CourseMap(studentId,courseId);
                    courseCatalogSystem.addLateralCourse(courseMap);
                    break;
                case 3:
                    courseCatalogSystem.showStudentCourses(studentId);
                    courseCatalogSystem.dropLateralCourse(studentId);
                    break;

                case 4:
                    AccountingSystem accountingSystem = new AccountingSystem();
                    float amount =  accountingSystem.calculateBill(studentId);
                    System.out.println("The total bill incurred is: " + amount);
                    System.out.println("Enter the card details to pay: ");
                    DebitCard studentDebitCard = DebitCardInput.DebitCardStudentInputFunction();
                    accountingSystem.makeTransaction(studentId,studentDebitCard,amount);
                    break;
                case 5:
                    courseCatalogSystem.showStudentGrades(studentId);
                    break;
                case 6:
                    new UserModificationSystem().change_password(studentId);
                    break;
                case 7: new NotificationSystem().viewStudentNotifications(studentId);
                    break;
                case 0:
                    getOutOfThisMenu = false;
                    break;
            }
        }
    }

    public void enterSubjectPreferences(Student student){

    }

}

