package javaassignment;

/**
<<<<<<< HEAD
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 
 * COCS4301 (Programming for Graduate Students)
 * PROGRAM 11: Searching Input Files for Common Names Using HashSet<T> 
 *             and ArrayList<T> Classes
 * 
 * OBJECTIVES:
 *1.	Read two text files containing 1,000 most popular boy names and 1,000 
 *      popular girl names, respectively.
 *2.	Add all 1,000 boy names to a HashSet.
 *3.	After all boy names have been added, when trying to add a girl name to 
 **     the same Hashset fails, it is because that name is common, namely both 
 **     boys and girls have that same name as popular.
 *4.	All those common names will be added to an ArrayList defined.
 *5.	Create a ListIterator<String> for an ArrayList<String> to be used in 
 **     outputting all its contents. 
 * *****************************************************************************
 */


import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ListIterator;


public class HashSetDemo {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //input and output file declared
        FileInputStream boysFile = new FileInputStream("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 3\\Program 11\\boynames.txt");
        FileInputStream girlsFile = new FileInputStream("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 3\\Program 11\\girlnames.txt");
        PrintWriter writer = new PrintWriter("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 3\\Program 11\\Program 11.out", "UTF-8");
        
        //input created for boys and girls name
        Scanner boysInput = new Scanner(boysFile);
        Scanner girlsInput = new Scanner(girlsFile);
        
        //collection list declaration
        HashSet<String> nameHash = new HashSet<>(1000);
        ArrayList<String> commonNamesArray = new ArrayList<>(1000);
        
        //variable declaration for names and code
        String name;
        int code;
        
        while(boysInput.hasNext()){
            name = boysInput.next();
            code = boysInput.nextInt();
            nameHash.add(name);
        }
        
        while(girlsInput.hasNext()){
            name = girlsInput.next();
            code = girlsInput.nextInt();
            if(!(nameHash.add(name))){
                commonNamesArray.add(name);
            }
        }
        
        ListIterator<String> itr = commonNamesArray.listIterator();
        
        while(itr.hasNext()){
            Object commonName = itr.next();
            writer.println(commonName);
        }
        writer.close();
    }
    
}
