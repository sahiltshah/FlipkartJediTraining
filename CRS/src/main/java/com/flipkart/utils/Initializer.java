package com.flipkart.utils;

import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.DB;
import com.flipkart.temporaryDB.OldDB;

public class Initializer {
    //adding debit card details
    //adding initial courses and admin login credentials
    public void initialize(){
        SpecialUser specialUser=new SpecialUser();
        specialUser.type=1;
        specialUser.id =0;
        OldDB.loginMap.put("sahil",specialUser);
        OldDB.loginDetails.put("sahil","sahil");

        DB.makeConnection();
    }


}


