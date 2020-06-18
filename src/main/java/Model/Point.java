package Model;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = -3140220789751481345L;
    private double lat;
    private double longit;

    public Point(double lat, double longit) {
        this.lat = lat;
        this.longit = longit;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongit() {
        return longit;
    }

    public void setLongit(double longit) {
        this.longit = longit;
    }
}
