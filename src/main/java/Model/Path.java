package Model;

import java.io.Serializable;
import java.util.Vector;

public class Path implements Serializable {
    private static final long serialVersionUID = -3140220789751481348L;
    private Vector<Point> points;
    private int distance;
    private int time;
    private int trafficType;
    private Place startPlace;
    private Place goalPlace;
    private String distanceUnit;

    public Path(Vector<Point> points, int distance, int time, int trafficType, Place startPlace, Place goalPlace, String distanceUnit) {
        this.points = points;
        this.distance = distance;
        this.time = time;
        this.trafficType = trafficType;
        this.startPlace = startPlace;
        this.goalPlace = goalPlace;
        this.distanceUnit = distanceUnit;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Vector<Point> getPoints() {
        return points;
    }

    public void setPoints(Vector<Point> points) {
        this.points = points;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(int trafficType) {
        this.trafficType = trafficType;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getGoalPlace() {
        return goalPlace;
    }

    public void setGoalPlace(Place goalPlace) {
        this.goalPlace = goalPlace;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }
}
