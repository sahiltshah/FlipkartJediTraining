package com.example.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    int counter = 0;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        counter++;
        return ("Hello world!"+String.valueOf(counter));
    }
}
