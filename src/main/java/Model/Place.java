package Model;

public class Place {
    private String placeName;
    private Double longitude;
    private Double latitude;
    private String address;

    public Place(String placeName, Double longitude, Double latitude, String address) {
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public Place(String placeName, Double longitude, Double latitude) {
        this.placeName = placeName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
