package eselmeci_widestPath;

/**
 * Graph class can be treated as an opaque weighted undirected singular graph with a designated start and target Node
 * It maps Strings to graph nodes, where each String must be unique
 * It implements a widest path algorithm between start and target
 *
 * The graph is disjunct at first.
 * This can cause some problems, but we work around this
 * by silently adding and removing a connection between startNode and targetNode when needed
 */
public class Graph {
    private final Node startNode, targetNode;
    private int graphSize = 2;

    /**
     * Initialize Graph with a start and a target node
     * @param startNodeName - the name of the start node
     * @param targetNodeName - the name of the target node
     */
    public Graph(String startNodeName, String targetNodeName) {
        startNode = new Node(startNodeName);
        targetNode = new Node(targetNodeName);
        startNode.addConnection(targetNode,0);
    }


    /**
     * Creates a new link in the graph, possibly creating a node corresponding with endpoint if needed.
     * @param startPoint - the name of one of the nodes related to the edge to be created. Must exist prior to function call.
     * @param endPoint - the name of the other node related to the edge to be created. May be undefined prior to function call. Cannot be the same as startPoint
     * @param weight - the weight of the edge to be created
     * @return - the current size of the graph
     */
    public Integer addNode(String startPoint, String endPoint, Integer weight) {
    }

    private Node getNode(String nodeName) {
        return null;
    }

    public void widestPath() {

    }

}
