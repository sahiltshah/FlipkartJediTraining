package com.dropwizard;

/**
 * Hello world!
 *
 */
import com.flipkart.bean.Professor;
import com.flipkart.controller.AdminController;
import com.flipkart.controller.ProfessorController;
import com.flipkart.controller.StudentController;
import com.flipkart.controller.UserController;
import com.flipkart.controller.example.CustomerRestController;
import com.flipkart.controller.example.HelloWorldController;


import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
        System.out.println("initialization call");
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        System.out.println("Run call");
        LOGGER.info("Registering REST resources");
        //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
        System.out.println("Registering REST resources");

        e.jersey().register(new AdminController());
        e.jersey().register(new ProfessorController());
        e.jersey().register(new StudentController());
        e.jersey().register(new UserController());
        e.jersey().register(new CustomerRestController());
        e.jersey().register(new HelloWorldController());


    }

    public static void main(String[] args) throws Exception {
        System.out.println("main call");
        new App().run(args);
    }
}
