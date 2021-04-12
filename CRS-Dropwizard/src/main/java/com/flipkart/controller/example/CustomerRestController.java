package com.flipkart.controller.example;



import com.flipkart.controller.example.Customer;
import com.flipkart.controller.example.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;


@Path("/customer")
public class CustomerRestController {



    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getMessage() {
        Customer customer = new Customer();
        customer.setId(101);
        customer.setName("Flipkart");
        customer.setAddress("Mumbai");
        Order order = new Order();
        order.setId(1);
        order.setName("abc");
        customer.setOrder(order);
        return customer;

        //return Response.status(200).entity(customer).build();

    }

    //Post implementation

    @POST
    @Path("/showCustomer")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer createTrackInJSON(Customer customer) {
        System.out.println("hit post service");

        System.out.println("value of title from UI " +customer.getId());
        System.out.println("value of singer form UI" +customer.getName());


        String result = "Track saved : " + customer;


        return customer;

    }


    @DELETE
    @Path("/delete/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") int customerId)
            throws URIReferenceException {

        // service query to perform the delete operation

        // implementation
        return Response.status(200).entity("Track id " +customerId +
                "successfully deleted").build();


    }


    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer updateCustomer(Customer customer){

        // rest of impl write here in code

        customer.setName(customer.getName() +"updated");
        return customer;

    }







}

