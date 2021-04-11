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
import org.apache.log4j.Logger;

import java.util.ArrayList;

//all modules !complete
public class CourseCatalogValidator implements helperInterface.CourseValidatorFunctions {
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
            if(course.getcourseId()==courseId)
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
            if(course.getcourseId()==-1)
                throw new CourseNotFoundFromCourseID();
            else if(course.getcourseStrength()==0)
                throw new CourseAlreadyHasStudents();
                return true;
        } catch (CourseNotFoundFromCourseID courseNotFoundFromCourseID) {
            logger.debug(courseNotFoundFromCourseID.getMessage());
            System.out.println("CourseNotFoundFromCourseID");
        } catch (CourseAlreadyHasStudents courseAlreadyHasStudents) {
            logger.debug(courseAlreadyHasStudents.getMessage());
            System.out.println("courseAlreadyHasStudents");
        }
        return false;
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

    public boolean checkCourseId(int courseId){
        try {
            Course course = new CourseCatalogDBOperations().getCourseFromCourseId(courseId);
            if(course.getcourseId()!=-1)
                throw new CourseAlreadyExists();
            else
                return true;
        } catch (CourseAlreadyExists courseAlreadyExists) {
            courseAlreadyExists.printStackTrace();
        }
        return false;
    }


}
