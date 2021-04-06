package com.flipkart.validator;

import com.flipkart.Exception.CourseException.CourseIndexInvalidException;
import com.flipkart.Exception.ProfessorException.AreNotTeachingThisCourseException;
import com.flipkart.Exception.ProfessorException.CantAbandonSubjectException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.dao.DB;
import com.flipkart.temporaryDB.OldDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

//all modules !complete
public class CourseCatalogValidator implements helperInterface.CourseValidatorFunctions {
    public static final Logger logger = LogManager.getLogger(CourseCatalogDBOperations.class);


    @Override
    public boolean checkSpecifcCourseStudyPossible(Course course) {
        return false;
    }

    @Override
    public boolean checkSpecificCourseTeachPossible(Course course) {
        return false;
    }

    @Override
    public boolean checkAnyCourseToTeachPossible(Professor p, Course course) {
        return false;
    }

    @Override
    public boolean checkProfNoMoreTeachCoursePossible(Professor p, Course course) {
        return false;
    }

    @Override
    public Course checkCourseExists(int courseId) {
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Course> courses = courseCatalogDBOperations.getAllCourses();
        for(Course course : courses)
            if(course.getcourseId()==courseId)
                return course;
        return new Course("",-1,-1,-1,-1);
    }

    @Override
    public float getSubjectCost(Course course) {
        return 0;
    }


    public boolean dropCourseCheck(int courseId, ArrayList<Course> courses){
        try {
            for(Course course : courses){
                if(course.getcourseId() == courseId){
                    if(course.getcourseStrength()==0)
                        return true;
                    else
                        throw new CantAbandonSubjectException();

                }
            }
            throw new CourseIndexInvalidException();
        } catch (CantAbandonSubjectException e) {
            e.printStackTrace();
        } catch (CourseIndexInvalidException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean checkTaughtCourse(int courseId,ArrayList<Course> courses,int professorId){
        try {
            System.out.println("CourseID: " + courseId);
            System.out.println("professor ID: " + professorId);
            for(Course course : courses){
                if(course.getcourseId() == courseId){
                    if(course.getFacultyId()==professorId){
                        return true;
                    }

                    else
                        throw new AreNotTeachingThisCourseException();
                }
            }
            throw new CourseIndexInvalidException();
        }
         catch (CourseIndexInvalidException e) {
             System.out.println("The course index you've enterred is invalid");
             logger.debug(e.getMessage());
        }
        catch (AreNotTeachingThisCourseException e){
            System.out.println("You aren't teaching this course so can't select it");
            logger.debug(e.getMessage());
        }
        return false;

    }


}
