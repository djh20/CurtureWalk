package Model;

public class ConcertHall extends Place {
    private String phoneNum;
    private String numSeat;
    private String siteUrl;
    private String hasParkingLot;

    public ConcertHall(String name, String address, String type, Double latitude, Double longitude, String phoneNum, String numSeat, String siteUrl, String hasParkingLot) {
        super(name, address, type, latitude, longitude);
        this.phoneNum = phoneNum;
        this.numSeat = numSeat;
        this.siteUrl = siteUrl;
        this.hasParkingLot = hasParkingLot;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNumSeat() {
        return numSeat;
    }

    public void setNumSeat(String numSeat) {
        this.numSeat = numSeat;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getHasParkingLot() {
        return hasParkingLot;
    }

    public void setHasParkingLot(String hasParkingLot) {
        this.hasParkingLot = hasParkingLot;
    }
}
