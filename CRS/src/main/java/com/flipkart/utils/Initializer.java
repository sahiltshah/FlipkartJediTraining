package com.flipkart.utils;

import com.flipkart.bean.SpecialUser;
import com.flipkart.temporaryDB.DB;

public class Initializer {
    //adding debit card details
    //adding initial courses and admin login credentials
    public void initialize(){
        SpecialUser specialUser=new SpecialUser();
        specialUser.type=1;
        specialUser.row=0;
        DB.loginMap.put("sahil",specialUser);
        DB.loginDetails.put("sahil","sahil");
    }


}


