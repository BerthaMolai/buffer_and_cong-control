/**
 * Main class - implements a routing protocol using djikstra's algorithm
 * to passively collect queing information
 */
public class myProtocol {
    
    public static void main(String[] args) {
        
        //start 'network' - create nodes x1...x5
        Router[] net = new Router[6];
        Router r0 = new Router(0, 1000, "r0");
        net[0] = r0;
        Router r1 = new Router(1, 1000, "r1");
        net[1] = r1;
        Router r2 = new Router(2, 1000, "r2");
        net[2] = r2;
        Router r3 = new Router(3, 1000, "r3");
        net[3] = r3;
        Router r4 = new Router(4, 1000, "r4");
        net[4] = r4;
        Router r5 = new Router(5, 1000, "r5");
        net[5] = r5;

        //add link weights between nodes
        r0.addLink(1, 2 );
        r0.addLink(3, 1 );
        r0.addLink(2, 5);

        //r1.addLink(id, weight);

        //create local list with link weights - or static and pass to each router
        //run dijkstras to find shortest paths
        Graph g = new Graph();
        g.addEdge(r0.name, r1.name, 2);
        g.addEdge(r0.name, r2.name, 5);
        g.addEdge(r0.name, r3.name, 1);

        g.addEdge(r1.name, r3.name, 2);
        g.addEdge(r1.name, r2.name, 3);

        g.addEdge(r2.name, r3.name , 3);
        g.addEdge(r2.name, r4.name, 1);
        g.addEdge(r2.name, r5.name, 5);

        g.addEdge(r3.name, r4.name, 1);

        g.addEdge(r4.name, r5.name , 2);

        System.out.println( g.vertexMap.size( ) + " routers/nodes connected" );
        System.out.println("Simulated network created! Current paths and their link states: ");
        
        g.dijkstra(r0.name);
        //checking paths
        g.printPath(r1.name);
        g.printPath(r2.name);
        g.printPath(r3.name);
        g.printPath(r4.name);
        g.printPath(r5.name);
        

        //create packet - use src and dst to get shortest path to print, and next hop to pkt.nextHop(current )
        Packet pkt = new Packet(r0.name, r5.name);

        //get next hop
        g.dijkstra(pkt.src); //run algorithm
        pkt.dst = g.checkPath(pkt.final_dst);//get next hop, then change dst of packet

        //single packet traversal
        while(! pkt.src.equals(pkt.final_dst)){
            //get current router queue size and enqueue size
            Router currHop;
            for(Router router: net){
                
                if(pkt.dst.equals(router.name)){
                    currHop = router;

                    pkt.qd_enqueue = currHop.qdepth();
                    System.out.println("Packet currently in buffer at " + currHop.name + ". Queue size= " + pkt.qd_enqueue);
                    //time delay
                    //update router queue sizes,get current router queue size and dequeue size
                    System.out.println("... \n");
                    pkt.qd_dequeue = currHop.qdepth();
                    System.out.println("Packet being processed at " + currHop.name + ". Queue size= " + pkt.qd_dequeue);

                    //update link if necessary
                    double qdiff = pkt.qd_dequeue - pkt.qd_enqueue;
                    if(qdiff > 0){
                        //queue is increasing, so increase link cost & update
                        System.out.println("Queue is increasing...updating link states");
                        g.changeCost(pkt.src, pkt.dst, qdiff);

                    }

                    else if( qdiff < -10){
                        //queue is decresing, so reduce link cost & update
                        System.out.println("Queue is decreasing quicker...updating link states");
                        g.changeCost(pkt.src, pkt.dst, qdiff);
                    }

                    //get next hop + print current network information
                    g.dijkstra(pkt.src);
                    String next = g.checkPath(pkt.final_dst);
                    g.printPath(pkt.dst);
                    System.out.println("next hop: " + next);
                    pkt.nextHop(pkt.dst, next);
                }
            } 
            
        }

        System.out.println("Packet sent successfully! Paths and their link states: ");
        
        g.dijkstra(r0.name);
        //checking paths
        g.printPath(r1.name);
        g.printPath(r2.name);
        g.printPath(r3.name);
        g.printPath(r4.name);
        g.printPath(r5.name);

        // //updateLink
        // g.changeCost(r4.name, r5.name, 10);
        // g.printPath(r5.name);
        // g.dijkstra(r0.name);
        // g.printPath(r5.name);
        
        // g.dijkstra(r5.name);
        // System.out.println(" from graph: ");
        // g.checkPath(r5.name);
        // g.printPath(r5.name);
    }
}
