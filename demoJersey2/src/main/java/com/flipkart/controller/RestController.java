package com.flipkart.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/testService")
public class RestController {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestService() {
        return "Hello World";
    }
}
