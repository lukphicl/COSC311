/* Luke Clover
   HW 0911
   https://github.com/lukphicl/COSC311/blob/master/LinkedList.java
   Below, the code I cannibalized for my insert function
   https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/ */

package linkedlist;

public class LinkedList {
    
    node head;
    
    class node // Creates node class 
    {
        double num;
        node next;
        public node()
        {
            num = 0;
            next = null;
        }
        public node(double p) 
        {
            num = p; 
            next = null;
        }
    }
    
    public void insert (double x)
    {
        if (head == null) // Creates head node if list is empty
        {
            head = new node(x);
            return;
        } 
        
        // Traverses to end of list, adds new node
        node lastNode = head;
        while (lastNode.next != null)
            lastNode = lastNode.next;
        
        lastNode.next = new node(x);
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
        double total = 0, mean = 0;
        
        for (node p = head; p != null; p = p.next)
        {
            total += p.num;
            ++i;
        }
        mean = total/i;

        return mean;    
    }
    
    public void deleteMore(double avg)
    {
        node temp = head;
        node prev = null;
    
        if(head == null) // If list is empty 
        {
            System.out.println("Empty list!");
            return;
        }

        if (head != null && head.num > avg) // If head node needs to be deleted
        {
            head = head.next;
            temp = head;
        }

        while(temp !=null) // Every remaining occurrence to be removed
        {
            if(temp.num > avg)
                prev.next = temp.next; 
            else 
                prev = temp;
            temp = temp.next;
        }
        
    }

    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        
        double[] arr = {100.0, 10.0, 15.0, 20.0, 200.0, 30.0, 40.0, 300.0};
        
        for (int i = 0; i < arr.length; ++i) // Creates list
            myList.insert(arr[i]);
        
        System.out.print("Your initial list: ");
        myList.printList();
        System.out.println("Your average is: " + myList.findAvg());
        myList.deleteMore(myList.findAvg());
        System.out.print("Your final list: ");
        myList.printList();
    }
    
}
