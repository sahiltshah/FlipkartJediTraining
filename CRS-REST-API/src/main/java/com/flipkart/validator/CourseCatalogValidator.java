package com.flipkart.validator;

import com.flipkart.exception.CourseException.CourseAlreadyExists;
import com.flipkart.exception.CourseException.CourseAlreadyHasStudents;
import com.flipkart.exception.CourseException.CourseIndexInvalidException;
import com.flipkart.exception.CourseException.CourseNotFoundFromCourseID;
import com.flipkart.exception.ProfessorException.AreNotTeachingThisCourseException;
import com.flipkart.exception.ProfessorException.CantAbandonSubjectException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.global.GlobalVariables;
import org.apache.log4j.Logger;

import java.util.ArrayList;

//all modules !complete
public class CourseCatalogValidator implements ValidatorInterface.CourseValidatorFunctions {
    public static final Logger logger = Logger.getLogger(CourseCatalogDBOperations.class);


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
            if(course.getCourseId()==courseId)
                return course;
        return new Course("",-1,-1,-1,-1);
    }

    @Override
    public float getSubjectCost(Course course) {
        return 0;
    }

    @Override
    public boolean checkCourseDeletable(int courseId) {

        try {
            Course course = new CourseCatalogDBOperations().getCourseFromCourseId(courseId);
            if(course.getCourseId()==-1)
                throw new CourseNotFoundFromCourseID();
            else if(course.getCourseStrength()!=0)
                throw new CourseAlreadyHasStudents();
                return true;
        } catch (CourseNotFoundFromCourseID ex) {
            System.out.println("CourseNotFoundFromCourseID");
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } catch (CourseAlreadyHasStudents ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
            System.out.println("courseAlreadyHasStudents");
        }
        return false;
    }


    public boolean dropCourseCheck(int courseId, ArrayList<Course> courses){
        try {
            for(Course course : courses){
                if(course.getCourseId() == courseId){
                    if(course.getCourseStrength()==0)
                        return true;
                    else
                        throw new CantAbandonSubjectException();

                }
            }
            throw new CourseIndexInvalidException();
        } catch (CantAbandonSubjectException | CourseIndexInvalidException ex) {
            System.out.println(ex);
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        }
        return false;

    }

    public boolean checkTaughtCourse(int courseId,ArrayList<Course> courses,int professorId){
        try {
            System.out.println("CourseID: " + courseId);
            System.out.println("professor ID: " + professorId);
            for(Course course : courses){
                if(course.getCourseId() == courseId){
                    if(course.getFacultyId()==professorId){
                        return true;
                    }

                    else
                        throw new AreNotTeachingThisCourseException();
                }
            }
            throw new CourseIndexInvalidException();
        }
         catch (CourseIndexInvalidException ex) {
             System.out.println("The course index you've enterred is invalid");
             logger.debug(ex);
             GlobalVariables.appendException(String.valueOf(ex));
        }
        catch (AreNotTeachingThisCourseException ex){
            System.out.println("You aren't teaching this course so can't select it");
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        }
        return false;

    }

    public boolean checkCourseId(int courseId){
        try {
            Course course = new CourseCatalogDBOperations().getCourseFromCourseId(courseId);
            if(course.getCourseId()!=-1)
                throw new CourseAlreadyExists();
            else
                return true;
        } catch (CourseAlreadyExists ex) {
            System.out.println(ex);
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));

        }
        return false;
    }


}
