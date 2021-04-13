package com.dropwizard;

/**
 * Hello world!
 *
 */
import com.flipkart.controller.AdminController;
import com.flipkart.controller.ProfessorController;
import com.flipkart.controller.StudentController;
import com.flipkart.controller.UserController;


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
public class CRSDropWizardApplication extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CRSDropWizardApplication.class);

    /**
     *
     * To initialize any content if need be.
     */
    @Override
    public void initialize(Bootstrap<Configuration> b) {
        LOGGER.info("initialization call");
    }

    /**
     * @param c Standard CPU files
     * @param e Environment type variable
     * @throws Exception if a specific run didnt work out
     */
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Run call");
        LOGGER.info("Registering REST resources");


        e.jersey().register(new AdminController());
        e.jersey().register(new ProfessorController());
        e.jersey().register(new StudentController());
        e.jersey().register(new UserController());


    }

    public static void main(String[] args) throws Exception {
        LOGGER.info("main call");
        new CRSDropWizardApplication().run(args);
    }
}
