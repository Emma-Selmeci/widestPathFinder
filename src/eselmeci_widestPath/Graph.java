package eselmeci_widestPath;

/**
 * Graph class can be treated as an opaque weighted undirected multi-graph with a designated start and target Node
 * It maps Strings to graph nodes, where each String must be unique
 * It implements a widest path algorithm between start and target
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
    }


    /**
     * Creates a new link in the graph, possibly creating a node corresponding with endpoint if needed.
     * @param startPoint - the name of one of the nodes related to the edge to be created. Must exist prior to function call
     * @param endPoint - the name of the other node related to the edge to be created. May be undefined prior to function call. May be same as startPoint
     * @param weight - the weight of the edge to be created
     * @return - the current size of the graph
     */
    public Integer addNode(String startPoint, String endPoint, Integer weight) {
        Node start = getNode(startPoint);
        if(start == null) throw new IllegalArgumentException("Node " + startPoint + " not in graph instance");
        Node end = getNode(startPoint);

        if(start != end) ++graphSize;
        return graphSize;
    }

    private Node getNode(String nodeName) {

    }

    public void widestPath() {

    }

}
