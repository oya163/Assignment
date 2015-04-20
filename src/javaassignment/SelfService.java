package javaassignment;

/**
 *
 * @author Oyesh Mann Singh 
 * LUID - L20372791 
 * COSC4301 
 * Program 2 
 * Screen Input/Output and Loop Iteration Counting
 */
import java.util.Scanner;
public class SelfService {

    public static void main(String[] args) {
        double price = 0, total = 0, salesTax = 0.0825;
        Scanner keyboard = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            System.out.println("Enter number of items purchased.");
            System.out.println("followed by the unit cost.");
            System.out.println("Do not use a dollar sign.");
            int count = keyboard.nextInt();
            price = keyboard.nextDouble();
            total += (count * price);
            System.out.printf("Item number %d: %d items at $%.2f each.%n",i, count, price);

        }
        double grandTotal = total + (total * salesTax);
        System.out.println("**********************************************************");
        System.out.printf("Total amount due (including sales tax) $%.2f.%n", grandTotal);
        System.out.println("Please take your merchandise.");
        System.out.printf("Place $%.2f in an envelope %n", grandTotal);
        System.out.println("and slide it under the office door.");
        System.out.println("Thank you for using the self-service line.");
    }	// end of main() method  
}	// end of public class SelfService

