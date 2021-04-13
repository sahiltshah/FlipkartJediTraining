package com.flipkart.validator;
import com.flipkart.exception.AuthenticationException.*;
import com.flipkart.bean.SpecialUser;
import com.flipkart.dao.AuthenticationOperations;
import com.flipkart.global.GlobalVariables;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class  AuthenticationValidator {
    public static final Logger logger = Logger.getLogger(AuthenticationValidator.class);
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
        catch (InvalidLoginInputException ex) {
            System.out.println("Username or password is invalid length");
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));


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

        catch (UserDoesNotExistException ex) {
            System.out.println("The username you enterred doesn't exist in our records. Try again!");
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } catch (WrongPasswordException ex) {
            System.out.println("You entered the wrong password! Try again");
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
        } catch (UserIDMapDoesNotExist ex) {
            ex.printStackTrace();
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));
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
        } catch (UsernIDAlreadyInUse ex) {
            System.out.println("Username already in use. Try again! ");
            logger.debug(ex);
            GlobalVariables.appendException(String.valueOf(ex));

        }

        return false;

    }

}
