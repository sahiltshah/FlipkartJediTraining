package com.flipkart.controller;


import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.global.GlobalVariables;
import com.flipkart.service.UserModificationSystem;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);



    @POST
    @Path("/newUserSignUp/{username}/{password}")
    public Response newUserSignUp(@PathParam("username") String username, @PathParam("password") String password){
        new AuthenticationOperations().registerNewUserInDB(username,password);

        if(GlobalVariables.checkNoException())
            return Response.status(200).entity("User added to DB").build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }

    }


    @POST
    @Path("/login/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("username") String username, @PathParam("password") String password){
        SpecialUser specialUser = new UserModificationSystem().login(username,password);
        if(GlobalVariables.checkNoException())
            return Response.status(200).entity(specialUser).build();
        else {
            String message = GlobalVariables.globalExceptionMessage;
            GlobalVariables.flushExceptionMessage();
            return Response.status(200).entity(message).build();
        }
    }



}
