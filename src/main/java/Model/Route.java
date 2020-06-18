package Model;

import java.io.Serializable;
import java.util.Vector;

public class Route implements Serializable {
    private static final long serialVersionUID = -3140220789751481344L;
    private Vector<Path> pathes;
    private int distance;

    public Route(Vector<Path> pathes, int distance) {
        this.pathes = pathes;
        this.distance = distance;
    }

    public Route() {
        this.pathes = new Vector<>();
        this.distance = 0;
    }
    public void addPath(Path path){
        pathes.add(path);
    }
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vector<Path> getPathes() {
        return pathes;
    }

    public void setPathes(Vector<Path> pathes) {
        this.pathes = pathes;
    }
}
