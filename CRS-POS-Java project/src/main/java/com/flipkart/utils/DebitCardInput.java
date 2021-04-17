package com.flipkart.utils;

import com.flipkart.bean.DebitCard;

import java.util.Scanner;

import static com.flipkart.utils.ScannerInput.input_obj;

public class DebitCardInput {
    public static DebitCard DebitCardInputFunction(){
        DebitCard debitCard = new DebitCard("");
        System.out.println("Enter the number: ");
        debitCard.setCardNumber(input_obj.nextLine());
        System.out.println("Enter the cvv: ");
        debitCard.setCvv(input_obj.nextInt());
        System.out.println("Enter the name: ");
        debitCard.setName(input_obj.nextLine());
        System.out.println("Enter the Balance: ");
        debitCard.setBalance(input_obj.nextFloat());
        return debitCard;
    }

    public static DebitCard DebitCardStudentInputFunction(){
        Scanner sc= new Scanner(System.in);
        DebitCard debitCard = new DebitCard("");
        System.out.println("Enter the number: ");
        debitCard.setCardNumber(sc.nextLine());
        System.out.println("Enter the cvv: ");
        debitCard.setCvv(sc.nextInt());

        return debitCard;
    }
}
