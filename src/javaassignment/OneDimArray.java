package javaassignment;

/*
 Oyesh Mann Singh
 L20372791
 Program-4
 New Class Design, Sorting and File Outputs

 */
import java.util.Random;

public class OneDimArray {

    final static int MAX = 10;
    private int[] original = new int[MAX];
    public int[] sorted = new int[MAX];
    private int sum, max, min;
// Constructor will set the original array by generating random
// integers determined by the given parameter

    public OneDimArray(int upper) {
        Random generator = new Random();
        for (int index = 0; index <= MAX - 1; index++) {
            original[index] = generator.nextInt(upper) + 1;
        }
        // generated integers are between 1 and upper.
    }
//////////////////////////////////////////////////////////////////
//	Class methods() follow now!!!
//////////////////////////////////////////////////////////////////

    public void bubblesort() {	// first copy original to sorted so as to sort sorted thereby leaving original intact
        for (int index = 0; index <= MAX - 1; index++) {
            sorted[index] = original[index];
        }
        // now sort the copied sorted array.
        for (int last = MAX - 1; last >= 1; last--) {
            for (int first = 0; first <= last - 1; first++) {
                if (sorted[first] > sorted[first + 1]) // Swap them.
                {
                    int temp = sorted[first];
                    sorted[first] = sorted[first + 1];
                    sorted[first + 1] = temp;
                }
            }
        }
    }		//  end of bubblesort()
/////////////////////////////////////////////////////////////////////////////////////////

    public void selectSort() {
        int currentMin = 0, temp = 0;
        for (int index = 0; index <= MAX - 1; index++) {
            sorted[index] = original[index];
        }
        for (int first = 0; first <= MAX-1; first++) {
            currentMin = first;
            for (int second = first + 1; second <= MAX - 1; second++) {
                if (sorted[second] < sorted[currentMin]) {
                    currentMin = second;
                }
            }

            temp = sorted[currentMin];
            sorted[currentMin] = sorted[first];
            sorted[first] = temp;

        }

    }
/////////////////////////////////////////////////////////////////////////////////////////

    public void display() // Displays sorted array
    {
        for (int index = 0; index <= MAX - 1; index++) {
            System.out.printf("%8d", sorted[index]);
            if ((index + 1) % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void displayOriginal() // Displays sorted array
    {
        for (int index = 0; index <= MAX - 1; index++) {
            System.out.printf("%8d", original[index]);
            if ((index + 1) % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void calSum() //    calculates the sum of all integers in the array and sets instance variable sum to this calculated total.
    {
        int total = 0;
        for (int index = 0; index <= MAX - 1; index++) {
            total += original[index];
        }
        sum = total;

    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    public int getSum() {
        return sum;
    }

    public void quickSort() {
        int currentMinPosition = 0, temp = 0, pivot = MAX-1;
        pivot = original[MAX-1];
        for (int i =0; i<= MAX-1;i++){
            
        }

    }

    public int calMax() {
        return 0;
    }

    public int calMin() {
        return 0;
    }

    public int getMax() {
        return 0;
    }

    public int getMin() {
        return 0;
    }

}
