package com.flipkart.controller;


import com.flipkart.bean.User;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.global.GlobalVariables;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;

@Path("/user")
public class UserFunctionsController {

    private static final Logger logger = Logger.getLogger(UserFunctionsController.class);



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


}
