package Model;

import java.util.Vector;

public class PublicTransportPath extends Path {
    private String publicTransport;

    public PublicTransportPath(Vector<Point> points, int distance, int time, int trafficType, Place startPlace, Place goalPlace, String distanceUnit, String publicTransport) {
        super(points, distance, time, trafficType, startPlace, goalPlace, distanceUnit);
        this.publicTransport = publicTransport;
    }

    public String getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(String publicTransport) {
        this.publicTransport = publicTransport;
    }
}
