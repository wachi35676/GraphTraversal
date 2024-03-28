package GraphDataStructure;

public class Edge implements Comparable<Edge> {
    private Node source;
    private Node destination;
    private int weight;

    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public int getId() {
        return Integer.parseInt(source.getId() + "" + destination.getId());
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.getWeight();
    }

    public String toString() {
        if (weight != 0) {
            return source + " " + destination + " " + weight;
        }
        return "";
    }

}