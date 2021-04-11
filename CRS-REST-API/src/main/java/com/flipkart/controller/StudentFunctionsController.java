package com.flipkart.controller;


import com.flipkart.bean.*;
import com.flipkart.dao.CourseCatalogDBOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.service.AccountingSystem;
import com.flipkart.service.CourseCatalogSystem;
import com.flipkart.service.NotificationSystem;
import com.flipkart.service.UserModificationSystem;
import com.flipkart.utils.DB;
import org.apache.log4j.Logger;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/student")
public class StudentFunctionsController {

    private static final Logger logger = Logger.getLogger(StudentFunctionsController.class);



    /**
     * @return All the Courses with the details
     */
    @GET
    @Path("/showAllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses() {

        logger.info("getCourses controller");
        DB db = DB.getInstance();
        ArrayList<Course> courses = new CourseCatalogDBOperations().getAllCourses();
        logger.debug("Courses size: "+String.valueOf(courses.size()));

        //GlobalVariables.appendException("abcException");
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity(courses).build();
        else{
            String message=GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    @GET
    @Path("/showAllCourseMaps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourseMaps(){
        logger.info("get all courseMaps controller");
        DB db = DB.getInstance();
        ArrayList<CourseMap> courseMaps = new CourseCatalogDBOperations().getAllCourseMaps();
        //courseMaps.add(new CourseMap(-1,-1));
        return Response.status(200).entity(courseMaps).build();

    }



    @POST
    @Path("/addCourseMap")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourseMapForStudent(CourseMap courseMap) {
        logger.info("add courseMap controller method");
        new CourseCatalogSystem().addLateralCourse(courseMap);
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity(courseMap).build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    @DELETE
    @Path("/removeCourseMap")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeCourseMapForStudent(CourseMap courseMap){
        logger.info("remove CourseMap method");

        new CourseCatalogSystem().dropLateralCourse(courseMap);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("CourseMap deleted").build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    @GET
    @Path("/showBill/{studentId}")
    public Response showBillForStudent(@PathParam("studentId") int studentId){
        logger.info("show Bill method");

        float amount = new AccountingSystem().calculateBill(studentId);
        logger.info("The bill amount is: "+amount);
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("The bill amount is: "+amount).build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }

    @POST
    @Path("/payBill/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response payBill(@PathParam("studentId") int studentId,DebitCard studentDebitCard) {
        logger.info("pay bill controller method for: " + studentId);

        new AccountingSystem().makeTransaction(studentId,studentDebitCard,studentDebitCard.getBalance());

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("Amount paid successfully!").build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    @GET
    @Path("/showGrades/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrades(@PathParam("studentId") int studentId){

        ArrayList<Grade> grades = new CourseCatalogDBOperations().fetchGrades(studentId);


        if(GlobalVariables.checkNoException())
            return Response.status(200).entity(grades).build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }

    @PUT
    @Path("/changePassword/{studentId}/{newPassword}")
    public Response changePassword(@PathParam("studentId") int studentId,@PathParam("newPassword") String newPassword){

        new UserModificationSystem().change_password(studentId,newPassword);
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("Password has been updated successfully").build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }

    @GET
    @Path("notifications/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@PathParam("studentId") int studentId){

        ArrayList<Notification> notifications = new NotificationSystem().viewStudentNotifications(studentId);
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity(notifications).build();
        else{
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }









}
