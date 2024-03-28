package GraphAlgorithms;

import FileHandling.FileHandling;
import GraphDataStructure.*;

import java.util.LinkedList;

public class ShortestPath {

    //Dijkstra's algorithm for finding distance of shortest path from source to all other nodes
    public void dijkstra(Graph graph, int sourceId) {
        long startTime = System.nanoTime();

        FileHandling fileHandling = new FileHandling();
        fileHandling.clearFile("dijkstra.txt");

        Node source = graph.getNode(sourceId);

        //mark all nodes as unvisited and set distance to infinity
        for (Node node : graph.getNodes()) {
            fileHandling.write("dijkstra.txt","Marking node " + node.getId() + " as unvisited and setting distance to infinity\n");

            node.setVistStatus(false);
            node.setDistance(2147483647);
        }

        //set distance of source to 0
        source.setDistance(0);
        fileHandling.write("dijkstra.txt","\nSetting distance of source ID:" + source.getId() + " to 0\n");

        //create a queue of nodes
        LinkedList<Node> queue = new LinkedList<Node>();

        //add source to queue
        queue.add(source);
        fileHandling.write("dijkstra.txt","Adding source ID:" + source.getId() + " to queue\n");

        //while queue is not empty
        while (queue.size() != 0) {

            //get the node with the smallest distance from the queue
            Node smallest = queue.poll();
            fileHandling.write("dijkstra.txt","Dequeing node ID:" + smallest.getId() + " from queue\n");

            //mark the node as visited
            smallest.setVistStatus(true);
            fileHandling.write("dijkstra.txt","Marking node ID:" + smallest.getId() + " as visited\n\n");

            //for each edge of the node
            for (Edge edge : smallest.getEdges()) {
                fileHandling.write("dijkstra.txt","\nChecking edge ID:" + edge.getId() + " of node ID:" + smallest.getId() + "\n");

                //get the destination node
                Node neighbor = edge.getDestination();

                //if the node is not visited
                if (neighbor.getVistStatus() == false) {
                    fileHandling.write("dijkstra.txt","Node ID:" + neighbor.getId() + " is not visited\n");

                    //calculate the distance of the neighbor
                    int distance = smallest.getDistance() + edge.getWeight();

                    //if the distance is smaller than the current distance
                    fileHandling.write("dijkstra.txt","Distance of node ID:" + neighbor.getId() + " is " + neighbor.getDistance() + "\n");
                    fileHandling.write("dijkstra.txt","Distance of node ID:" + smallest.getId() + " + edge ID:" + edge.getId() + " is " + distance + "\n");
                    if (distance < neighbor.getDistance()) {
                        fileHandling.write("dijkstra.txt","Distance of node ID:" + neighbor.getId() + " is smaller than current distance\n");

                        //set the distance of the neighbor to the calculated distance
                        neighbor.setDistance(distance);
                        fileHandling.write("dijkstra.txt","Setting distance of node ID:" + neighbor.getId() + " to " + distance + "\n");

                        //add the neighbor to the queue
                        queue.add(neighbor);
                        fileHandling.write("dijkstra.txt","Adding node ID:" + neighbor.getId() + " to queue\n\n");
                    }
                }
            }
        }

        //print the distance of all the nodes from the source
        for (Node node : graph.getNodes()) {
            fileHandling.write("dijkstra.txt","Distance of node ID:" + node.getId() + " from source ID:" + source.getId() + " is " + node.getDistance() + "\n");
            System.out.println("Distance of node ID:" + node.getId() + " from source ID:" + source.getId() + " is " + node.getDistance());
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Dijkstra's algorithm took " + elapsedTime + " nanoseconds to execute");
        fileHandling.write("dijkstra.txt","\nDijkstra's algorithm took " + elapsedTime + " nanoseconds to execute");

    }

    //Bellman-Ford algorithm for finding distance of shortest path from source to all other nodes
    public void bellmanFord(Graph graph, int sourceId) {
        long startTime = System.nanoTime();

        FileHandling fileHandling = new FileHandling();
        fileHandling.clearFile("bellmanFord.txt");

        Node source = graph.getNode(sourceId);

        //mark all nodes as unvisited and set distance to infinity
        for (Node node : graph.getNodes()) {
            fileHandling.write("bellmanFord.txt","Marking node " + node.getId() + " as unvisited and setting distance to infinity\n");
            node.setVistStatus(false);
            node.setDistance(2147483647);
        }

        //set distance of source to 0
        source.setDistance(0);
        fileHandling.write("bellmanFord.txt","\nSetting distance of source ID:" + source.getId() + " to 0\n");

        //for each node
        for (Node node : graph.getNodes()) {
            fileHandling.write("bellmanFord.txt","\nChecking node ID:" + node.getId() + "\n");
            //for each edge
            for (Edge edge : graph.getEdges()) {

                //get the source and destination nodes
                Node sourceNode = edge.getSource();
                Node destinationNode = edge.getDestination();
                fileHandling.write("bellmanFord.txt","Checking edge from node ID:" + sourceNode.getId() + " to node ID:" + destinationNode.getId() + "\n");

                //calculate the distance of the destination node
                int distance = sourceNode.getDistance() + edge.getWeight();

                //if the distance is smaller than the current distance
                if (distance < destinationNode.getDistance()) {

                    //set the distance of the destination node to the calculated distance
                    destinationNode.setDistance(distance);
                    fileHandling.write("bellmanFord.txt","Setting distance of node ID:" + destinationNode.getId() + " to " + distance + "\n");

                }
            }
        }

        //for each edge
        for (Edge edge : graph.getEdges()) {

            //get the source and destination nodes
            Node sourceNode = edge.getSource();
            Node destinationNode = edge.getDestination();

            //calculate the distance of the destination node
            int distance = sourceNode.getDistance() + edge.getWeight();

            //if the distance is smaller than the current distance
            if (distance < destinationNode.getDistance()) {
                    //there is a negative cycle
                    fileHandling.write("bellmanFord.txt","There is a negative cycle\n");
            }
        }

        //print the distance of all the nodes from the source
        for (Node node : graph.getNodes()) {
            fileHandling.write("bellmanFord.txt","Distance of node ID:" + node.getId() + " from source ID:" + source.getId() + " is " + node.getDistance() + "\n");
            System.out.println("Distance of node ID:" + node.getId() + " from source ID:" + source.getId() + " is " + node.getDistance());
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Bellman-Ford algorithm took " + elapsedTime + " nanoseconds to execute");
        fileHandling.write("bellmanFord.txt","\nBellman-Ford algorithm took " + elapsedTime + " nanoseconds to execute");
    }

    public void diameter(Graph graph) {
        long startTime = System.nanoTime();

        FileHandling fileHandling = new FileHandling();
        fileHandling.clearFile("diameter.txt");

        int diameter = 0;
        //for each node in the graph
        for (Node node : graph.getNodes()) {
            fileHandling.write("diameter.txt","\nChecking First node ID:" + node.getId() + "\n");
            //run Dijkstra on the graph
            dijkstra(graph, node.getId());
            //for each node in the graph
            for (Node node2 : graph.getNodes()) {
                fileHandling.write("diameter.txt","Checking Second node ID:" + node2.getId() + "\n");
                //if the distance to the node is greater than the diameter
                if (node2.getDistance() > diameter) {
                    //set the diameter to the distance to the node
                    diameter = node2.getDistance();
                    fileHandling.write("diameter.txt","Setting diameter to " + diameter + "\n");
                }
            }
        }

        if(diameter == 2147483647) {
            fileHandling.write("diameter.txt","There is no diameter\n");
            System.out.println("There is no diameter");
            fileHandling.write("diameter.txt","Diameter algorithm took " + (System.nanoTime() - startTime) + " nanoseconds to execute");
            System.out.println("Diameter algorithm took " + (System.nanoTime() - startTime) + " nanoseconds to execute");
        }
        else {
            fileHandling.write("diameter.txt","The diameter is " + diameter + "\n");
            System.out.println("The diameter is " + diameter);
            fileHandling.write("diameter.txt","Diameter algorithm took " + (System.nanoTime() - startTime) + " nanoseconds to execute");
            System.out.println("Diameter algorithm took " + (System.nanoTime() - startTime) + " nanoseconds to execute");
        }
    }

    //implement Dijkstra's algorithm that makes a new graph with the shortest paths from the source node to all other nodes in the graph and returns the graph
    //all other edges in the graph that are not part of the shortest path should have a weight of 0
//    public Graph dijkstra(Graph graph, int sourceId) {
//        Graph shortestPathGraph = new Graph();
//        Node sourceNode = graph.getNode(sourceId);
//        if (sourceNode == null) {
//            return null;
//        }
//        LinkedList<Node> nodes = graph.getNodes();
//        for (Node node : nodes) {
//            Node newNode = new Node(node.getId());
//            shortestPathGraph.addNode(newNode);
//        }
//        LinkedList<Edge> edges = graph.getEdges();
//        for (Edge edge : edges) {
//            Node source = shortestPathGraph.getNode(edge.getSource().getId());
//            Node destination = shortestPathGraph.getNode(edge.getDestination().getId());
//            Edge newEdge = new Edge(source, destination, 0);
//            shortestPathGraph.addEdge(newEdge);
//        }
//        LinkedList<Node> unvisitedNodes = new LinkedList<Node>();
//        for (Node node : nodes) {
//            node.setDistance(Integer.MAX_VALUE);
//            unvisitedNodes.add(node);
//        }
//        sourceNode.setDistance(0);
//        while (!unvisitedNodes.isEmpty()) {
//            Node currentNode = getClosestNode(unvisitedNodes);
//            unvisitedNodes.remove(currentNode);
//            for (Edge edge : currentNode.getEdges()) {
//                Node destination = edge.getDestination();
//                if (unvisitedNodes.contains(destination)) {
//                    int newDistance = currentNode.getDistance() + edge.getWeight();
//                    if (newDistance < destination.getDistance()) {
//                        destination.setDistance(newDistance);
//                        Edge shortestPathEdge = shortestPathGraph.getEdge(currentNode.getId(), destination.getId());
//                        shortestPathEdge.setWeight(edge.getWeight());
//                    }
//                }
//            }
//        }
//        return shortestPathGraph;
//    }
//
//    private Node getClosestNode(LinkedList<Node> unvisitedNodes) {
//        Node closestNode = null;
//        int closestDistance = Integer.MAX_VALUE;
//        for (Node node : unvisitedNodes) {
//            int distance = node.getDistance();
//            if (distance < closestDistance) {
//                closestDistance = distance;
//                closestNode = node;
//            }
//        }
//        return closestNode;
//    }

//    public Graph dijkstra(Graph graph, int sourceId) {
//        Graph shortestPathGraph = new Graph();
//        Node sourceNode = graph.getNode(sourceId);
//
//        //add all nodes to the new graph
//        for (Node node : graph.getNodes()) {
//            shortestPathGraph.addNode(new Node(node.getId()));
//        }
//
//        //add all edges to the new graph
//        for (Edge edge : graph.getEdges()) {
//            shortestPathGraph.addEdge(new Edge(shortestPathGraph.getNode(edge.getSource().getId()), shortestPathGraph.getNode(edge.getDestination().getId()), edge.getWeight()));
//        }
//
//        //set the distance of the source node to 0
//        shortestPathGraph.getNode(sourceId).setDistance(0);
//
//        //create a list of unvisited nodes
//        LinkedList<Node> unvisitedNodes = new LinkedList<Node>();
//        for (Node node : shortestPathGraph.getNodes()) {
//            unvisitedNodes.add(node);
//        }
//
//        //while there are unvisited nodes
//        while (!unvisitedNodes.isEmpty()) {
//            //find the node with the smallest distance
//            Node smallestDistanceNode = unvisitedNodes.get(0);
//            for (Node node : unvisitedNodes) {
//                if (node.getDistance() < smallestDistanceNode.getDistance()) {
//                    smallestDistanceNode = node;
//                }
//            }
//
//            //remove the node with the smallest distance from the list of unvisited nodes
//            unvisitedNodes.remove(smallestDistanceNode);
//
//            //for each of the node's edges
//            for (Edge edge : smallestDistanceNode.getEdges()) {
//                //if the distance of the destination node is greater than the distance of the source node plus the weight of the edge
//                if (edge.getDestination().getDistance() > smallestDistanceNode.getDistance() + edge.getWeight()) {
//                    //set the distance of the destination node to the distance of the source node plus the weight of the edge
//                    edge.getDestination().setDistance(smallestDistanceNode.getDistance() + edge.getWeight());
//                }
//            }
//        }
//
//        return shortestPathGraph;
//    }
//
//    //find the shortest path from the source node to the destination node in the graph and return the path as a list of nodes
//    public LinkedList<Node> findShortestPath(Graph graph, int sourceId, int destinationId) {
//        //create a new graph with the shortest paths from the source node to all other nodes in the graph
//        Graph shortestPathGraph = dijkstra(graph, sourceId);
//
//        //create a list of nodes that will contain the shortest path
//        LinkedList<Node> shortestPath = new LinkedList<Node>();
//
//        //add the destination node to the list of nodes that will contain the shortest path
//        shortestPath.add(shortestPathGraph.getNode(destinationId));
//
//        //while the destination node is not the source node
//        while (shortestPath.getLast().getId() != sourceId) {
//            //find the node that has an edge to the destination node with the smallest weight
//            Node smallestWeightNode = shortestPath.getLast().getIncomingEdges().get(0).getSource();
//            for (Edge edge : shortestPath.getLast().getIncomingEdges()) {
//                if (edge.getWeight() < shortestPathGraph.getEdge(smallestWeightNode.getId(), shortestPath.getLast().getId()).getWeight()) {
//                    smallestWeightNode = edge.getSource();
//                }
//            }
//
//            //add the node that has an edge to the destination node with the smallest weight to the list of nodes that will contain the shortest path
//            shortestPath.add(smallestWeightNode);
//        }
//
//        //reverse the list of nodes that will contain the shortest path
//        LinkedList<Node> reversedShortestPath = new LinkedList<Node>();
//        for (int i = shortestPath.size() - 1; i >= 0; i--) {
//            reversedShortestPath.add(shortestPath.get(i));
//        }
//
//        return reversedShortestPath;
//    }

    //find the shortest path from the source node to the destination node and return the path as a list of nodes
    //the path is found by starting at the destination node and following the incoming edges to the source node
//    public LinkedList<Node> findShortestPath(Graph shortestPathGraph, int sourceId, int destinationId) {
//        LinkedList<Node> shortestPath = new LinkedList<Node>();
//        Node destinationNode = shortestPathGraph.getNode(destinationId);
//        Node currentNode = destinationNode;
//        while (currentNode.getId() != sourceId) {
//            shortestPath.addFirst(currentNode);
//            currentNode = currentNode.getIncomingEdges().get(0).getSource();
//        }
//        shortestPath.addFirst(currentNode);
//        return shortestPath;
//    }

//implement Bellman-Ford algorithm that makes a new graph with the shortest paths from the source node to all other nodes in the graph and returns the graph


//    public Graph dijkstra(Graph graph, int sourceId) {
//        Graph shortestPathGraph = new Graph();
//        Node sourceNode = graph.getNode(sourceId);
//        if (sourceNode == null) {
//            return null;
//        }
//        LinkedList<Node> queue = new LinkedList<Node>();
//        for (Node node : graph.getNodes()) {
//            node.setDistance(Integer.MAX_VALUE);
//            queue.add(node);
//        }
//        sourceNode.setDistance(0);
//        while (!queue.isEmpty()) {
//            Node currentNode = queue.remove();
//            for (Edge edge : currentNode.getEdges()) {
//                Node destinationNode = edge.getDestination();
//                int newDistance = currentNode.getDistance() + edge.getWeight();
//                if (newDistance < destinationNode.getDistance()) {
//                    destinationNode.setDistance(newDistance);
//                    shortestPathGraph.addEdge(edge);
//                }
//            }
//        }
//        return shortestPathGraph;
//    }
//
//    //implement Dijkstra's algorithm that returns the shortest path from the source node to the destination node as a string
//    public String dijkstra(Graph graph, int sourceId, int destinationId) {
//        Graph shortestPathGraph = dijkstra(graph, sourceId);
//        Node destinationNode = shortestPathGraph.getNode(destinationId);
//        if (destinationNode == null) {
//            return null;
//        }
//        String path = "";
//        while (destinationNode != null) {
//            path = destinationNode + " " + path;
//            for (Edge edge : shortestPathGraph.getEdges()) {
//                if (edge.getDestination() == destinationNode) {
//                    destinationNode = edge.getSource();
//                    break;
//                }
//            }
//        }
//        return path;
//    }

}
