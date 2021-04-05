package com.flipkart.dao.oldDAO;

import com.flipkart.Exception.AuthenticationException.UserDoesNotExistException;
import com.flipkart.Exception.AuthenticationException.UserIDMapDoesNotExist;
import com.flipkart.Exception.AuthenticationException.WrongPasswordException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.SpecialUser;
import com.flipkart.bean.Student;

import static com.flipkart.temporaryDB.DB.loginDetails;
import static com.flipkart.temporaryDB.DB.loginMap;

public class LoginAuthDB implements daoInterface.LoginFunctionsDB {

    @Override
    public SpecialUser login(String username, String password) {
        SpecialUser specialUser=new SpecialUser();
        try {
            if(loginDetails.containsKey(username))
            {
                if(loginDetails.get(username).equals(password)){

                    if(loginMap.containsKey(username)){
                        return loginMap.get(username);
                    }
                    else
                        throw new UserIDMapDoesNotExist();
                }

                else
                    throw new WrongPasswordException();
            }
            else
                throw new UserDoesNotExistException();
        }

        catch (UserDoesNotExistException e) {
            System.out.println("The username you enterred doesn't exist in our records. Try again!");
        } catch (WrongPasswordException e) {
            System.out.println("You enterred the wrong password! Try again");
        } catch (UserIDMapDoesNotExist userIDMapDoesNotExist) {
            userIDMapDoesNotExist.printStackTrace();
            System.out.println("The login details seem valid but the corresponding mapped record is not found");
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
