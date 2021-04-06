package com.flipkart.service;

import com.flipkart.Exception.CourseException.CourseAlreadyFullException;
import com.flipkart.Exception.CourseException.CourseAlreadyHasFaculty;
import com.flipkart.Exception.CourseException.CourseIndexInvalidException;
import com.flipkart.Exception.CourseException.CourseNotFoundException;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.bean.Grade;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.validator.CourseCatalogValidator;

import java.util.ArrayList;
import java.util.Scanner;



public class CourseCatalogSystem implements serviceInterface.CourseCatalogSystemFunctions {


    @Override
    public boolean developNewCourse() {
        return false;
    }

    @Override
    public boolean addCoursePreferences(int student_index) {
        return false;
    }


    public void showAllCourses(){
    System.out.println("All the courses are: ");
    CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
    ArrayList<Course> courses = courseCatalogDBOperations.getAllCourses();
    for(Course course : courses){
        course.printCourse();
    }
    }

    public void addLateralCourse(int studentId){
        int courseId;
        System.out.println("Enter the courseId you wish to study: ");
        Scanner sc= new Scanner(System.in);
        courseId = sc.nextInt();
        CourseCatalogValidator courseCatalogValidator = new CourseCatalogValidator();
        Course course = courseCatalogValidator.checkCourseExists(courseId);
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        try{
            if(course.getcourseId()!=-1){
                if(course.getcourseStrength()<10){
                    CourseMap courseMap = new CourseMap(studentId,courseId);
                    courseCatalogDBOperations.addCourseMap(courseMap);
                    course.setcourseStrength(course.getcourseStrength()+1);
                    courseCatalogDBOperations.modifyCourseCount(course);
                }
                else throw new CourseAlreadyFullException();
            }
            else throw new CourseNotFoundException();
        } catch (CourseAlreadyFullException e) {
            e.printStackTrace();
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void dropLateralCourse(int studentId){
        int courseId;
        System.out.println("Enter the courseId you wish to delete: ");
        Scanner sc= new Scanner(System.in);
        courseId = sc.nextInt();
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        courseCatalogDBOperations.dropCourseMap(new CourseMap(studentId,courseId));
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

    public void facultyAddCourse(int professorId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        try {
            showAllCourses();
            System.out.println("Enter a courseId that does not have already have a faculty");
            Scanner sc= new Scanner(System.in);
            int courseId = sc.nextInt();
            Course course = courseCatalogDBOperations.getCourseFromCourseId(courseId);
            if(course.getcourseId()==-1)
                throw new CourseIndexInvalidException();
            else if (course.getFacultyId()!=-1)
                throw new CourseAlreadyHasFaculty();
            else
                courseCatalogDBOperations.addFaculty(professorId,courseId);
        } catch (CourseAlreadyHasFaculty courseAlreadyHasFaculty) {
            courseAlreadyHasFaculty.printStackTrace();
        } catch (CourseIndexInvalidException e) {
            e.printStackTrace();
        }

    }

    public void facultyDropCourse(int professorId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Course> courses =courseCatalogDBOperations.getFacultyCourses(professorId);
        for(Course course : courses){
            course.printCourse();
        }
        System.out.println("Enter a courseId that you would like to opt out of teaching: ");
        Scanner sc= new Scanner(System.in);
        int courseId = sc.nextInt();
        if(new CourseCatalogValidator().dropCourseCheck(courseId,courses)){
            courseCatalogDBOperations.dropFacultyCourse(courseId);
        }
        else
            System.out.println("There was an error in deleting the course");



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
            System.out.println("Condition met");
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
        else
            System.out.println("courseId enterred not valid!");
    }

    public void viewGrades(int professorId){
        CourseCatalogDBOperations courseCatalogDBOperations = new CourseCatalogDBOperations();
        ArrayList<Course> courses =courseCatalogDBOperations.getFacultyCourses(professorId);
        System.out.println("These are your courses: ");
        for(Course course : courses)
            course.printCourse();
        System.out.println("Enter a courseId that you would like to see+search the grade of: ");
        Scanner sc= new Scanner(System.in);
        int courseId = sc.nextInt();
        if(new CourseCatalogValidator().checkTaughtCourse(courseId,courses,professorId)){
            System.out.println("Printing grades: ");
            ArrayList<Grade> grades = courseCatalogDBOperations.getSubjectGrades(courseId);
            for(Grade grade : grades)
                grade.printGrade();
        }

    }
}





