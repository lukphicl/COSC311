// Luke Clover
// HW 9/6/2018
// https://github.com/lukphicl/COSC311/blob/master/hw-0906.java

package javaapplication2;
import java.util.Scanner;

public class JavaApplication2 {

    public static int[] repeat(int[] a, int factor) {
        
        if (factor <= 0)
        {
            int [] newArray = new int [0];
            return newArray;
        }
        
        int[] newArray = new int[factor * a.length];
        
        for (int i = 0; i < newArray.length; ++i)
            newArray[i] = a[i%a.length];

        return newArray;
    }
    
    public static void printArray(int[] array)
    {
        System.out.print("{");
        for (int i = 0; i < array.length; ++i)
            System.out.print(array[i] + " ");
        System.out.println("}");
    }
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        int[] arr = {1, 2, 3, 4, 5}; int newSize = 0;
        System.out.println("Here's your starting array...");
        printArray(arr);
        System.out.print("How many times would you like your array repeated? ");
        int rep = keyboard.nextInt();
        
        int[] x = repeat(arr, rep);
        printArray(x);
        
    }
    
}
