package GraphAlgorithms;

import java.util.LinkedList;

import FileHandling.FileHandling;
import GraphDataStructure.*;

public class Traversal {

	public void BFS(Graph graph, int sourceId) {
		FileHandling fileHandling=new FileHandling();
		fileHandling.clearFile("bfsTraversal.txt");
		fileHandling.clearFile("bfsTraversalResult.txt");
		Node source = graph.getNode(sourceId);
		for (Node node : graph.getNodes()) {
			node.setVistStatus(false);
		}
		LinkedList<Node> queue = new LinkedList<Node>();
		fileHandling.write("bfsTraversal.txt","Starting with source node: "+source.getId()+"\n");
		source.setVistStatus(true);
		queue.add(graph.getNode(sourceId));
		while (queue.size() != 0) {
			fileHandling.write("bfsTraversal.txt","Dequeing vertex: "+source.getId()+"\n");		
			source = queue.poll();
			System.out.print(source.getId() + " ");
			fileHandling.write("bfsTraversalResult.txt",source.getId()+" ");
			for (Edge edge : source.getEdges()) {
				Node neighbor = edge.getDestination();
				if (neighbor.getVistStatus()==false) {
					neighbor.setVistStatus(true); 
					fileHandling.write("bfsTraversal.txt","Enqueing vertex: "+neighbor.getId()+"\n");
					queue.add(neighbor);
				}
			}
		}
	}

	public void DFS(Graph graph, int sourceId) {
		FileHandling fileHandling=new FileHandling();
		fileHandling.clearFile("dfsTraversal.txt");
		fileHandling.clearFile("dfsTraversalResult.txt");
		Node source = graph.getNode(sourceId);
		for (Node node : graph.getNodes()) {
			node.setVistStatus(false);
		}
		for (Node node : graph.getNodes()) {
			if (node.getVistStatus() == false) {
				fileHandling.write("dfsTraversal.txt","Starting with connected node: "+node.getId()+"\n");
				DFS_VIST(node);
			}
		}
	}

	void DFS_VIST(Node node) {
		FileHandling fileHandling=new FileHandling();
		node.setVistStatus(true);
		System.out.print(node.getId() + " ");
		fileHandling.write("dfsTraversalResult.txt",node.getId()+" ");
		fileHandling.write("dfsTraversal.txt","Exploring Vertex: " + node.getId()+"\n");
		for (Edge edge : node.getEdges()) {
			Node neighbor = edge.getDestination();
			if (neighbor.getVistStatus()==false){
				fileHandling.write("dfsTraversal.txt","Visiting adjacent vertex: "+ neighbor.getId()+ " to vertex: "+node.getId()+"\n");
				DFS_VIST(neighbor);
			}
		}
	}

	public boolean IsCyclic(Graph graph, int sourceId) {
		FileHandling fileHandling = new FileHandling();
		fileHandling.clearFile("cycleDetection.txt");
		fileHandling.clearFile("cycleDetectionResult.txt");
		Node source = graph.getNode(sourceId);
		for (Node node : graph.getNodes()) {
			node.setVistStatus(false);
		}
		LinkedList<Node> queue = new LinkedList<Node>();
		fileHandling.write("cycleDetection.txt", "Starting with source node: " + source.getId() + "\n");
		source.setVistStatus(true);
		queue.add(graph.getNode(sourceId));
		while (queue.size() != 0) {
			fileHandling.write("cycleDetection.txt", "Dequeing vertex: " + source.getId() + "\n");
			source = queue.poll();
			for (Edge edge : source.getEdges()) {
				Node neighbor = edge.getDestination();
				if (neighbor.getVistStatus() == false) {
					neighbor.setVistStatus(true);
					fileHandling.write("cycleDetection.txt", "Enqueing vertex: " + neighbor.getId() + "\n");
					queue.add(neighbor);
				} else {
					fileHandling.write("cycleDetection.txt", "Cycle detected between vertex: " + neighbor.getId()
							+ " and vertex: " + source.getId() + "\n");
					fileHandling.write("cycleDetectionResult.txt","There is a cycle" + "\n");
					return true;
				}
			}
		}
		fileHandling.write("cycleDetectionResult.txt","There is no cycle" + "\n");
		return false;

	}
}

