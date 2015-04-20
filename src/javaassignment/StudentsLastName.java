package javaassignment;

/**
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 COCS4301 (Programming for Graduate Students)
 * PROGRAM 8: Implementing the Comparable Interface and Define two compareTo() 
 *            methods
 * 
 * This is "StudentsLastName" class which overrides compareTo() and toString() 
 * methods to sort the values of array according to the Last name and 
 * to print the values respectively.
 * ****************************************************************************
 */

public class StudentsLastName implements Comparable<StudentsLastName>{
    private String lastname;
    private int id;
    private long SSN;
    
    //initializer
    public StudentsLastName(){
        this.lastname = null;
        this.id = 0;
        this.SSN = 0;
    }
    
    //initializer
    public StudentsLastName(String lName, int ID, long SSN){
        this.lastname = lName;
        this.id = ID;
        this.SSN = SSN;
    }
    
    //accessor
    public String getLastName(){
        return lastname;
    }
    
    //accessor
    public int getID(){
        return id;
    }
    
    //accessor
    public long getSSN(){
        return SSN;
    }
    
    //mutator
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
    
    //mutator
    public void setID(int ID){
        this.id = ID;
    }
    
    //mutator
    public void setSSN(long SSN){
        this.SSN = SSN;
    }
    
    //overriding method compareTo
    @Override
    public int compareTo(StudentsLastName other) {
        int retval = this.lastname.compareTo(other.lastname);
        return retval == 0 ? 0 : retval;
    }
    
    //overriding method toString
    @Override
    public String toString(){
        return this.getLastName() + "\t" + this.getID() + "\t" + this.getSSN();
    }
    
}
