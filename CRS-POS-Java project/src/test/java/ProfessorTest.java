import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.service.CourseCatalogSystem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ProfessorTest {


    @Test
    public void testGetAllCourses(){

        //Course( int courseId, String courseName, int courseStrength, float courseCost, int facultyId)
        ArrayList<Course> courses = new CourseCatalogDBOperations().getAllCourses();
        System.out.println("the size is: "+courses.size());
        ArrayList<Course> expected = new ArrayList<>();
        expected.add(new Course(101,"Physics",0,100,501));
        expected.add(new Course(102,"Chemistry",6,200,502));
        expected.add(new Course(104,"Math",0,150,501));

        assertEquals(expected.size(),courses.size());
    }

    @Test
    public void startTeachingACourse(){
        new CourseCatalogDBOperations().addFaculty(501,104);
        ArrayList<Course> courses = new CourseCatalogDBOperations().getAllCourses();
        int ans = courses.get(2).getFacultyId();
        assertEquals(501,ans);

    }

    @Test
    public void stopTeachingACourse(){
        new CourseCatalogDBOperations().dropFacultyCourse(104);
        ArrayList<Course> courses = new CourseCatalogDBOperations().getAllCourses();
        int ans = courses.get(2).getFacultyId();
        assertEquals(-1,ans);

    }

    @Test
    public void addGradeStudent(){
        ArrayList<Grade> inputGrades = new ArrayList<>();
        inputGrades.add(new Grade(999,888,'A'));
        new CourseCatalogDBOperations().addStudentGrades(inputGrades);

        ArrayList<Grade> outputGrades = new CourseCatalogDBOperations().getSubjectGrades(888);
        assertEquals(outputGrades.get(0).grade,inputGrades.get(0).grade);
    }


}
