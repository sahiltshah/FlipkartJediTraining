package com.flipkart.service;

import com.flipkart.dao.NotificationSystemDBOperations;
import com.flipkart.exception.CourseException.CourseAlreadyFullException;
import com.flipkart.exception.CourseException.CourseAlreadyHasFaculty;
import com.flipkart.exception.CourseException.CourseIndexInvalidException;
import com.flipkart.exception.CourseException.CourseNotFoundException;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.bean.Grade;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.validator.CourseCatalogValidator;
import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;



public class CourseCatalogSystem implements serviceInterface.CourseCatalogSystemFunctions {

    public static final Logger logger = Logger.getLogger(CourseCatalogSystem.class);


    @Override
    public boolean developNewCourse() {
        return false;
    }

    @Override
    public boolean addCoursePreferences(int student_index) {
        return false;
    }

    @Override
    public void removeCourse() {
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        courseCatalogDBOperations.showAllCourses();
        System.out.println("Enter the courseId you wish to delete: ");
        Scanner sc= new Scanner(System.in);
        int courseId = sc.nextInt();
        if(new CourseCatalogValidator().checkCourseDeletable(courseId))
            courseCatalogDBOperations.deleteCourseFromDB(courseId);

    }


    public void showAllCourses(){
    System.out.println("All the courses are: ");
    CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
    ArrayList<Course> courses = courseCatalogDBOperations.getAllCourses();
    for(Course course : courses){
        course.printCourse();
    }
    }

    public void addLateralCourse(CourseMap courseMap){
        CourseCatalogValidator courseCatalogValidator = new CourseCatalogValidator();
        Course course = courseCatalogValidator.checkCourseExists(courseMap.courseId);
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        try{
            if(course.getcourseId()!=-1){
                if(course.getcourseStrength()<10){
                    courseCatalogDBOperations.addCourseMap(courseMap);
                    course.setcourseStrength(course.getcourseStrength()+1);
                    courseCatalogDBOperations.modifyCourseCount(course);
                }
                else throw new CourseAlreadyFullException();
            }
            else throw new CourseNotFoundException();

        } catch (CourseAlreadyFullException | CourseNotFoundException e) {
            System.out.println("Debugging");
            logger.debug(e);
            GlobalVariables.appendException(String.valueOf(e));
        }


    }

    public void dropLateralCourse(CourseMap courseMap){
        int courseId;

        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        courseCatalogDBOperations.dropCourseMap(courseMap);
    }

    public void showStudentCourses(int studentId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Integer> courseIds = courseCatalogDBOperations.getCoursesFromStudent(studentId);
        for(int courseId : courseIds){
            System.out.println(courseId);
        }

    }

    public void showStudentGrades(int studentId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Grade> grades = courseCatalogDBOperations.fetchGrades(studentId);
        System.out.println("For student: " + studentId + " the grades are: ");
        for(Grade grade : grades){
            grade.printGrade();
        }
    }

    public void facultyAddCourse(int professorId,int courseId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        try {


            Course course = courseCatalogDBOperations.getCourseFromCourseId(courseId);
            if(course.getcourseId()==-1)
                throw new CourseIndexInvalidException();
            else if (course.getFacultyId()!=-1)
                throw new CourseAlreadyHasFaculty();
            else
                courseCatalogDBOperations.addFaculty(professorId,courseId);
        } catch (CourseAlreadyHasFaculty courseAlreadyHasFaculty) {
            courseAlreadyHasFaculty.printStackTrace();
        } catch (CourseIndexInvalidException ex) {
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        }

    }

    public void facultyDropCourse(int professorId,int courseId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Course> courses =courseCatalogDBOperations.getFacultyCourses(professorId);
        for(Course course : courses){
            course.printCourse();
        }

        if(new CourseCatalogValidator().dropCourseCheck(courseId,courses)){
            courseCatalogDBOperations.dropFacultyCourse(courseId);
        }
        else
            System.out.println("Can't drop course!");



    }

    public void addGrades(int professorId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Course> courses =courseCatalogDBOperations.getFacultyCourses(professorId);
        System.out.println("These are your courses: ");
        for(Course course : courses)
            course.printCourse();
        ArrayList<Grade> grades = new ArrayList<>();
        System.out.println("Enter a courseId that you would like to grade: ");
        Scanner sc= new Scanner(System.in);
        int courseId = sc.nextInt();
        CourseCatalogValidator courseCatalogValidator = new CourseCatalogValidator();
        if(courseCatalogValidator.checkTaughtCourse(courseId,courses,professorId)){

            ArrayList<Integer> studentIds = courseCatalogDBOperations.getStudentsFromCourse(courseId);
            System.out.println("Enter the grades of each student one by one: ");
            for(int studentId : studentIds){
                System.out.println(studentId + ": ");
                Scanner scx= new Scanner(System.in);
                char c = scx.nextLine().charAt(0);
                grades.add(new Grade(studentId,courseId,c));

            }
            courseCatalogDBOperations.addStudentGrades(grades);
        }
        else{
            logger.debug("courseId entered is not valid!");
            GlobalVariables.appendException("courseId entered is not valid!");

        }

    }

    public void addGrades(int professorId,ArrayList<Grade> grades,int courseId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();

        ArrayList<Course> courses =courseCatalogDBOperations.getFacultyCourses(professorId);

        CourseCatalogValidator courseCatalogValidator = new CourseCatalogValidator();
        if(courseCatalogValidator.checkTaughtCourse(courseId,courses,professorId)){
            courseCatalogDBOperations.addStudentGrades(grades);
        }
        else{
            logger.debug("courseId entered is not valid!");
            GlobalVariables.appendException("courseId entered is not valid!");

        }

    }


    public ArrayList<Grade> viewGrades(int professorId,int courseId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Course> courses =courseCatalogDBOperations.getFacultyCourses(professorId);
        System.out.println("These are your courses: ");
        for(Course course : courses)
            course.printCourse();
        if(new CourseCatalogValidator().checkTaughtCourse(courseId,courses,professorId)){
            System.out.println("Printing grades: ");
            ArrayList<Grade> grades = courseCatalogDBOperations.getSubjectGrades(courseId);
            for(Grade grade : grades)
                grade.printGrade();
            return grades;
        }

        return null;

    }

    public void addCourse() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the courseId: ");
        int courseId = sc.nextInt();
        System.out.println("Enter the courseName: ");
        String courseName = sc.nextLine();
        System.out.println("Enter the courseCost: ");
        float courseCost = sc.nextFloat();
        Course course = new Course(courseName,courseId,0,courseCost,-1);
        if(new CourseCatalogValidator().checkCourseId(courseId))
            new CourseCatalogDBOperations().addCourse(course);
    }
}





