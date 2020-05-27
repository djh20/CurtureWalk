package Model;

import java.util.Vector;

public class Node {
    private Place place;
    private Vector<Vertex> vertexes;

    public Node(Place place) {
        this.place = place;
        vertexes = new Vector<>();
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Vector<Vertex> getVertexes() {
        return vertexes;
    }

    public void addVertexe(Vertex vertex) {
        this.vertexes.add(vertex);
    }
}
