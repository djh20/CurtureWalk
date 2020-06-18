package Model;

public class Park extends Place {
    private String parkType;
    private String facilities;

    public Park(String name, String address, String type, Double latitude, Double longitude, String parkType, String facilities) {
        super(name, address, type, latitude, longitude);
        this.parkType = parkType;
        this.facilities = facilities;
    }

    public String getParkType() {
        return parkType;
    }

    public void setParkType(String parkType) {
        this.parkType = parkType;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
}
