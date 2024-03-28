package GraphAlgorithms;

import FileHandling.FileHandling;
import GraphDataStructure.*;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumSpanningTree {
    FileHandling fileHandling = new FileHandling();

    //Function to find the degree of a node
    public int degree(Graph graph, int id) {
        //Get the number of nodes going out of the node
        int outDegree = graph.getNode(id).getEdges().size();
        fileHandling.write("averageDegree.txt", "Out Degree of Node " + id + ": " + outDegree + "\n");
        //Get the number of nodes going into the node
        int inDegree = graph.getNode(id).getIncomingEdges().size();
        fileHandling.write("averageDegree.txt", "In Degree of Node " + id + ": " + inDegree + "\n");
        fileHandling.write("averageDegree.txt", "Degree of Node " + id + ": " + inDegree + outDegree + "\n");
        return outDegree + inDegree;
    }

    public int averageDegreeOfAGraph(Graph graph) {
        fileHandling.clearFile("averageDegree.txt");
        //The average degree of a graph is the sum of the degrees of all the nodes in the graph divided by the number of nodes in the graph
        int sumOfDegrees = 0;
        int numberOfNodes = graph.getNodes().size();
        int averageDegree = 0;
        //Find the sum of the degrees of all the nodes in the graph
        for (Node node : graph.getNodes()) {
            sumOfDegrees = sumOfDegrees + degree(graph, node.getId());
            fileHandling.write("averageDegree.txt","\n");
        }
        fileHandling.write("averageDegree.txt", "============ Finding the Average Degree ============"+ "\n");
        fileHandling.write("averageDegree.txt", "Sum of Degrees: " + sumOfDegrees + "\n");
        fileHandling.write("averageDegree.txt", "Number of Nodes: " + numberOfNodes + "\n");
        //Return the average degree of the graph
        averageDegree = sumOfDegrees / numberOfNodes;
        fileHandling.write("averageDegree.txt", "Average Degree: " + averageDegree + "\n");


        return averageDegree;

    }

    public Graph primsAlgorithm(Graph graph, int sourceId) {

        Graph minimumSpanningTree = new Graph();
        Node source = graph.getNode(sourceId);

        // add the source node to the minimum spanning tree
        minimumSpanningTree.addNode(source);
        fileHandling.write("primsAlgorithm.txt", "============ Source Node ============" + "\n");
        fileHandling.write("primsAlgorithm.txt", "Source node id: " + source.getId() + "\n");

        //queue that will contain the edges of the minimum spanning tree
        Queue<Edge> queue = new PriorityQueue<Edge>();

        // add the edges of the source node to the queue
        fileHandling.write("primsAlgorithm.txt", "============ Edges of Source Node ============" + "\n");
        queue.addAll(source.getEdges());
        fileHandling.write("primsAlgorithm.txt", "All edges added to queue" + "\n");

        fileHandling.write("primsAlgorithm.txt", "============ Edges Added to Minimum Spanning Tree ============" + "\n");
        // while the queue is not empty
        while (!queue.isEmpty()) {
            // get the edge with the smallest weight
            Edge edge = queue.poll();

            // if the destination node of the edge is not in the minimum spanning tree
            if (!minimumSpanningTree.containsNode(edge.getDestination().getId())) {
                // add the destination node to the minimum spanning tree
                minimumSpanningTree.addNode(edge.getDestination());

                // add the edge to the minimum spanning tree
                minimumSpanningTree.addEdge(edge);
                fileHandling.write("primsAlgorithm.txt", "Edge Added: " + edge.getWeight() + "\n");
                // add the edges of the destination node to the queue
                queue.addAll(edge.getDestination().getEdges());
                fileHandling.write("primsAlgorithm.txt", "============ Edges of New Node Added ============" + "\n");
                fileHandling.write("primsAlgorithm.txt", "edge weight: " + edge.getWeight() + "\n");
            }
        }

        return minimumSpanningTree;
    }

    public Graph kruskalsAlgorithm(Graph graph) {

        Graph MST = new Graph(); //MST stands for Minimum Spanning Tree
        //Sort the edges of the graph in ascending order using a priority queue
        fileHandling.write("kruskalsAlgorithm.txt", "============ Sorted Edges ============" + "\n");
        Queue<Edge> queue = new PriorityQueue<Edge>();
        queue.addAll(graph.getEdges());
        fileHandling.write("kruskalsAlgorithm.txt", "All edges have been added and sorted" + "\n");
        fileHandling.write("kruskalsAlgorithm.txt", "============ Adding Edges ============" + "\n");
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            //Checking that there isn't a cycle in the graph
            if (!MST.containsNode(edge.getDestination().getId())) {
                //If the destination node of the edge is not in the MST
                //Add the destination node to the MST
                //Add the edge to the MST
                //Add the edges of the destination node to the queue
                MST.addNode(edge.getDestination());
                MST.addEdge(edge);
                fileHandling.write("kruskalsAlgorithm.txt", "Edge added "+ "\n");
                fileHandling.write("kruskalsAlgorithm.txt", "source node: " + edge.getSource() + " destination node: " + edge.getDestination() + " edge weight: " + edge.getWeight() + "\n");
            }
        }

        return MST;

    }
}

