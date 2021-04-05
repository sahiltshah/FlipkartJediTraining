
package com.flipkart.business;

import com.flipkart.bean.Customer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CustomerBusiness {




        //Customer myCustomers[] = new Customer[] {new Customer(), new Customer(), new Customer()};
        List<Customer> myCustomers = new ArrayList<Customer>(3);

        private static Logger logger = Logger.getLogger(CustomerBusiness.class);

        // craete customer methods
        public void createCustomer(){

            System.out.println("create Customer Methods-->");
            // First Recod Insert
            myCustomers.get(0).setCustId(101);
            myCustomers.get(0).setCustName("Training");
            myCustomers.get(0).setCustAddress("IBM");

            // Second Recod Insert

            myCustomers.get(1).setCustId(102);
            myCustomers.get(1).setCustName("Lecture");
            myCustomers.get(1).setCustAddress("3i-Infotech");


            // third Record

            myCustomers.get(2).setCustId(103);
            myCustomers.get(2).setCustName("Lecture1");
            myCustomers.get(2).setCustAddress("3i-Infotech ABC");

        }

        // list Customer

        public void listCustomer(){

            System.out.println("list of Customers");

            // Also note that we have given i < 4 and not i < 5 - I ll explain Why.
            for(Customer value : myCustomers){
                logger.info(" Customer ID is :" + value.getCustId() + " and name is : " +  value.getCustName() + "" + value.getCustAddress());
            }

        }


        // Update Customer


        public void updateCustomer(){

            System.out.println("update customer-->");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter index to be updated: ");

            int index = myObj.nextInt();  // Read user input
            if(index < myCustomers.size()){
                System.out.println("Enter the new name: ");
                myCustomers.get(index).setCustName(myObj.nextLine());
                System.out.println("Enter the new address: ");
                myCustomers.get(index).setCustAddress(myObj.nextLine());
            }


        }

        // Delete Customer

        public void deleteCustomer(){

            System.out.println("delete Customer-->");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter customer index to be deleted: ");
            int index = myObj.nextInt();  // Read user input
            myCustomers.remove(index);


        }

        public void modifyRecords(){
            int choice;
            System.out.println("Enter 1 to Modify and 2 to Delete: ");
            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextInt();
            switch(choice){
                case 1:updateCustomer(); break;
                case 2:deleteCustomer(); break;
            }

        }

        public void runner(){
            for(int i=0;i< 10;i++){
                modifyRecords();
                listCustomer();
            }
        }




    }
