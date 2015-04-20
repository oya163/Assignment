package javaassignment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 COCS4301 (Programming for Graduate Students)
 * PROGRAM 7: Defining a New Exception Class and Catching Exceptions 
 * 
 * The objective of this program is to get acquainted with custom exception 
 * class and catching those exceptions. This program has new exception called
 * NonPositiveException and a predefined Exception InputMismatchException. This
 * program takes input numbers, and checks if it is positive and integer or not.
 * If it is not positive and not integer, then it throws an exception message.
 * Finally, it displays the average of all the valid input integers.
 */


class NonPositiveException extends Exception { //new exception class

    private int nonPositiveNumber = 0;  //variable declaration

    public NonPositiveException() {	// no-argument constructor
        super("Non Positive Number");
    }

    public NonPositiveException(String message) {// one-String-argument constructor
        super(message);
    }

    public NonPositiveException(int number) {	// one-integer-argument constructor
        super("Non Positive Number");
        this.nonPositiveNumber = number;
    }

    public int getNonPositiveNumber() { //actuator
        return nonPositiveNumber;
    }
}

public class ExceptionExample {

    public static void main(String[] args) {
        //variable declaration
        int numberTotal = 0, originalTotal = 0, numberInput = 0, sum = 0;
        double avg = 0;
        
        //input from the user
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nPlease input the interation number : ");
        numberTotal = keyboard.nextInt();
        originalTotal = numberTotal;   
        
        System.out.println();
        
        while (numberTotal > 0) {
            try {
                System.out.println("Please enter a positive number ");
                numberInput = keyboard.nextInt();
                if (numberInput < 0) {  //check number is non positive
                    throw new NonPositiveException(numberInput);    
                }

                sum = sum + numberInput;
                numberTotal--;
                
            } catch (NonPositiveException e) {  //catches nonpositive exception
                System.out.println(e.getNonPositiveNumber() + " is not a Positive Integer\n");
            } catch (InputMismatchException e) {    //catches noninteger exception
                System.out.println("Non-Integer value entered. Please enter valid Integer\n");
                keyboard.next();
            }

        }
        avg = (double) sum / originalTotal; //average of the valid input numbers
        System.out.println("\nThe average of given Positive number is " + avg);
    }
}
