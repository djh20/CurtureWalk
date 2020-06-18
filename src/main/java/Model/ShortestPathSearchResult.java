package Model;

import java.util.Vector;

public class ShortestPathSearchResult {
    private Vector<Vertex> orderedNode;
    private int totalDistance;

    public ShortestPathSearchResult(Vector<Vertex> orderedNode, int totalDistance) {
        this.orderedNode = orderedNode;
        this.totalDistance = totalDistance;
    }

    public Vector<Vertex> getOrderedNode() {
        return orderedNode;
    }

    public void setOrderedNode(Vector<Vertex> orderedNode) {
        this.orderedNode = orderedNode;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }
}
