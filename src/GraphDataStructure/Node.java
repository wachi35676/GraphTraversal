package GraphDataStructure;

import java.util.LinkedList;

public class Node {
    private int id;
    private LinkedList<Edge> edges;
    private LinkedList<Edge> incomingEdges;
    private int distance;
    private boolean visited;

    public Node(int id) {
        this.id = id;
        edges = new LinkedList<Edge>();
        incomingEdges = new LinkedList<Edge>();
        visited = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public LinkedList<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public void setEdges(LinkedList<Edge> edges) {
        this.edges = edges;
    }

    public void setIncomingEdges(LinkedList<Edge> incomingEdges) {
        this.incomingEdges = incomingEdges;
    }

    public int getDistance() {
        return distance;
    }

    public boolean getVistStatus() {
        return visited;
    }
    
    public void setVistStatus(boolean visited) {
    	this.visited=visited;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addIncomingEdge(Edge edge) {
        incomingEdges.add(edge);
    }
    
    public String toString() {
        return "" + id;
    }
}
