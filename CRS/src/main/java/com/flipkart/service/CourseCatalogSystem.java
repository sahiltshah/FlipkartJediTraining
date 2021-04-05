package com.flipkart.service;

import com.flipkart.Exception.CourseException.CourseAlreadyFullException;
import com.flipkart.Exception.CourseException.CourseNotFoundException;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.dao.DB;
import com.flipkart.dao.oldDAO.CourseCatalogDB;
import com.flipkart.validator.CourseCatalogValidator;
import com.flipkart.validator.StudentValidator;

import java.util.ArrayList;
import java.util.Scanner;

import static com.flipkart.temporaryDB.OldDB.students;
import static com.flipkart.utils.ScannerInput.input_obj;


public class CourseCatalogSystem implements serviceInterface.CourseCatalogSystemFunctions {

    @Override
    public boolean developNewCourse() {
        Course createdCourse =new Course(); //tbf
        CourseCatalogDB courseCatalogDB = new CourseCatalogDB();
        courseCatalogDB.addCourseToDb(createdCourse);
        return false;
    }

    @Override
    public boolean addCoursePreferences(int student_index) {
        StudentValidator studentValidator = new StudentValidator();
        if(studentValidator.checkCanStudyMoreCourses(student_index)){
            CourseCatalogDB courseCatalogDB=new CourseCatalogDB();
            ArrayList<Integer> availableCourses = courseCatalogDB.fetchAvailableCourses();
            System.out.println("The available courses are: ");
            for(Integer course_index : availableCourses){
                System.out.println(course_index);
            }
            while(students.get(student_index).subjectPreferences.size()<6){
                System.out.println("Add preference: ");
                Integer subjectPreference = input_obj.nextInt();
                students.get(student_index).subjectPreferences.add(subjectPreference);
            }
            return true;


        }
        return false;

    }

    public void assignAllCourses(){
        StudentValidator studentValidator=new StudentValidator();
        CourseCatalogDB courseCatalogDB = new CourseCatalogDB();
        CourseMap courseMap = new CourseMap(-1,-1);
        for(int student_index=0;student_index<students.size();student_index++){
            for(int i=0;i<students.get(student_index).subjectPreferences.size() && studentValidator.checkCanStudyMoreCourses(student_index);i++){
                courseMap.studentId =student_index;
                courseMap.courseId =students.get(student_index).subjectPreferences.get(i);
                courseCatalogDB.selectStudyCourse(courseMap);

            }

        }
    }

    public void showAllCourses(){
    System.out.println("All the courses are: ");
    ArrayList<Course> courses = DB.getAllCourses();
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
        try{
            if(course.getcourseId()!=-1){
                if(course.getcourseStrength()<10){
                    CourseMap courseMap = new CourseMap(studentId,courseId);
                    DB.addCourseMap(courseMap);
                    course.setcourseStrength(course.getcourseStrength()+1);
                    DB.modifyCourseCount(course);
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
        DB.dropCourseMap(new CourseMap(studentId,courseId));
    }

    public void showStudentCourses(int studentId){
        ArrayList<Integer> courseIds = DB.getStudentCourses(studentId);
        for(int courseId : courseIds){
            System.out.println(courseId);
        }

    }
}





