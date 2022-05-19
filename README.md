# EEE4121F Module B Project

## using buffer parameters to aid in congestion

The main file, myProtocol, traverses a single packet through a default simulated network. As it traverses from each router, the packet picks up the buffer queue sizes and this is used to update the link states which in turn updates the routing tables if necessary.
myProtocol uses Packet class to create a packet object, Router class to create a node/router and store it's information and a Graph class to perform the routing (performs control plane actions).

### to run the program

'make' to compile
'make run' to run
