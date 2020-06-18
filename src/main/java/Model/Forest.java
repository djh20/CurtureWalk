package Model;

public class Forest extends Place {
    private String entrancePrice;
    private String canStay;
    private String facilities;
    private String phoneNum;
    private String siteUrl;

    public Forest(String name, String address, String type, Double latitude, Double longitude, String entrancePrice, String canStay, String facilities, String phoneNum, String siteUrl) {
        super(name, address, type, latitude, longitude);
        this.entrancePrice = entrancePrice;
        this.canStay = canStay;
        this.facilities = facilities;
        this.phoneNum = phoneNum;
        this.siteUrl = siteUrl;
    }

    public String getEntrancePrice() {
        return entrancePrice;
    }

    public void setEntrancePrice(String entrancePrice) {
        this.entrancePrice = entrancePrice;
    }

    public String getCanStay() {
        return canStay;
    }

    public void setCanStay(String canStay) {
        this.canStay = canStay;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
