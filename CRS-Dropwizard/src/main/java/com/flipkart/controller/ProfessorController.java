package com.flipkart.controller;


import com.flipkart.bean.Course;
import com.flipkart.utils.wrappers.GradesWrapper;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.utils.DBConnection;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/professor")
public class ProfessorController {
    private static final Logger logger = Logger.getLogger(ProfessorController.class);

    /**
     * @return Get all Courses in the System
     */
    @GET
    @Path("/showAllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses() {

        logger.info("getCourses controller");
        DBConnection dbConnection = DBConnection.getInstance();
        ArrayList<Course> courses = new CourseCatalogDBOperations().getAllCourses();
        logger.debug("Courses size: "+String.valueOf(courses.size()));


        if(GlobalVariables.checkNoException())
            return Response.status(200).entity(courses).build();
        else{
            String message=GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    /**
     * @param facultyId who will teach the course henceforth
     * @param courseId to be taught by the professor
     * @return message indicating successful registration for a course
     */
    @POST
    @Path("/addCourse/{facultyId}/{courseId}")
    public Response addFacultyCourse(@PathParam("facultyId") int facultyId,@PathParam("courseId") int courseId) {

        new CourseCatalogSystem().facultyAddCourse(facultyId,courseId);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("The course has been added to you!").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    /**
     * @param facultyId the faculty that is checking the grades
     * @param courseId the course for which he/she intends to see all the grades
     * @return an ArrayList of Grade type having all the results of that course
     */
    @GET
    @Path("/viewGrades/{facultyId}/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrades(@PathParam("facultyId") int facultyId,@PathParam("courseId") int courseId) {
        GradesWrapper gradesWrapper = new GradesWrapper(new CourseCatalogSystem().viewGrades(facultyId,courseId));

        if(GlobalVariables.checkNoException())
            if(gradesWrapper.grades.size()>0)
                return Response.status(200).entity(gradesWrapper).build();
            else
                return Response.status(200).entity("No grades found at the moment").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    /**
     * @param facultyId who wants to stop teaching a course
     * @param courseId that will no longer be taught by the professor
     * @return message of successful completion
     */
    @DELETE
    @Path("dropCourse/{facultyId}/{courseId}")
    public Response dropFacultyCourse(@PathParam("facultyId") int facultyId,@PathParam("courseId") int courseId) {

        new CourseCatalogSystem().facultyDropCourse(facultyId,courseId);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("The course has been unassigned from you!").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    /**
     * @param gradesWrapper All the grades embedded to a Wrapper class object for the sake of input to REST API
     * @param facultyId the faculty who is adding the grades
     * @param courseId for which the grades are being added
     * @return message intimating of successful addition
     */
    @POST
    @Path("/addGrades/{facultyId}/{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response AddFacultyGrade(GradesWrapper gradesWrapper, @PathParam("facultyId") int facultyId,@PathParam("courseId") int courseId) {


        new CourseCatalogSystem().addGrades(facultyId,gradesWrapper.grades,courseId);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("The grades have been successfully recorded").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }


    }



//Admin


}
