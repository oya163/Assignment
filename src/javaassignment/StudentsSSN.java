package javaassignment;

/**
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 COCS4301 (Programming for Graduate Students)
 * PROGRAM 8: Implementing the Comparable Interface and Define two compareTo() 
 *            methods
 * 
 * This is "StudentsSSN" class which overrides compareTo() and toString() 
 * methods to sort the values of array according to the SSN and 
 * to print the values respectively.
 * ****************************************************************************
 */

public class StudentsSSN implements Comparable<StudentsSSN>{
    private String lastname;
    private int id;
    private long SSN;
    
    //null initializer
    public StudentsSSN(){
        this.lastname = null;
        this.id = 0;
        this.SSN = 0;
    }
    
    //initializer
    public StudentsSSN(String lName, int ID, long SSN){
        this.lastname = lName;
        this.id = ID;
        this.SSN = SSN;
    }
    
    //accessor for Last Name
    public String getLastName(){
        return lastname;
    }
    
    //accessor for ID
    public int getID(){
        return id;
    }
    
    //accessor for SSN
    public long getSSN(){
        return SSN;
    }
    
    //mutator for Last Name
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
    
    //mutator for ID
    public void setID(int ID){
        this.id = ID;
    }
    
    //mutator for SSN
    public void setSSN(long SSN){
        this.SSN = SSN;
    }
    
    //overriding method compareTo
    @Override
    public int compareTo(StudentsSSN other) {
        int retval = 0;
        if(this.SSN > other.SSN){
            retval = 1;
        }
        else if(this.SSN == other.SSN)
            retval = 0;
        else
            retval = -1;
        return retval;
    }
    
    //overriding method toString
    @Override
    public String toString(){
        return this.getLastName() + "\t" + this.getID() + "\t" + this.getSSN();
    }
}    