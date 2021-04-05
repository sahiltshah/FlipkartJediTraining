package com.flipkart.validator;
import com.flipkart.Exception.AuthenticationException.InvalidLoginInputException;
import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.oldDAO.LoginAuthDB;
public class  AuthenticationValidator {
    public static SpecialUser authenticate(String username, String password){
        SpecialUser specialUser = new SpecialUser();
        try{

            if(username.length()>10 || username.length()<1 || password.length()>10 || password.length()<1)
                throw new InvalidLoginInputException();
            else
            {
                LoginAuthDB loginAuthDB=new LoginAuthDB();
                return loginAuthDB.login(username, password);
            }

        }
        catch (InvalidLoginInputException e) {
            System.out.println("Username or password is invalid length");
            return specialUser;
        }

    }

}
