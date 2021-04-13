package com.flipkart.validator;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;

public class ValidatorInterface {

    public static interface CourseValidatorFunctions {
        //allotment
        boolean checkSpecifcCourseStudyPossible(Course course);
        boolean checkSpecificCourseTeachPossible(Course course);

        boolean checkAnyCourseToTeachPossible(Professor p, Course course);
        boolean checkProfNoMoreTeachCoursePossible(Professor p, Course course);


        Course checkCourseExists(int courseId);

        float getSubjectCost(Course course);

        boolean checkCourseDeletable(int courseId);
    }
    public static interface StudentValidatorFunctions {
        boolean checkIsStudentIdValid(int rollNumber);
        boolean checkCanStudyMoreCourses(int student_index);

    }
}
