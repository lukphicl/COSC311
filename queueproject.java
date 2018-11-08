// Luke Clover
// pp 1016
// https://people.emich.edu/lclover/COSC311/Projects/Serverque.java

package serverque;
import java.util.Random;
import java.lang.Math;

public class Serverque {

    // Creates client class
    public class Client {
        
        // sTicks == Service Ticks, whWaited = Would have waited
        int id, sTicks, que, newQue, waitTime, whWaited;         
        boolean hasSwitched;
        Random rando = new Random();
        
	// Constructor without parameters
        public Client() {

            // Determines required service ticks                
            sTicks = (rando.nextInt(5) + 1);         
            hasSwitched = false;           
        }
        
        // Constructor with parameters
        public Client(int iD, int q, int wTime) {
            id = iD;
            sTicks = (rando.nextInt(5) + 1);
            que = q;
            hasSwitched = false;

	    // Calculates waiting time
            waitTime = (sTicks + wTime);                                     
        }

        // Displays client information
        public void display() { 
            System.out.println("ID: " + id);
            System.out.println("Required Service Ticks: " + sTicks);
            System.out.println("Current Wait Time: " + waitTime);
            System.out.println("Original Queue: " + que);
            if (hasSwitched == true) {
                System.out.println("New Queue: " + newQue);
                System.out.println("Would have waited: " + whWaited);
            }
            System.out.println("...............................");
            System.out.println();        
        }
        
    } // End Client Class
    
    Client map[] = new Client[40];
    int head, tail, num, totalsTicks, qID, numSwitches;
    
    // Constructor
    public Serverque() {              
        head = tail = num = totalsTicks = numSwitches = 0;
        for (int i = 0; i < map.length; ++i)
            map[i] = null;
    }
    
    // Constructur with ID parameter
    public Serverque(int queID) {     
        head = tail = num = totalsTicks = numSwitches = 0;
        qID = queID;
        for(int i = 0; i < map.length; ++i)
            map[i] = null;
    }
    
