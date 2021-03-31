package com.test.array;

import com.flipkart.business.CustomerBusiness;

public class TestArray {
    public static void main(String[] args) {

        /*
        int arrayofInt[] = new int[] {10,20,30,40};
        for(int i=0;i<arrayofInt.length;i++){
            System.out.println(i + arrayofInt[i]);
        }


        String names[]= new String[]{"abc","def","sha"};

        for(int i=0;i<names.length;i++){
            System.out.println(i + names[i]);
        }

         */

        CustomerBusiness customerBusiness = new CustomerBusiness();
        customerBusiness.runner();
        System.out.println();



    }
}
