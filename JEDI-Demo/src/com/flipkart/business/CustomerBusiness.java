
package com.flipkart.business;

import com.flipkart.bean.Customer;

import java.util.Scanner;

public class CustomerBusiness {




        Customer myCustomers[] = new Customer[] {new Customer(), new Customer(), new Customer()};


        // craete customer methods
        public void createCustomer(){

            System.out.println("create Customer Methods-->");

            // First Recod Insert
            myCustomers[0].setCustId(101);
            myCustomers[0].setCustName("Training");
            myCustomers[0].setCustAddress("IBM");

            // Second Recod Insert

            myCustomers[1].setCustId(102);
            myCustomers[1].setCustName("Lecture");
            myCustomers[1].setCustAddress("3i-Infotech");


            // third Record

            myCustomers[2].setCustId(103);
            myCustomers[2].setCustName("Lecture1");
            myCustomers[2].setCustAddress("3i-Infotech ABC");

        }

        // list Customer

        public void listCustomer(){

            System.out.println("list of Customers");

            // Also note that we have given i < 4 and not i < 5 - I ll explain Why.
            for (int i = 0; i < myCustomers.length; i++) {

                System.out.println("The id of " + i + " Customer ID is :" + myCustomers[i].getCustId() + " and name is : " +  myCustomers[i].getCustName() + "" + myCustomers[i].getCustAddress());

            }

        }


        // Update Customer


        public void updateCustomer(){

            System.out.println("update customer-->");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter index to be updated: ");

            int index = myObj.nextInt();  // Read user input
            if(index < myCustomers.length){
                System.out.println("Enter the new name: ");
                myCustomers[index].setCustName(myObj.nextLine());
                System.out.println("Enter the new address: ");
                myCustomers[index].setCustAddress(myObj.nextLine());
            }


        }

        // Delete Customer

        public void deleteCustomer(){

            System.out.println("delete Customer-->");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter customer index to be deleted: ");
            int index = myObj.nextInt();  // Read user input
            if(index < myCustomers.length){
                myCustomers[index].setCustId(-1);
                myCustomers[index].setCustName("");
                myCustomers[index].setCustAddress("");
            }


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
