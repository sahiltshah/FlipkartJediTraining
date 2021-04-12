package com.flipkart.global;

import com.flipkart.bean.*;

public class GlobalVariables {

    //Global variables
    public static int globalTransactionId = 1000;
    public static int globalRollNumber = 1001;
    public static int globalFacultyId = 9002;
    public static int globalNotificationId = 1000;

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
