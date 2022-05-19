public class Packet {
    /**
     * Creates a packet for easier use and routing
     * packet(final_dst, init_src, dst, src, qd_enqueue, qd_dequeue, t_buff)
     */

     String final_dst,init_src, dst, src;
     int qd_enqueue, qd_dequeue; // depth of queue on entering and leaving queue respectively
     int t_buff;//time spent in buffer

     public Packet(String s, String d){
        final_dst = d;
        init_src = src = s;
     }

    //change src and dst 'ip' info for 
     public void nextHop(String prevhop,String nexthop){
        src = prevhop;
        dst = nexthop;
     }
     
     
}
