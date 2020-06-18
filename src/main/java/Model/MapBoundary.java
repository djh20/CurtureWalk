package Model;

import java.io.Serializable;

public class MapBoundary implements Serializable {
    private static final long serialVersionUID = -3140220789751481346L;
    private double min_lat;
    private double max_lat;
    private double min_long;
    private double max_long;
    private int zoom;

    public MapBoundary(double min_lat, double max_lat, double min_long, double max_long, int zoom) {
        this.min_lat = min_lat;
        this.max_lat = max_lat;
        this.min_long = min_long;
        this.max_long = max_long;
        this.zoom = zoom;
    }

    public double getMin_lat() {
        return min_lat;
    }

    public void setMin_lat(double min_lat) {
        this.min_lat = min_lat;
    }

    public double getMax_lat() {
        return max_lat;
    }

    public void setMax_lat(double max_lat) {
        this.max_lat = max_lat;
    }

    public double getMin_long() {
        return min_long;
    }

    public void setMin_long(double min_long) {
        this.min_long = min_long;
    }

    public double getMax_long() {
        return max_long;
    }

    public void setMax_long(double max_long) {
        this.max_long = max_long;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
