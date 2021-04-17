package com.flipkart.controller;

import com.flipkart.bean.Course;
import com.flipkart.utils.wrappers.UnregisteredUsersWrapper;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.service.NewUserRegistrationSystem;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 * All the Admin functions
 */
@Path("/admin")
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class);

    /**
     * @return All the unregistered users in the LoginDetails table is shown via an ArrayList
     */
    @GET
    @Path("/getUnregisteredUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnregisteredUsers(){
        UnregisteredUsersWrapper unregisteredUsersWrapper = new UnregisteredUsersWrapper(new AuthenticationOperations().getUnregisteredUsers());

        if(GlobalVariables.checkNoException())
            if(unregisteredUsersWrapper.unregisteredUsers.size()>0)
                return Response.status(200).entity(unregisteredUsersWrapper).build();
            else
                return Response.status(200).entity("No unregistered users found at the moment").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }


    /**
     * @param username to be registered as a student
     * @return message on success of the action
     */
    @POST
    @Path("/registerStudent/{username}")
    public Response registerUserAsStudent(@PathParam("username") String username){

        new AuthenticationOperations().addStudentType(username);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("Student has been registered").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }


    /**
     * @param username to be exited from the system as it may not be approved
     * @return a message indicating successful completion of the Delete operation
     */
    @DELETE
    @Path("/removeUser/{username}")
    public Response removeUnregisteredUser(@PathParam("username") String username){
        AuthenticationOperations authenticationOperations = new AuthenticationOperations();
        authenticationOperations.removeUsernameFromLoginDetails(username);
        authenticationOperations.removeUsernameFromLoginMap(username);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("User removed from DB!").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    /**
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("/addProfessor/{username}/{password}")
    public Response addProfessor(@PathParam("username") String username, @PathParam("password") String password){
        int ans = new NewUserRegistrationSystem().addProfessor(username,password);
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("Professor added to system: " + ans).build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    /**
     * @param course The course to be enterred to the System
     * @return message of successful completion
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course){
        course.setFacultyId(-1);
        course.setCourseStrength(0);
        new CourseCatalogSystem().addCourse(course);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("Course added to system").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }


    /**
     * @param courseId to be deleted from the System
     * @return message of successful completion of API call
     */
    @DELETE
    @Path("/removeCourse/{courseId}")
    public Response removeCourse(@PathParam("courseId") int courseId){
        new CourseCatalogSystem().removeCourse(courseId);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("Removed course!").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }







}



