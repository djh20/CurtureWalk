package Model;

public class Vertex {
    private Node startNode;
    private Node destNode;
    private int weight;

    public Vertex(Node startNode, Node destNode, int weight) {
        this.startNode = startNode;
        this.destNode = destNode;
        this.weight = weight;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getDestNode() {
        return destNode;
    }

    public void setDestNode(Node destNode) {
        this.destNode = destNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
