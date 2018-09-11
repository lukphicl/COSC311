// Luke Clover
// HW 9/6/2018
// https://github.com/lukphicl/COSC311/blob/master/hw-0906.java

package javaapplication2;
import java.util.Scanner;

public class JavaApplication2 {

    public static int[] repeat(int[] a, int factor) {
        
        int newSize = factor * a.length;
        System.out.println("Here's your new, repeated array");
        int[] newArray = new int[newSize];
        
        for (int i = 0; i < newSize; ++i)
        {
            if ((i + 5) % 5 == 0)
                newArray[i] = a [0];
            if ((i + 5) % 5 == 1)
                newArray[i] = a [1];
            if ((i + 5) % 5 == 2)
                newArray[i] = a [2];
            if ((i + 5) % 5 == 3)
                newArray[i] = a [3];
            if ((i + 5) % 5 == 4)
                newArray[i] = a [4];
        }
        return newArray;
    }
    
    public static void printArray(int[] array)
    {
        System.out.print("{");
        for (int i = 0; i < array.length; ++i)
            System.out.print(array[i] + " ");
        System.out.print("}");
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        int[] arr = {1, 2, 3, 4, 5};
        int newSize = 0;
        System.out.println("Hello, world!");
        System.out.println("Here's your starting array...");
        printArray(arr);
        System.out.print("How many times would you like your array repeated? ");
        int rep = keyboard.nextInt();
        
        if (rep <= 0) 
           System.out.println("{}");
        else 
        {
            newSize = 5 * rep;
            int[] x = new int[newSize];
            x = repeat(arr, rep);
            printArray(x);
        }
        
    }
    
}
