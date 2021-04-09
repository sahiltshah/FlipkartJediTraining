package com.example.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/customer")
public class CustomerRestController {



    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() {
        Customer customer = new Customer();
        customer.setId(101);
        customer.setName("Flipkart");
        customer.setAddress("Mumbai");
        return Response.status(200).entity(customer).build();

    }



}
