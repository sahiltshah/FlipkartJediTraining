package com.flipkart.validator;
import com.flipkart.exception.AuthenticationException.*;
import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.AuthenticationOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class  AuthenticationValidator {
    public static final Logger logger = LogManager.getLogger(AuthenticationValidator.class);
    public static SpecialUser authenticate(String username, String password){
        SpecialUser specialUser = new SpecialUser();
        try{

            if(username.length()>10 || username.length()<1 || password.length()>10 || password.length()<1)
                throw new InvalidLoginInputException();
            else
            {
                return login(username, password);
            }

        }
        catch (InvalidLoginInputException e) {
            System.out.println("Username or password is invalid length");
            logger.debug(e.getMessage());
            return specialUser;
        }

    }

    public static SpecialUser login(String username, String password) {
        SpecialUser specialUser=new SpecialUser();
        try {

            AuthenticationOperations authenticationOperations = new AuthenticationOperations();
            ArrayList<String> ans = authenticationOperations.loginAccess(username);
            if(ans.size()!=0)
            {
                if(ans.get(0).equals(password)){
                    System.out.println("Authentication successful!");

                    ArrayList<SpecialUser> specialUsers = authenticationOperations.loginMapAccess(username);
                    if(specialUsers.size()==0)
                        throw new UserIDMapDoesNotExist();
                    else
                        return specialUsers.get(0);
                }
                else
                    throw new WrongPasswordException();
            }
            else throw new UserDoesNotExistException();


        }

        catch (UserDoesNotExistException e) {
            System.out.println("The username you enterred doesn't exist in our records. Try again!");
            logger.debug(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println("You enterred the wrong password! Try again");
            logger.debug(e.getMessage());
        } catch (UserIDMapDoesNotExist userIDMapDoesNotExist) {
            userIDMapDoesNotExist.printStackTrace();
        }
        return specialUser;
    }

    public boolean userNameDoesNotExist(String username){
        AuthenticationOperations authenticationOperations = new AuthenticationOperations();
        try{
            if(authenticationOperations.getSpecificUsername(username).equals(""))
                return true;
            else
                throw new UsernIDAlreadyInUse();
        } catch (UsernIDAlreadyInUse usernIDAlreadyInUse) {
            System.out.println("Username already in use. Try again! ");
            logger.debug(usernIDAlreadyInUse.getMessage());


        }

        return false;

    }

}
