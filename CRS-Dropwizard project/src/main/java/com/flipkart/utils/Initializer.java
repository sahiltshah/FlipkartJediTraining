package com.flipkart.utils;

import com.flipkart.bean.SpecialUser;
import org.apache.log4j.Logger;

public class Initializer {
    //adding debit card details
    //adding initial courses and admin login credentials
    //public static final Logger logger = LogManager.getLogger(Initializer.class);
    private static final Logger logger = Logger.getLogger(Initializer.class);
    public void initialize(){
        SpecialUser specialUser=new SpecialUser();
        specialUser.type=1;
        specialUser.id =0;

        DBConnection x= DBConnection.getInstance();
        logger.info("initialization with DB connection complete");
    }


}


