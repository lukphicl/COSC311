// Luke Clover
// HW 9/11/2018
// https://github.com/lukphicl/COSC311/blob/master/hw-0906.java

package linkedlist;


public class LinkedList {
    
    node head;
    
    class node // Creates node class 
    {
        double num;
        node next;
        node (double p) {num = p; next = null;}
    }
    
    public void insert (double x)
    {
        node newNode = new node(x);
        if (head == null) // Creates head node if list is empty
        {
            head = new node(x);
            return;
        }
        
        // Unsorted insert, new node will always be added to end
        newNode.next = null; 
        
        // Traverses to end of list, adds new node
        node lastNode = head;
        while (lastNode.next != null)
            lastNode = lastNode.next;
        
        lastNode.next = newNode;
        return;
    }   
    
    public void printList()
    {
        node p = head;
        while (p != null)
        {
            System.out.print(p.num + " ");
            p = p.next;
        }
        System.out.println();
    }
    
    public double findAvg()
    {
        int i = 0;
        node p = head;
        double total = 0, mean = 0;
        
        while (p != null)
        {   
            total = total + p.num;
            ++i;
            p = p.next;
        }
        mean = total/i;

        return mean;    
    }
    
    public void deleteLess(double avg)
    {
        node temp = head;
        
        if (head == null) // If list is empty
        {
            System.out.println("Empty List!");
            return;
        }
        
        if (head != null && head.num <= avg) // If head node is to be removed
            head = head.next;

        // Parses through list, removes every occurrence
        while (temp != null && temp.next != null)
        {
            if (temp.next.num <= avg)
            {
                System.out.println("Removing " + temp.next.num);
                temp.next = temp.next.next;
            } else
            {
                temp = temp.next;
            }
        }
        
    }

    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        
        double[] arr = {100.0, 10.0, 15.0, 20.0, 200.0, 30.0, 40.0, 300.0};
        
        for (int i = 0; i < arr.length; ++i) // Creates list
            myList.insert(arr[i]);
        
        System.out.print("Initial list: ");
        myList.printList();
        System.out.println("Your average is: " + myList.findAvg());
        myList.deleteLess(myList.findAvg());
        System.out.print("Your final list: ");
        myList.printList();

    }
    
}
