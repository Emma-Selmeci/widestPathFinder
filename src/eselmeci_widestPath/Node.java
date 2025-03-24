package eselmeci_widestPath;

import eselmeci_collectionExtension.OrderedStringVector;

import java.util.Vector;

/**
 * This class should ideally be the inner class of Graph, but I've separated it for readability
 *
 * So here's the big question - how do we store the connections?
 *
 * I don't think we have enough information to decide the best approach.
 * What will this data structure be used for? What are our constraints? (speed/memory)
 * What will be the average number of connections per nodes?
 *
 * Given that we have key-value pairs, a treemap might sound like a good solution,
 * but if we imagine a graph with many nodes and few connections per nodes, we face a problem.
 * Most of the running time will be spent caching from the core memory due to the tree structure.
 * A tree would be ideal for a graph with many connections for each node,
 * but I imagine a situation where most nodes would have <5 connections.
 *
 * This makes vectors the most effective solution - and I'm dying on that hill
 *
 * And just to show that I'm not doing this because I'm lazy, I'm implementing a binary search algorithm,
 * which is something I haven't done before.
 */

class Node {
    private String name;
    //Setting these vectors to initialCapacity = 2 as they'd default to 10
    private OrderedStringVector names = new OrderedStringVector(2,2);
    private Vector<Node> connections = new Vector<>(2,2);
    private Vector<Integer> weights = new Vector<>(2,2);
    Node(String name) {
        this.name = name;
    }

    void addConnection(Node other, int weight) {
        receiveConnection(other,weight);
        other.receiveConnection(this,weight);
    }

    void receiveConnection(Node other, int weight) {
        boolean overWrite = names.add(other.name); //True if name was already a connection (overwriting weight)
        int index = names.find(other.name); //Calling the cached data
        if(overWrite) {
            weights.setElementAt(weight,index);
        } else {
            connections.insertElementAt(other,index);
            weights.insertElementAt(weight,index);
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing Node");
        Node n = new Node("A");

        System.out.println("###Testing receiveConnection###");

        System.out.println("Adding connection");
        Node b = new Node("B");
        n.receiveConnection(b,1);
        if(n.names.find("B") == 0 && n.connections.elementAt(0) == b && n.weights.elementAt(0) == 1
                && n.connections.size() == 1 && n.weights.size() == 1)
            System.out.println("Test passed");
        else System.out.println("Test failed");

        System.out.println("Overwriting connection");
        n.receiveConnection(b,2);
        if(n.names.find("B") == 0 && n.connections.elementAt(0) == b && n.weights.elementAt(0) == 2
                && n.connections.size() == 1 && n.weights.size() == 1)
            System.out.println("Test passed");
        else System.out.println("Test failed");

        System.out.println("Adding second connection");
        Node a = new Node("A");
        n.receiveConnection(a,1);
        if(n.names.find("B") == 1 && n.connections.elementAt(1) == b && n.weights.elementAt(1) == 2
                && n.names.find("A") == 0 && n.connections.elementAt(0) == a && n.weights.elementAt(0) == 1
                && n.connections.size() == 2 && n.weights.size() == 2)
            System.out.println("Test passed");
        else System.out.println("Test failed");

        System.out.println("Adding third connection");
        Node c = new Node("C");
        n.receiveConnection(c,3);
        if(n.names.find("B") == 1 && n.connections.elementAt(1) == b && n.weights.elementAt(1) == 2
                && n.names.find("A") == 0 && n.connections.elementAt(0) == a && n.weights.elementAt(0) == 1
                && n.names.find("C") == 2 && n.connections.elementAt(2) == a && n.weights.elementAt(2) == 3
                && n.connections.size() == 3 && n.weights.size() == 3)
            System.out.println("Test passed");
        else System.out.println("Test failed");

    }

}