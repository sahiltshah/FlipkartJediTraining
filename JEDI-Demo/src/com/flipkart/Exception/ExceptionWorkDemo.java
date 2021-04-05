package com.flipkart.Exception;

public class ExceptionWorkDemo {
    public static void main(String[] args) {

        try {
            int a = 30,b=0,c;
            int[] arr = {1,2,3};

            System.out.println(arr[4]);
            c=a/b;
            System.out.println("C: "+c);
        }

        catch (ArithmeticException ex){
            System.out.println("we found an exception "+ex.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Found exception: " + ex.getMessage());
        }
        finally {
            System.out.println("Finally we come to: ");
            //We have to close all the objects inside the finally block to release the overhead of the Java container

        }
        System.out.println("Random after finally");



    }

    void randomnote()
    {
        /*
        We can create the custom exception in java to extend the exception class.
        Inside this custom exception class, could be relevant knowledge related to the exception.
        we can just raise the exception using throw and throws keyword
         */
    }
}
