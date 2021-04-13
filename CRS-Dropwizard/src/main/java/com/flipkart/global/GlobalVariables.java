package com.flipkart.global;

import com.flipkart.bean.*;

public class GlobalVariables {

    //Global variables
    public static int globalTransactionId = 1010;
    public static int globalRollNumber = 1010;
    public static int globalFacultyId = 9022;
    public static int globalNotificationId = 1020;

    //Assumption no threading of API calls. Only one API call at a time
    public static String globalExceptionMessage = "";
    public static boolean checkNoException(){
        return globalExceptionMessage.length() == 0;
    }

    public static void flushExceptionMessage(){
        globalExceptionMessage="";
    }

    public static void appendException(String message){
        if(globalExceptionMessage.length()>0)
            globalExceptionMessage += ("|" + message);
        else
            globalExceptionMessage = "Exception(s): " + message;
    }


}
