package eselmeci_widestPath;

import java.util.Vector;

/**
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
 * Another reason to use vectors is that I don't expect this graph to be updated frequently.
 * If that's the case, we can store the nodes ordered by their keys and use binary search to retrieve them.
 * This is definitely quicker than what can be achieved by a tree
 *
 * This makes vectors the most effective solution - and I'm dying on that hill
 *
 * Due to the assig
 */

class Node {
    private String name;
    private Vector<> connections;
    Node(String name) {
        this.name = name;
    }
}
