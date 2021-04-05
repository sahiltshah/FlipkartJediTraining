package com.flipkart.validator;

import com.flipkart.Exception.ProfessorException.CantAbandonSubjectException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;
import com.flipkart.dao.DB;
import com.flipkart.temporaryDB.OldDB;

import java.util.ArrayList;

//all modules !complete
public class CourseCatalogValidator implements helperInterface.CourseValidatorFunctions {

    @Override
    public boolean checkSpecifcCourseStudyPossible(Course course) {
        for(Course x : OldDB.courses){
            if(x.equals(course))
            {
                if(course.getcourseStrength()>=3 && course.getcourseStrength()<10)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean checkSpecificCourseTeachPossible(Course course) {
        for(Course x : OldDB.courses){
            if(x.equals(course))
            {
                if(x.getcourseStrength()>3)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean checkAnyCourseToTeachPossible(Professor p, Course course) {
        for(Course x: OldDB.courses)
            if(x.equals(course))
            {
                if(x.getFacultyId()==-1)
                    return true;
                else
                    return false;
            }
        return false;
    }

    @Override
    public boolean checkProfNoMoreTeachCoursePossible(Professor p, Course course) {
        try {
            for(Course x: OldDB.courses)
                if(x.equals(course))
                {
                    if(x.getcourseStrength()==0)
                        return true;
                    else
                        throw new CantAbandonSubjectException();
                }

        }
        catch (CantAbandonSubjectException ex){
            System.out.println("There are students already taking this course. You can't drop teaching it");
        }

        return false;
    }


    @Override
    public Course checkCourseExists(int courseId) {
        ArrayList<Course> courses = DB.getAllCourses();
        for(Course course : courses)
            if(course.getcourseId()==courseId)
                return course;
        return new Course("",-1,-1,-1,-1);
    }


    @Override
    public float getSubjectCost(Course course) {
        for(Course x: OldDB.courses)
            if(x.equals(course))
            {
                return x.getcourseCost();
            }
        return 0;
    }


}
