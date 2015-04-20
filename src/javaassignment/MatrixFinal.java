/*     OYESH MANN SINGH 
       LUID: L20372791
       COCS4301  (Programming for Graduate Students)
       PROGRAM 5: Determinant and Inverse of Square matrices
  
 *      Program Assessment:
 *         The source code compiled successfully and produced the required Determinant and Inverse matrix if existed
 *         This program takes input from the file named "program5.inp" and prints the result 
 *         in the output file named as "program5.out" . If there is no "program5.out", then it creates and prints the result
 */

package javaassignment;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;	// a subclass of IOException
import java.io.IOException;

public class MatrixFinal {

    // private instance variables
    private static int MAX = 20;
    private int size;
    private double[][] original = new double[MAX][MAX];
    private double[][] inverse = new double[MAX][MAX];
    // inverse of original when it does exists
    private boolean exists;	// indicator of whether or not the inverse does exist. 
    private double determinant;            // the determinant of original matrix.

    // public methods
    public MatrixFinal(Scanner inputStream) // This one-argument constructor will create an object of this class, MatrixFinal, 
    // by reading the given inputStream that must have been connected to an physical 
    // input file by the main() for the size
    // of a new object and its original matrix of that given size.
    {
        exists = false;	// Initially assume the non-existence of inverse.
        try {
            if (inputStream.hasNext()) {
                size = inputStream.nextInt();	//The instance variable size is defined.
                for (int row = 0; row <= size - 1; row++) {
                    for (int col = 0; col <= size - 1; col++) {
                        original[row][col] = inputStream.nextDouble();
                    }
                }
            } else // an input file exception 
            {
                throw new FileNotFoundException("ERROR:::END-OF-INPUT-FILE:::");
            }

        } // end of the construction and also of try block
        catch (FileNotFoundException e) {
            System.out.println("\n:::INPUT FILE NOT FOUND OR SOMETHING ELSE:::");
            System.exit(0);
        }	// end of catch block
    }	// end of constructor MatrixFinal()
    ////////////////////////////////////////////////////////////////////
    // end of constructor method.
    ////////////////////////////////////////////////////////////////////

    public void calInverse() // Calculates two instance variables, inverse and exists
    {
        exists = false;		// assume that the original matrix is not invertible.
        // The constructor already made this assumption.
        double[][] result = new double[size][size];
        double[][] temp = new double[size][size];
        int row, col, belowRow, index;
        double tolerance = 0.000000000001;
        double factor;
        boolean found;
        // Initialize inverse to an identity matrix of given size and copy original matrix
        // into temp. So initially temp=original.
        for (row = 0; row <= size - 1; row++) {
            for (col = 0; col <= size - 1; col++) {
                temp[row][col] = original[row][col];
                inverse[row][col] = 0.0;
            }
            inverse[row][row] = 1.0;
        }
        // Initialization and copying are over.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // added section of code to compute inverse
        for (col = 0; col <= size - 1; col++) {
            if (Math.abs(temp[col][col]) < tolerance) {
                found = false;
                belowRow = col + 1;
                while (!found && (belowRow <= size - 1)) {
                    
                    if (Math.abs(temp[belowRow][col]) > tolerance * 10.0) {
                        found = true;
                    }
                    if (!found) {
                        belowRow++;
                    }
                }

                if (!found) {                       //exits if not found
                    return;
                }
                exchange(temp, col, belowRow);      // exchanges the two rows 
                exchange(inverse, col, belowRow);   // exchanges the two rows
            }

            for (row = 0; row <= size - 1; row++) {
                if (row != col) {

                    factor = temp[row][col] / temp[col][col];

                    for (int column = 0; column <= size - 1; column++) {
                        temp[row][column] = temp[row][column] - factor * temp[col][column];

                        inverse[row][column] = inverse[row][column] - factor * inverse[col][column];
                    }
                }
            }
        }

        for (row = 0; row <= size - 1; row++) {
            for (col = 0; col <= size - 1; col++) {
                inverse[row][col] = inverse[row][col] / temp[row][row];
            }
        }
        exists = true;	// inverse found in success.
    }	// end of calInverse()
    //////////////////////////////////////////////////////////////////

    public void exchange(double[][] mat, int row1, int row2) // exchanges the two given rows of the given matrix of doubles
    {
        double temp;
        for (int col = 0; col <= size - 1; col++) {
            temp = mat[row1][col];
            mat[row1][col] = mat[row2][col];
            mat[row2][col] = temp;
        }
    }	// end of exchange()
    //////////////////////////////////////////////////////////////////

    public void matrixMultiply() // Multiplies the matrix original and the matrix inverse
    // only when the latter, in fact, exists.
    // Additionally, prints out the product matrix to System.in when multiplication
    // actually carried out as the product must be an identity matrix
    // if the inverse is correctly calculated.
    {
        double[][] product = new double[MAX][MAX];
        double temp, sum;
        for (int row = 0; row <= size - 1; row++) {
            for (int col = 0; col <= size - 1; col++) {
                sum = 0.0;
                for (int mid = 0; mid <= size - 1; mid++) {
                    sum += original[row][mid] * inverse[mid][col];
                }
                product[row][col] = sum;
                ////////////////////////////////////////////////////////
            }
        }
        // Computation of matrix product is over, and time to print it out to the screen.
        System.out.println("\nPRODUCT MATRIX SUPPOSED TO BE AN IDENTITY MATRIX FOLLOWS:::\n");
        for (int row = 0; row <= size - 1; row++) {
            for (int col = 0; col <= size - 1; col++) {
                System.out.printf("%10.6f", product[row][col]);
            }
            System.out.println();
        }
        // printing is over and also end of method matrixMultiply()
    }	// end of method matrixMultiply()
    /////////////////////////////////////////////////////////////////

