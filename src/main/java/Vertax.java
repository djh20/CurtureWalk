import java.util.Comparator;
import java.util.Vector;

public class Vertax implements Comparable<Vertax> {
    Edge start;
    Edge target;
    int distance;
    Vector<Point> points;

    public Vertax(Edge start, Edge target, int distance, Vector<Point> points) {
        this.start = start;
        this.target = target;
        this.distance = distance;
        this.points = points;
    }

    @Override
    public int compareTo(Vertax vertax) {
        return this.distance > vertax.distance ? 1 : -1;
    }
}