    // Generages # of arrivals per tick
    private static int getPoissonRandom(double mean) { 
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
    
    public int genArrivals(double mean) {
 
        int gen = getPoissonRandom(mean);
        System.out.println("Queue " + qID + " arrivals: " + gen);
        System.out.println("*****************************************");
        return gen;
    }
    
    boolean isFull() {
        return head == tail + 1;
    }
    
    boolean isEmpty() {
        return head == tail;
    }
    
    public int totalService() {
        return totalsTicks;
    }
    
    public void updateWait() {        
        if (!isEmpty()) {
            --map[head].sTicks;
            
            if (map[head].sTicks == 0) {
                System.out.println ("Client #" + map[head].id + " done. Removing now." );
                remove();
                assignIdle();
                }
            
            // Decrements wait time for clients still waiting
            for (int i = (head+1); i < tail; ++i)
                --map[i].waitTime;
            --totalsTicks;
        }
    }
       
    public void insert(int iD) {
        if (!isFull()) {
            map[tail] = new Client(iD, qID, totalsTicks);
            
            // First arrival to an idle server
            if (tail == head) 
                System.out.println("Client #" + iD + " moved to server.");
            totalsTicks += map[tail].sTicks;
            tail = ((tail + 1) % map.length);
            ++num;
        } else {
            System.out.println("Overflow!!!");
        }
    }
    
    public void remove() {
        if (!isEmpty()){
            map[head] = null;
            head = ((head+1)%map.length);
            --num;
        } else
            System.out.println("Underflow!");   
    }
    
    public void assignIdle() {
        if (!isEmpty()) {
            System.out.println("Client #" + map[head].id + " moved to idle server.");
        }
    }
    
    // Switches clients from Queue 1 to Queue 2
    public void switchInsert(Client x) { 
        if (!isFull()) {           
            
            if (isEmpty()) {
                System.out.println("Moving " + x.id + " to idle server");
            }
            // Copies data from client over to new Queue
            map[tail] = x;
            map[tail].newQue = 2;
            map[tail].whWaited = x.waitTime;
            map[tail].waitTime = (x.sTicks+totalsTicks);
            totalsTicks += map[tail].sTicks;
            map[tail].hasSwitched = true;
            ++num;
            tail = ((tail+1)%map.length);
        }
    }
    
    // Returns number of switches
    public int numSwitches() {
        return numSwitches;
    }
    
    public void switchQ(Serverque x) {
        
        if (num > 1) {             
            int last = (tail-1);
            int waiting = (num-1);
            Random switchRan = new Random();
            
            // Generates random number of switches from end of queue, if queue isn't empty
            int switches = (switchRan.nextInt(waiting) + 1); 
            
            System.out.println(switches + " Clients switching this tick");
            for (int i = 0; i < switches; ++i) {
                System.out.println("Client #" + map[last].id + " switching queues now...");
                x.switchInsert(map[last]);
                
                // Removes client data from queue 1 and updates
                totalsTicks -= map[last].sTicks;
                map[last] = null;
                tail = ((tail-1) % map.length);
                --num;
                --last;
                ++numSwitches;
            }
            
        } else {
            System.out.println("No switches this tick.");
        }
    }
    
    // Server function - Client at head receives service
    // When service is done, client is removed, and next client
    // is assigned to server
    public void service() {
        
        System.out.println("SERVER #" + qID);
        System.out.println("~~~~~~~~~~~~~");
        if (isEmpty()) {
            System.out.println("Server idle now");           
        } else {
            System.out.println("Server busy...");
            
            // Client is removed if it starts tick with 0 sTicks
            if (map[head].sTicks == 0) {   
                System.out.println ("Client #" + map[head].id + " done. Removing now." );
                remove();
                assignIdle();
            } else {          
            updateWait();
            }
        }
    }  
    
    public int size() {
        return num;
    }
    
    // Display clients of queue 
    public void showQue() {       
        
        // For empty queue
        if (isEmpty()) {
            System.out.println("Empty Queue!");
        } else {

            // Displays client currently being serviced           
            System.out.println("Currently being served");
            System.out.println("----------------------");
            System.out.println("Client #" + map[head].id);
            System.out.println("Remaining service ticks: " + map[head].sTicks);
            if (map[head].hasSwitched == true)
                System.out.println("Would have waited " + map[head].whWaited + " ticks");
            System.out.println();                   
            
            if (num == 1) {
                System.out.println("No clients waiting currently");
            } else {
                int frontQ = (head + 1); // Front position of queue
                
                // Shows clients still waiting in queue
                System.out.println((num-1) + " client(s) currently waiting in queue");
                for (int i = frontQ; i < tail; ++i)
                    map[i].display();
                }
            }
    }
    
    public static void main(String[] args) {
        Serverque q1 = new Serverque(1), q2 = new Serverque(2);
        int q1id = 100, q2id = 200, arrivals = 0;
        
        System.out.println("There will be about 1 customer arriving every four ticks.");
        System.out.println();
        
        // Main simulation loop
        for (int i = 0; i < 20; ++i) {
            System.out.println("TICK #" + (i+1));
            System.out.println("-----------------------------------------");
            
            // Queue Server Operations
            q1.service();
            System.out.println();

            q2.service();
            System.out.println();
            
            // Compute arrivals, add to queues            
            arrivals = q1.genArrivals(0.25);
            for (int j = 0; j < arrivals; ++j)
                q1.insert(++q1id);
            q1.showQue();           
            System.out.println("=========================================");
            System.out.println();
            
            arrivals = q2.genArrivals(0.25);
            for (int k = 0; k < arrivals; ++k)
                q2.insert(++q2id);
            q2.showQue();            
            System.out.println("=========================================");
            System.out.println();           
            
            // Allow any possible switches
            q1.switchQ(q2);
            System.out.println("Number of switches so far: " + q1.numSwitches());
            System.out.println();            
            
        } 
        
    }
    
}