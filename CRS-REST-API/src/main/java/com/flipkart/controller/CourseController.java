package com.flipkart.controller;


import com.flipkart.bean.Course;
import com.flipkart.bean.CourseMap;
import com.flipkart.controller.example.Customer;
import com.flipkart.controller.example.Order;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.utils.DB;
import com.flipkart.utils.Initializer;
import org.apache.log4j.Logger;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;
import java.util.ArrayList;

@Path("/course")
public class CourseController {

    private static final Logger logger = Logger.getLogger(CourseController.class);



    /**
     * @return All the Courses with the details
     */
    @GET
    @Path("/showAllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses() {
        System.out.println("Hello World");
        logger.info("getCourses method");
        DB db = DB.getInstance();
        ArrayList<Course> courses = new CourseCatalogDBOperations().getAllCourses();
        //System.out.printf();
        logger.debug(String.valueOf(courses.size()));
        System.out.println(String.valueOf(courses.size()));
        //courses.add(new Course());
        //return courses;
        return Response.status(200).entity(courses).build();

    }

    @GET
    @Path("/showAllCourseMaps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourseMaps(){
        logger.info("get all courseMaps method");
        DB db = DB.getInstance();
        ArrayList<CourseMap> courseMaps = new CourseCatalogDBOperations().getAllCourseMaps();
        //courseMaps.add(new CourseMap(-1,-1));
        return Response.status(200).entity(courseMaps).build();

    }






    @POST
    @Path("/addCourse")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseMap addCourseForStudent(CourseMap courseMap) {
        System.out.println("hit post service");
        new CourseCatalogSystem().addLateralCourse(courseMap);
        return courseMap;

    }


}
