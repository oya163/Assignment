package javaassignment;

/**
 *
 * @author Oyesh Mann Singh
 */
public class Program4 {


    public static void main(String[] args) {
        OneDimArray firstObj = new OneDimArray(1000);
        //OneDimArray secondObj = new OneDimArray(10000);
        System.out.println("Before sort ");
        firstObj.displayOriginal();
        System.out.println();
        System.out.println("After sort ");
        firstObj.selectSort();
        firstObj.display();
    }
    
}
