package com.flipkart.handler;

import com.flipkart.bean.DebitCard;
import com.flipkart.bean.Student;
import com.flipkart.service.AccountingSystem;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.utils.DebitCardInput;

import static com.flipkart.utils.ScannerInput.input_obj;

public class StudentHandler {
    public void studentMenu(int student_index) {
        boolean getOutOfThisMenu = true;
        int choice;
        while (getOutOfThisMenu) {
            System.out.println("Enter: \n" +
                    "1 to put in subject preferences\n" +
                    "2 to add or remove courses post allotment/starting of courses\n" +
                    "3 to pay bills if any amount is pending\n" +
                    "4 to check grades if results declared and \n" +
                    "0 to logout and go to portal menu");
            choice = input_obj.nextInt();

            switch (choice) {
                case 1:
                    CourseCatalogSystem courseCatalogSystem = new CourseCatalogSystem();
                    courseCatalogSystem.addCoursePreferences(student_index);
                    break;
                case 2:

                    break;
                case 3:
                    AccountingSystem accountingSystem = new AccountingSystem();
                    float amount =  accountingSystem.calculateBill(student_index);
                    System.out.println("The total bill incurred is: " + amount);
                    System.out.println("Enter the card details to pay: ");
                    DebitCard studentDebitCard = DebitCardInput.DebitCardInputFunction();
                    accountingSystem.makeTransaction(student_index,studentDebitCard,amount);
                    break;
                case 4:
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
