package javaassignment;
/*
 * ****************************************************************************
 * OYESH MANN SINGH 
 * LUID: L20372791 COCS4301 (Programming for Graduate Students)
 * PROGRAM 9: Stack Creation and PostFix Expression Evaluation
 * 
 * Objective of this program is to:
 * 1.	Deeper Understanding of Stack structures and primary operations.
 * 2.	Benefits of postfix expressions as opposed to infix expressions.
 * 3.	Design methods for evaluating postfix expressions. 
 * 4.	Manipulations of arrays of characters as opposed to Strings
 * 
 * This is the program which converts the infix expression into postfix 
 * expression and displays both of the expression and the calculated value.
 * ****************************************************************************
 Examples of Equivalent Postfix Expressions:

 Infix			Postfix
 -------------------------------------------------
 A+B*C                  ABC*+
 A+(B*C)                ABC*+
 (A+B)*C                AB+C*
 A+B+C*D*E             	AB+CD*E*+
 (A+B+C)*D*E          	AB+C+D*E*
 ((A+B+C)*D*E)       	AB+C+D*E*
 */

import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyStack {

    // private instance variables:
    private static int MAX = 100;
    private int top = -1;
    private int iSize;
    private int pSize = 0;	//postfix expression size
    private char store[] = new char[MAX]; // Stack storage
    private char infix[] = new char[MAX]; // input infix exp.
    private char postfix[] = new char[MAX]; // output postfix exp 
    private String inputt;
    private int inputValues[] = new int[3]; //to store values A, B and C
    private int Result[] = new int[MAX];     //To store the calculated results 
    /////////////////////////////////
    // public methods follow:

    public MyStack() {
        // The default constructor just calls input()
        // in creating a stack object by taking the input
        // infix expression
        input();
    }

    public void initialize() {
        push('(');
    }

    public MyStack(Scanner inputStream) {
        // The default constructor just calls input()
        // in creating a stack object by taking the input
        // infix expression
        for (int index = 0; index < 3; index++) {
            inputValues[index] = inputStream.nextInt();
        }
        //String inputt = JOptionPane.showInputDialog("Enter an infix expression < 60");
        inputt = inputStream.next();
        iSize = inputt.length();
        inputt.getChars(0, iSize, infix, 0);
    }

    public void input() /* This method will be to
     1. take an input infix expression as a String,
     2. store the input String into infix as an array of chars and
     3. set iSize to the length of input string.
     */ {

        String inputt = JOptionPane.showInputDialog("Enter an infix expression < 60");

        iSize = inputt.length();
        inputt.getChars(0, iSize, infix, 0);
        /* Note that in the above method getChars which converts the calling String, inputt in our case,
         into an array of characters, to be named infix, in our example:
         1. first argument is the starting index of the string, inputt in this case
         2. second argument is the number of characters to form an array of characters
         3. third argument is the name of the array of characters being formed
         4. last argument represents the starting index, subcript of the array being constructed
         */
        if (iSize > MAX) {
            System.err.println("ERROR:INPUT EXPRESSION TOO LONG:");
        }
    }

    ///////////////////////////////////////////////////
    public void convert() // This method is the primary method for performing the necessary 
    // conversion essentially defining the two instance variables,
    // postfix[] and pSize.
    {
        initialize();
        infix[iSize++] = ')';
        pSize = 0;
        char current;
        for (int index = 0; index < iSize; index++) {
            current = infix[index];
            if (Character.isLetter(current)) {
                insertPost(current);
            } else // the current char is an operator including parentheses.
            {
                while (sPrio(store[top]) > iPrio(current)) {
                    insertPost(pop());
                }
                if (sPrio(store[top]) < iPrio(current)) {
                    push(current);
                } else // they are the same and they are matching pair of parentheses
                {
                    pop();
                }
            }
        } 	// end of for loop
    }	// end of convert()
    ////////////////////////////////////////////////////////////////////////////

    public void insertPost(char current) {
        postfix[pSize] = current;
        pSize++;
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean isFull() {
        return (top >= MAX);
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean isEmpty() {
        return (top < 0);
    }

    ////////////////////////////////////////////////////////////////////////////
    public static int iPrio(char ch) {
        /*
         // No-static method call testing.
         if (isEmpty())  System.out.print (":E:");
         // Above causes a syntax error: Non-static method cannot be referenced
         if (!this.isEmpty()) System.out.print (":NE:");
         // Above causes a syntax error: Non-static variable this cannot be
         referenced from a static context.
         */
        switch (ch) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 3;
            case '/':
                return 3;
            case '(':
                return 10;
            case ')':
                return 0;
            default: {
                System.err.println("ILLEGAL INPUT OPERATOR:");
                return -1;
            }
        }		// end of switch
    }		// end of iPrio()
    ///////////////////////////////////////////////////////////////////////////

    public static int sPrio(char ch) {
        switch (ch) {
            case '+':
                return 2;
            case '-':
                return 2;
            case '*':
                return 4;
            case '/':
                return 4;
            case '(':
                return 0;
            default: {
                System.err.println("ILLEGAL STACK OPERATOR:");
                return -1;
            }
        }
    }		// end sPrio()
    ////////////////////////////////////////////////////////////////////////////

    public void push(char current) {
        if (isFull()) {
            System.err.println("STACK OVERFLOW:::");
            System.exit(1);
        } else {
            store[++top] = current;
        }
    }	// end of push()
    ////////////////////////////////////////////////////////////////////////////

    public char pop() {
        if (isEmpty()) {
            System.err.println("STACK ALREADY EMPTY:::");
            return '\\';
        } else {
            return (store[top--]);
        }
    }	// end pop()
    //////////////////////////////////////////////////////////////////////////////

    public int getValue(char currentChar) {
        int value = 0;
        switch (currentChar) {
            case 'A':
                value = inputValues[0];
                break;
            case 'B':
                value = inputValues[1];
                break;
            case 'C':
                value = inputValues[2];
                break;
            default:
                System.err.println("NO SUCH CHARACTER FOUND::");
                System.exit(1);
        }
        return value;
    }//end of getValue

    //////////////////////////////////////////////////////////////////////////////
    public int findResult() {
        char current;
        int Rin = 0;	          // index for the Result.
        for (int index = 0; index < pSize; index++) {
            current = postfix[index];
            if (Character.isLetter(current)) {
                Result[Rin] = getValue(current);
                Rin++;
            } else {
                if (current == '+') {
                    Result[Rin - 2] = Result[Rin - 2] + Result[Rin - 1];
                    Rin = Rin - 1;
                } else if (current == '-') {
                    Result[Rin - 2] = Result[Rin - 2] - Result[Rin - 1];
                    Rin = Rin - 1;
                } else if (current == '*') {
                    Result[Rin - 2] = Result[Rin - 2] * Result[Rin - 1];
                    Rin = Rin - 1;
                } else {
                    Result[Rin - 2] = Result[Rin - 2] / Result[Rin - 1];
                    Rin = Rin - 1;
                }
            }
        }
        return Result[0];
    }//end of findResult

    //////////////////////////////////////////////////////////////////////////////
    public static void main(String args[]) throws FileNotFoundException {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\Oyesh Mann Singh\\Documents\\Lamar\\Java\\Unit 2\\Assignment 9\\inputFile.txt");
        Scanner inputStream = new Scanner(inputFile);
        String output = "";
        MyStack stack;
        int times = 0;
        int result = 0;
        while (inputStream.hasNext()) {
            //for (int times = 1; times <= 3; times++) {
            stack = new MyStack(inputStream);    // This requires a default constructor.
            System.out.println("\nINFIX EXPRESSION :: " + String.valueOf(stack.infix, 0, stack.iSize));
            stack.convert();
            //    convert();
            // Above causes a syntax error:
            // Non-static method cannot be referenced from a static context     
            output += "\nINPUT CASE:::" + times;
            output += "\n\tINFIX EXPRESSION:::";
            for (int index = 0; index < stack.iSize - 1; index++) {
                output += stack.infix[index];
            }
            output += "\n\tPOSTFIX EXPRESSION:";
            output += String.valueOf(stack.postfix, 0, stack.pSize);
            // Note above is the exactly same as the following: 
            // output += String.valueOf (stack.postfix);
            result = stack.findResult();    //get the Total Calculate result of Postfix expression
            output += "\n\tTOTAL CALCULATED RESULT OF POSTFIX EXPRESSION:";
            output += result + "\n";
            times++;
        }
        output += "\nTHIS PROGRAM ENDS IN SUCCESS.";
        JOptionPane.showMessageDialog(null, output,
                "THE RESULT OF INFIX-POSTFIX CONVERSION",
                JOptionPane.INFORMATION_MESSAGE);
        // Testing static method call: iPrio()
        // No object qualification is needed. 
        output = "\nTESTING STATIC METHOD CALL:\niPrio('*')="
                + iPrio('*');
        System.out.println(output);
        System.exit(0);
    }
}       // end of class StackExample

