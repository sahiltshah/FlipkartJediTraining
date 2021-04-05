package com.flipkart.dao.oldDAO;

import com.flipkart.Exception.CourseException.CourseIndexInvalidException;
import com.flipkart.Exception.CourseException.CourseMapNeverExisted;
import com.flipkart.Exception.StudentException.StudentIndexInvalidException;
import com.flipkart.bean.CourseMap;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Course;

import java.util.ArrayList;

import static com.flipkart.temporaryDB.OldDB.*;

public class CourseCatalogDB implements daoInterface.CourseCatalogDBFunctions {

//all modules complete
    @Override
    public ArrayList<Integer> fetchAvailableCourses() { //to be replaced with select statement
        ArrayList<Integer> returnCourses = new ArrayList<>();
            for(int i=0;i<courses.size();i++){
                if(courses.get(i).getcourseStrength()<10)
                    returnCourses.add(i);
        }

        return returnCourses;

    }

    @Override
    public boolean selectStudyCourse(CourseMap courseMap) {
        try{
            if(courseMap.courseId < 0 || courseMap.courseId >= courses.size())
                throw new CourseIndexInvalidException();
            if(courseMap.studentId < 0 || courseMap.studentId >= students.size())
                throw new StudentIndexInvalidException();
            students.get(courseMap.studentId).setCurrentSubjectCount(students.get(courseMap.studentId).getCurrentSubjectCount()+1);
            courses.get(courseMap.courseId).setcourseStrength(courses.get(courseMap.courseId).getcourseStrength()+1);

            courseMaps.add(courseMap);
            System.out.println("Course added successfully");
            return true;
        } catch (StudentIndexInvalidException e) {
            e.printStackTrace();
        } catch (CourseIndexInvalidException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteStudyCourse(CourseMap courseMap) {

        try{
            if(courseMap.courseId < 0 || courseMap.courseId >= courses.size())
                throw new CourseIndexInvalidException();
            if(courseMap.studentId < 0 || courseMap.studentId >= students.size())
                throw new StudentIndexInvalidException();
            students.get(courseMap.studentId).setCurrentSubjectCount(students.get(courseMap.studentId).getCurrentSubjectCount()+1);
            courses.get(courseMap.courseId).setcourseStrength(courses.get(courseMap.courseId).getcourseStrength()+1);
            int courseMapIndex =-1;
            for(int i=0;i<courseMaps.size();i++)
                if(courseMaps.get(i).equals(courseMap)) {
                    courseMapIndex = i;
                    break;
                }

            if(courseMapIndex == -1)
                throw new CourseMapNeverExisted();

            System.out.println("Course removed successfully");
            return true;

        } catch (StudentIndexInvalidException e) {
            e.printStackTrace();
        } catch (CourseIndexInvalidException e) {
            e.printStackTrace();
        } catch (CourseMapNeverExisted courseMapNeverExisted) {
            courseMapNeverExisted.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean selectTeachCourse(Professor professor, Course course) {
        for(Course x : courses){
            if(x.equals(course)){
                x.setFacultyId(professor.getFacultyId());
                break;
            }
        }

        for(Professor x : professors){
            if(x.equals(professor)){
                x.subjectsUndertaken.add(course.getcourseId());
                break;
            }
        }

        return false;
    }

    @Override
    public boolean deleteTeachCourse(Professor professor, Course course) {
        for(Professor p : professors){
            if(p.equals(professor)){
                p.subjectsUndertaken.remove(course.getcourseId());
                break;
            }
        }
        for(Course s : courses){
            if(s.equals(course)){
                s.setFacultyId(-1);
            }
        }
        return false;
    }

    @Override
    public boolean addCourseToDb(Course course) {
        courses.add(course);
        return true;
    }

    @Override
    public boolean addCoursePreference(CourseMap courseMap) {
        try{
            if(courseMap.courseId < 0 || courseMap.courseId >= courses.size())
                throw new CourseIndexInvalidException();
            if(courseMap.studentId < 0 || courseMap.studentId >= students.size())
                throw new StudentIndexInvalidException();
            students.get(courseMap.studentId).subjectPreferences.add(courses.get(courseMap.courseId).getcourseId());
            return true;
        } catch (StudentIndexInvalidException e) {
            e.printStackTrace();
        } catch (CourseIndexInvalidException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public ArrayList<Course> getStudentCourses(int student_index) {
        ArrayList<Course> returnCourses = new ArrayList<Course>();
        for(CourseMap courseMap : courseMaps){
            if(courseMap.studentId ==student_index){
                returnCourses.add(courses.get(courseMap.courseId));
            }
        }
        return returnCourses;
    }

    public Course getCourseDetails(Integer course_index){
        try{
            if(course_index < 0 || course_index >= courses.size())
                throw new CourseIndexInvalidException();
            else{
                return courses.get(course_index);
            }
        } catch (CourseIndexInvalidException e) {
            e.printStackTrace();
        }

        return null;
    }




}
