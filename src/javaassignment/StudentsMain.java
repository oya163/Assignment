package javaassignment;

/**
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 COCS4301 (Programming for Graduate Students)
 * PROGRAM 8: Implementing the Comparable Interface and Define two compareTo() 
 *            methods
 * 
 * Objective of this program is to:
 * 1.	Effectively implement an Interface by defining all of its abstract 
 *      (by default) methods.
 * 2.	Create a Class that implements the Interface Comparable so that objects 
 *      of this Class can be simply sorted by resorting to the predefined 
 *      Arrays.sort method. 
 * 3.	Define the abstract method compareTo() in two different ways so that an 
 *      array of objects of this Class can be sorted by different sorting keys. 
 * 4.	Construct an array of objects of a Class.
 * 5.	Override the toString() method in order to effectively print out an 
 *      object of this new class.
 * 
 * This is the main program which creates an array object of classes 
 * "StudentsLastName" and "StudentsSSN", then it reads the value from file
 * and sorts the arrays according to the LastName and SSN
 * ****************************************************************************
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class StudentsMain {
    String lastname;
    int id;
    long SSN;
    
    public static void main(String[] args) throws FileNotFoundException {
        // file variable declaration
        File fileOfStudent;
        
        //input variable declaration to read from file
        Scanner inputFromFile;
        
        //array object created
        StudentsLastName[] studentListOfLastName = new StudentsLastName[10];
        StudentsSSN[] studentListOfSSN = new StudentsSSN[10];
        StudentsLastName eachStudent = new StudentsLastName();
        
        fileOfStudent = new File("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 2\\Assignment 8\\inputStudents.txt");
        if(!fileOfStudent.exists()){
            throw new FileNotFoundException("File is not found");
        }
        
        inputFromFile = new Scanner(fileOfStudent);
        
        //read values from file and placed into each array
        for(int i =0;inputFromFile.hasNextLine();i++){
            eachStudent.setLastName(inputFromFile.next());
            eachStudent.setID(inputFromFile.nextInt());
            eachStudent.setSSN(inputFromFile.nextLong());
            studentListOfLastName[i] = new StudentsLastName(eachStudent.getLastName(), eachStudent.getID(), eachStudent.getSSN());
            studentListOfSSN[i] = new StudentsSSN(eachStudent.getLastName(), eachStudent.getID(), eachStudent.getSSN());
        }
        
        /********Display before sorting the array *******/
        System.out.println();
        System.out.println("Before Sorting");
        System.out.println("Name \t ID \t SSN");
        
        for(int i = 0; i < studentListOfLastName.length;i++){
            System.out.println(studentListOfLastName[i].toString());
        }
        
        //Sorting array according to the Last Name
        Arrays.sort(studentListOfLastName);
        
        /********Display after sorting the array according to Last Name*******/
        System.out.println();
        System.out.println("After Sorting according to Last Name");
        System.out.println("Name \t ID \t SSN");
        
        for(int i = 0; i < studentListOfLastName.length;i++){
            System.out.println(studentListOfLastName[i].toString());
        }   
        
        //Sorting array according to the SSN
        Arrays.sort(studentListOfSSN);
        
        /********Display after sorting the array according to SSN*******/
        System.out.println();
        System.out.println("After Sorting according to SSN");
        System.out.println("Name \t ID \t SSN");
        
        for(int i = 0; i < studentListOfSSN.length;i++){
            System.out.println(studentListOfSSN[i].toString());
        }   
    }
    
}
