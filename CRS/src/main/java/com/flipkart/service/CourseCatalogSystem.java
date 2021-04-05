package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.bean.Student;
import com.flipkart.dao.CourseCatalogDB;
import com.flipkart.temporaryDB.DB;
import com.flipkart.validator.StudentValidator;

import java.util.ArrayList;

import static com.flipkart.temporaryDB.DB.courses;
import static com.flipkart.temporaryDB.DB.students;
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
        CourseMap courseMap = new CourseMap();
        for(int student_index=0;student_index<students.size();student_index++){
            for(int i=0;i<students.get(student_index).subjectPreferences.size() && studentValidator.checkCanStudyMoreCourses(student_index);i++){
                courseMap.student_index=student_index;
                courseMap.course_index=students.get(student_index).subjectPreferences.get(i);
                courseCatalogDB.selectStudyCourse(courseMap);

            }

        }
    }
}





