package com.flipkart.service;

import com.flipkart.bean.DebitCard;
import com.flipkart.bean.SpecialUser;
import com.flipkart.bean.Student;

public class serviceInterface {

    static interface userFunctions{
        SpecialUser login();
        void change_password();
        void forgot_password();
        void deleteStudent(int studentRollNumber);
        void deleteProfessor(int facultyId);
    }

    static interface AccountingSystemFunctions{
        float calculateBill(int studentIndex);
        boolean makeTransaction(int studentIndex, DebitCard debitCard,float billAmount);
    }

    static interface  NewUserRegistrationSystemFunctions{
        void register();
        void check_registration_status();
    }

    static interface CourseCatalogSystemFunctions{
        boolean developNewCourse();
        boolean addCoursePreferences(int student_index);

    }
}
