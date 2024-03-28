package FileHandling;

import GraphDataStructure.*;

import java.io.*;
import java.util.Scanner;

public class FileHandling {
    // This method reads the file and creates a graph
    // the files is csv file with the following format:
    // source,destination,weight
    public Graph getGraphFromFile(String fileName) {
        Graph graph = new Graph();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split(",");
                int sourceId = Integer.parseInt(lineArray[0]);
                int destinationId = Integer.parseInt(lineArray[1]);
                int weight = Integer.parseInt(lineArray[2]);
                Node sourceNode = graph.getNode(sourceId);
                if (sourceNode == null) {
                    sourceNode = new Node(sourceId);
                    graph.addNode(sourceNode);
                }
                Node destinationNode = graph.getNode(destinationId);
                if (destinationNode == null) {
                    destinationNode = new Node(destinationId);
                    graph.addNode(destinationNode);
                }
                Edge edge = new Edge(sourceNode, destinationNode, weight);
                graph.addEdge(edge);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }

    //this method creates a file
    public void createFile(String fileName){
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //this methods appends a line to a file
    public void write(String fileName, String message){
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(message);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method clears the file
    public void clearFile(String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
