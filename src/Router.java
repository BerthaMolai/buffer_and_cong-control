import java.util.Random;

public class Router{
    /**
     * Creates Router objects to act as nodes to keep
     * track of router buffer information at any given moment
     * nodes/router (router id/ip(set and not changed), queue size, buffersize, links(i, weight, speed) )
     */

     private String ip;
     int bsize;
     int queueSize;
     String name;
     int[][] links = new int[4][2];
     int linknum;

     //constructor - to create router object: initialise ip addr
     public Router(int num, int B, String nm){
        ip = "10.0.0." + Integer.toString(num);
        bsize = B;
        name = nm;
        linknum = 0;
        
        //randomly assign queue size when initialising
        Random rand = new Random();
        queueSize = rand.nextInt(bsize);
     }

     //set queuesize - increase and decrease should be realistic
     private void setQueueSize(){
         //randomly assign queue size - might depend on link speed
         //Random rand = new Random();
         

         if(queueSize*2>bsize){
            int min = queueSize - (queueSize/4); //smaller gap if buffer is almost full
            queueSize = (int)Math.floor(Math.random()*(bsize-min+1)+min);
            //queueSize = rand.nextInt(bsize);
         }
         else{
            int min = queueSize/2;
            int max = queueSize*2;
            queueSize = (int)Math.floor(Math.random()*(max-min+1)+min);
         }
         
     }

     //returns current depth of queue
     public int qdepth(){
         //call setQueueSize? maybe internal function that decreases/increases data after a defined period of time
         setQueueSize();
         return queueSize;
     }

     //getters
     public String getIP(){
         return ip;
     }

     //adds link to router at specific interface(necessay?)
     //might not be a good idea to keep link weights as part of object. Need to update each time
     //simply return list of links each time we updateLinks
     public void addLink(int id,int weight){
         if (linknum < 5){
            links[linknum][0] = id;
            links[linknum][1] = weight;
            linknum++;
         }

         else{
             System.out.println("link not created");
         }
         
     }

    /** update link weight - to use for recalculation of routing table
     *  called if some condition is met
     * uses - tbuff and queue sizes on enqueue and dequeue
     */
     public void updateLinks(int tbuff, int qdiff){

        //penalises longer tbuff and queue increases
/*y = 0.8*(tbuff + 1/qdiff**2)
        link_num[][] = y*linknum[][]; */

     }

}