package com.flipkart.validator;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Course;

public class helperInterface {

    public static interface CourseValidatorFunctions {
        //allotment
        boolean checkSpecifcCourseStudyPossible(Course course);
        boolean checkSpecificCourseTeachPossible(Course course);

        boolean checkAnyCourseToTeachPossible(Professor p, Course course);
        boolean checkProfNoMoreTeachCoursePossible(Professor p, Course course);

        //feasibility

        boolean checkCourseExists(Course course);


        float getSubjectCost(Course course);

    }
    public static interface StudentValidatorFunctions {
        boolean checkIsStudentIdValid(int rollNumber);
        boolean checkCanStudyMoreCourses(int student_index);

    }
}
