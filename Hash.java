// Luke Clover
// pp 1108
// https://people.emich.edu/lclover/COSC311/Homework/Week8/HW1108/Hash.java

package hash;
import java.util.Random;
import java.text.Format;

public class Hash {
    
    int hashArray [] = new int[8];
    double num;    
    public Hash() {
        for (int i = 0; i < hashArray.length; ++i)
            hashArray[i] = 0;
        num = 0;
    }
    
    public void showTable() {       
        System.out.println("index........value");
        System.out.println("------------------");
        for (int i = 0; i < hashArray.length; ++i)
            System.out.printf("%-3d %12d %n", i, hashArray[i]);
        System.out.println();
    }
    
    public int[] doubleSize(int[] arr) { 
        int temp[] = new int[arr.length * 2];
        int index;
        
        // Rehashes array
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != 0) {
                index = arr[i] % temp.length;
                while(temp[index] != 0)
                    index = (index+1) % temp.length;
                temp[index] = arr[i];
            }
        }       
        return temp;
    }
    
    public void insert(int data) {
        int index = data%hashArray.length;
        
        if ((num/hashArray.length) >= .75)
            hashArray = doubleSize(hashArray);
        
        // Probes as necessary, hashes to first open element
        while (hashArray[index] != 0)
            index = (index+1) % hashArray.length;
        hashArray[index] = data;
        ++num;        
    }

    public static void main(String[] args) {
        
        int inputs[] = new int[41];
        int hashInputs[] = new int[20];
        int max = inputs.length-1;
        int ix;
        Hash h = new Hash();
        Random rand = new Random(97);
        
        System.out.println("All possible inputs: ");
        for (int i = 0; i < inputs.length; ++i) {
            inputs[i] = (10+i);
            System.out.print(inputs[i] + " ");
            max = i;
        }
        System.out.println();
        
        System.out.println("Values that will be hashed:");
        for (int i = 0; i < 20; ++i) {
            ix = rand.nextInt(max);
            hashInputs[i] = inputs[ix];
            System.out.print(inputs[ix] + " ");
            inputs[ix] = inputs[max];
            max--;
        }
        System.out.println();
        System.out.println();
        
        System.out.println("Hashing...");
        for (int i = 0; i < hashInputs.length; ++i)
            h.insert(hashInputs[i]);
        
        System.out.println("Here's your final table");
        h.showTable();
    }
    
}

