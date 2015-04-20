package javaassignment;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Oyesh Mann Singh
 * L20372791
 * COSC 4301 _48F_1 - Programming for Grad Students
 * Program-3
 * The Longest Palindrome in an Input File
 */
public class Palindrome {

    public static void main(String[] args){
        String wordOriginal, wordReverse="";        //string variable declaration
        int count=0;
        File f = null;
        Scanner inputStream= null;
        try {
            f = new File("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 1\\Assignment 3\\words.txt"); //file variable declaration
            inputStream = new Scanner(f);
        } catch (Exception e) {
            System.out.println("File not found "+ e.getMessage());
            System.exit(0);
        }
        
        
        while(inputStream.hasNext()){           //checks whether any string is remaining
            wordOriginal = inputStream.next(); 
            for(int i = wordOriginal.length()-1; i >= 0 ; i--){ //loops until the word length and puts each character into another variable
                wordReverse = wordReverse + wordOriginal.charAt(i);
            }
            if(wordReverse.toLowerCase().equals(wordOriginal.toLowerCase())){  //matches the original and reversed word by converting to lowercase
                System.out.println(wordOriginal);
                count++;
            }
            wordReverse = "";
        }
        System.out.printf("The total number of palindrome word is %d \n",count); //Prints the total number of Palidrome word       
    }
}
