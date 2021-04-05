package com.flipkart.validator;

import com.flipkart.bean.Student;
import com.flipkart.dao.LoginAuthDB;

public class AdminValidator {

    boolean validator;


    public boolean check_if_user_exists(Student student){
        LoginAuthDB loginAuthDB = new LoginAuthDB();
        return(loginAuthDB.search_student(student));
    }
}
