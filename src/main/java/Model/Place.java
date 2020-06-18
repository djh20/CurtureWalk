package Model;

import java.io.Serializable;

public class Place implements Serializable {
    private static final long serialVersionUID = -3140220789751481347L;
    private String name;
    private String address;
    private String type;
    private Double latitude;
    private Double longitude;


    public Place(String name, String address, String type, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + " " + type + "/" + address.split(" ")[0];
    }
}