    public double getDeterminant() // Simply returns the instance variable, determinant.
    {
        return determinant;
    }	// end of this method().
    /////////////////////////////////////////////////////////////////

    public void calDeterminant() // Calculates the determinant of the matrix original by calling a recursive
    // method findDeterminant (double[][] mat, int howBig) and sets the Instance Variable,
    // determinant, at the same time.
    {
        determinant = findDeterminant(original, size);

    }	// end of method calDeterminant()
    ////////////////////////////////////////////////////////////////

    public double findDeterminant(double[][] mat, int howBig) // Calculates the determinant of the given square matrix of given size recursively
    // if the given size is at least 2.
    {
        int smallSize = howBig - 1;
        double[][] minor = new double[smallSize][smallSize];
        double sum = 0.0;
        double plusMinus = 1.0;
        // A major portion of the body of this method is deleted for you to figure out and complete
        if (howBig == 1) {
            sum = mat[0][0];
        } else if (howBig == 2) {
            sum = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        } else {
            for (int col = 0; col <= howBig - 1; col++) {
                for (int row = 0; row <= smallSize - 1; row++) {
                    for (int column = 0; column <= smallSize - 1; column++) {
                        if (column < col) {
                            minor[row][column] = mat[row + 1][column];
                        } else {
                            minor[row][column] = mat[row + 1][column + 1];
                        }
                    }
                }
                sum += plusMinus * mat[0][col] * findDeterminant(minor, smallSize);
                plusMinus *= -1.0;
            }
        }
        return sum;	// sum is the determinant.

    }	// end of findDeterminant()
    //////////////////////////////////////////////////////////////////////////

    public boolean getExists() // Simply returns the boolean instance variable, exists.
    {
        return exists;
    }       // end of method getExists()
    //////////////////////////////////////////////////////////////////////////

    public void filePrint(PrintWriter outputStream) // Prints both the matrix original and the matrix inverse to an output file.
    {
        int row, col;
        try {
            if (outputStream == null) {
                throw new FileNotFoundException("OUTPUT FILE NOT FOUND:::");
            }
            outputStream.println("\nTHE ORIGINAL MATRIX:::");
            for (row = 0; row <= size - 1; row++) {
                for (col = 0; col <= size - 1; col++) {
                    outputStream.printf("%12.5f", original[row][col]);
                }
                outputStream.println();
            }	// end of printing the entire matrix original
            if (exists) // Then inverse exists and is calculated.
            {
                outputStream.println("\nTHE INVERSE MATRIX EXISTS:::");
                for (row = 0; row <= size - 1; row++) {
                    for (col = 0; col <= size - 1; col++) {
                        outputStream.printf("%12.5f", inverse[row][col]);
                    }
                    outputStream.println();
                }
            } // end of printing an existing inverse.
            else // inverse does not exist.
            {
                outputStream.println("\nTHE INVERSE DOES NOT EXIST, SORRY:::");
            }
            // end of printing both matrices when both do exist...
        } // end of try block
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Program aborted:::");
            System.exit(0);
        }	// end of catch block
    }	// end of method filePrint()
    //////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        PrintWriter outputStream = null;      // To be safe,
        Scanner inputStream = null;       // To be safe.
        int inputCount = 0;
        MatrixFinal matObj;
        try {
            FileInputStream inStream = new FileInputStream("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 1\\Assignment 5\\program5.inp");
            inputStream = new Scanner(inStream);
            FileOutputStream outStream = new FileOutputStream("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 1\\Assignment 5\\program5.out"); //appending disabled here
            outputStream = new PrintWriter(outStream);
            while (inputStream.hasNext()) {
                inputCount++;
                matObj = new MatrixFinal(inputStream);  //creates the matrix object taking input from the file
                matObj.calInverse();    //calculates the inverse matrix
                matObj.filePrint(outputStream); //prints the output in the output file
                if (matObj.getExists()) 
                {
                    matObj.matrixMultiply();    //multiplies the matrix to get identity matrix
                }
                matObj.calDeterminant(); //calculates the determinant of matrix
                outputStream.println("\nDETERMINANT :::" + matObj.getDeterminant());
            }	// all input matrices of varying sizes have been processed

            outputStream.close();
            inputStream.close();
        } // end of try block
        catch (FileNotFoundException e) {
            System.out.println("SOME EXCEPTION WITH INPUT?OUTPUT FILES:::");
            System.exit(0);
        }
        System.out.println("\nMY PROGRAM PROCESSED:::" + inputCount + "  OBJECTS IN SUCCESS.");
    }	// end of main()
}		// end of class MatrixFinal
