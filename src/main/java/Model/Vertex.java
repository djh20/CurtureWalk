package Model;

public class Vertex {
    private Node startNode;
    private Node destNode;
    private Route route;

    public Vertex(Node startNode, Node destNode, Route route) {
        this.startNode = startNode;
        this.destNode = destNode;
        this.route = route;
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
