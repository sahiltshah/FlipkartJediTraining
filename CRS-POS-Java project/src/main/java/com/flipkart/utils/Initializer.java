package com.flipkart.utils;

import com.flipkart.bean.SpecialUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Initializer {
    //adding debit card details
    //adding initial courses and admin login credentials
    public static final Logger logger = LogManager.getLogger(Initializer.class);
    public void initialize(){
        SpecialUser specialUser=new SpecialUser();
        specialUser.type=1;
        specialUser.id =0;

        DB x= DB.getInstance();
        logger.info("initialization with DB connection complete");
    }


}


