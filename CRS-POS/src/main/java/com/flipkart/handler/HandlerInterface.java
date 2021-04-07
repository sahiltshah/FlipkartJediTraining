package com.flipkart.handler;

public class HandlerInterface {
    static interface AdminFunctions{

        void remove_user();
        void approve_user();
        void add_professor();
        void addNewCourseToSystemPrompt();
    }

    static interface NonRegsisteredFunctions{
        void registerUser();
    }

    static interface StudentFunctions{
        void registerCourses();
        void viewGrades();
        void addCourses();
        void dropCourses();
        void payFees();
    }

    static interface ProfessorFunctions{
        void add_grades();
        void register_courses();
        void view_student_details();
    }

    static interface DashboardFunctions{

    }


}
