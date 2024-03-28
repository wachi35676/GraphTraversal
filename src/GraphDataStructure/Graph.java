package GraphDataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    private LinkedList<Node> nodes;
    private LinkedList<Edge> edges;

    public Graph() {
        nodes = new LinkedList<Node>();
        edges = new LinkedList<Edge>();
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(LinkedList<Edge> edges) {
        this.edges = edges;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edge.getSource().addEdge(edge);
        edge.getDestination().addIncomingEdge(edge);
        edges.add(edge);
    }

    public Node getNode(int id) {
        for (Node node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    public Edge getEdge(int sourceId, int destinationId) {
        for (Edge edge : edges) {
            if (edge.getSource().getId() == sourceId && edge.getDestination().getId() == destinationId) {
                return edge;
            }
        }
        return null;
    }

    public boolean containsNode(int id) {
        for (Node node : nodes) {
            if (node.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean containsEdge(int id) {
        for (Edge edge : edges) {
            if (edge.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public String toString() {
        String graphString = "";
        for (Node node : nodes) {
            graphString += node + " " + node.getEdges() + "\n";
        }
        return graphString;
    }
}