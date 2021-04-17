package com.flipkart.utils;

public class ScreenWork {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mainHeadingPrint(){

        System.out.println("Course Registration System");



    }



}
