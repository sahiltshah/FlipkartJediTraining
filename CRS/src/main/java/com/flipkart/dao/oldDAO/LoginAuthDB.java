package com.flipkart.dao.oldDAO;

import com.flipkart.Exception.AuthenticationException.UserDoesNotExistException;
import com.flipkart.Exception.AuthenticationException.UserIDMapDoesNotExist;
import com.flipkart.Exception.AuthenticationException.WrongPasswordException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.SpecialUser;
import com.flipkart.bean.Student;

import java.util.ArrayList;

import static com.flipkart.dao.DB.loginAccess;
import static com.flipkart.dao.DB.loginMapAccess;
import static com.flipkart.temporaryDB.OldDB.loginDetails;
import static com.flipkart.temporaryDB.OldDB.loginMap;

public class LoginAuthDB implements daoInterface.LoginFunctionsDB {

    @Override
    public SpecialUser login(String username, String password) {
        SpecialUser specialUser=new SpecialUser();
        try {

            ArrayList<String> ans = loginAccess(username);
            if(ans.size()!=0)
            {
                if(ans.get(0).equals(password)){
                    System.out.println("Authentication valid");
                    ArrayList<SpecialUser> specialUsers = loginMapAccess(username);
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
        } catch (WrongPasswordException e) {
            System.out.println("You enterred the wrong password! Try again");
        } catch (UserIDMapDoesNotExist userIDMapDoesNotExist) {
            userIDMapDoesNotExist.printStackTrace();
        }
        return specialUser;
    }

    @Override
    public boolean returnType(String username) {
        return false;
    }

    @Override
    public boolean add_student(Student s) {
        return false;
    }

    @Override
    public boolean remove_student(Student s) {
        return false;
    }

    @Override
    public boolean add_professor(Professor p) {
        return false;
    }

    @Override
    public boolean remove_professor(Professor p) {
        return false;
    }

    @Override
    public boolean search_student(Student s) {
        return false;
    }

    @Override
    public boolean search_professor(Professor p) {
        return false;
    }
}
