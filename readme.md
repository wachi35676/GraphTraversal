# Java Graph Algorithms Project

## Introduction

This project is a Java-based implementation that focuses on various graph algorithms, including traversal methods, shortest path calculations, and the construction of minimum spanning trees. It utilizes custom data structures for representing graphs, nodes, and edges, and offers file handling capabilities for input and output operations. The algorithms implemented include Breadth-First Search (BFS), Depth-First Search (DFS), Dijkstra's, Bellman-Ford, Prim's, and Kruskal's algorithm, as well as methods for detecting cycles, computing the graph's diameter, and calculating average node degrees.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- An IDE that supports Java (e.g., IntelliJ IDEA, Eclipse, or NetBeans)

### Setup and Installation

1. Clone the repository to your local machine or download the source code.
2. Open the project in your preferred Java IDE.
3. Ensure the JDK is correctly set up in your IDE to match the JDK version you have installed.

### Running the Project

To run the project:

1. Navigate to the `Main` class in your IDE.
2. Run the `Main` class to execute the program.

You can also compile and run the project from the command line:

```shell
javac Main.java
java Main
```

## Project Structure

The project is organized into the following main components:

- **Graph Data Structures:** Contains the classes `Node`, `Edge`, and `Graph` for representing graphs.
- **Graph Algorithms:** Includes implementations of various graph algorithms:
    - `Traversal.java` for BFS and DFS.
    - `ShortestPath.java` for Dijkstra's and Bellman-Ford algorithms.
    - `MinimumSpanningTree.java` for Prim's and Kruskal's algorithms.
- **File Handling:** `FileHandling.java` class for reading graph data from files and writing algorithm outputs to files.
- **Algorithm Execution and Utilities:** Classes like `Traversal`, `ShortestPath`, and `MinimumSpanningTree` for executing specific graph algorithms and analyzing graph properties.

## Usage

### Creating a Graph

To create a graph and add nodes and edges:

```java
Graph graph = new Graph();
Node node1 = new Node(1);
Node node2 = new Node(2);
graph.addNode(node1);
graph.addNode(node2);
Edge edge = new Edge(node1, node2, 10); // Edge from node1 to node2 with weight 10
graph.addEdge(edge);
```

### Running Algorithms

To run an algorithm, such as BFS:

```java
Traversal traversal = new Traversal();
traversal.BFS(graph, 1); // Performs BFS starting from node with ID 1
```

### File Handling

To read a graph from a file and write output:

```java
FileHandling fileHandling = new FileHandling();
Graph graph = fileHandling.getGraphFromFile("path/to/graph.csv");
```

## Contributing

Contributions to the project are welcome. Please follow the standard fork-pull request workflow to submit your contributions.
