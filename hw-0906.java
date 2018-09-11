/*
 * Luke Clover
 * HW 9/6/2018
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.Scanner;

public class JavaApplication2 {


    public static int repeat(int[] a, int factor) {
        
        int newSize = factor * a.length;
        System.out.println("Here's your new, repeated array");
        int[] newArray = new int[newSize];
        
        for (int i = 0; i < newSize; ++i) // Fills new array
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
        printArray(newArray);
        return 0;
    }
    
    public static void printArray(int[] array)
    {
        System.out.print("{");
        for (int i = 0; i < array.length; ++i)
            System.out.print(array[i] + " ");
        System.out.print("}");

    }
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Hello, world!");
        System.out.println("Here's your starting array...");
        printArray(arr);
        System.out.println();
        System.out.print("How many times would you like your array repeated? ");
        int rep = keyboard.nextInt();
        if (rep <= 0)
            System.out.print("{}");
        else
            repeat(arr, rep);
        System.out.println();
    }
    
}
