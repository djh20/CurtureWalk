package Model;

public class Museum extends Place {
    private String phoneNum;
    private String operatingInstitution;
    private String siteUrl;
    private String facilities;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String closedInfo;
    private String adultPrice;
    private String teenagerPrice;
    private String childrenPrice;
    private String priceOtherInfo;
    private String introduce;

    public Museum(String name, String address, String type, Double latitude, Double longitude, String phoneNum, String operatingInstitution, String siteUrl, String facilities, String weekdayOpen, String weekdayClose, String weekendOpen, String weekendClose, String closedInfo, String adultPrice, String teenagerPrice, String childrenPrice, String priceOtherInfo, String introduce) {
        super(name, address, type, latitude, longitude);
        this.phoneNum = phoneNum;
        this.operatingInstitution = operatingInstitution;
        this.siteUrl = siteUrl;
        this.facilities = facilities;
        this.weekdayOpen = weekdayOpen;
        this.weekdayClose = weekdayClose;
        this.weekendOpen = weekendOpen;
        this.weekendClose = weekendClose;
        this.closedInfo = closedInfo;
        this.adultPrice = adultPrice;
        this.teenagerPrice = teenagerPrice;
        this.childrenPrice = childrenPrice;
        this.priceOtherInfo = priceOtherInfo;
        this.introduce = introduce;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getOperatingInstitution() {
        return operatingInstitution;
    }

    public void setOperatingInstitution(String operatingInstitution) {
        this.operatingInstitution = operatingInstitution;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getWeekdayOpen() {
        return weekdayOpen;
    }

    public void setWeekdayOpen(String weekdayOpen) {
        this.weekdayOpen = weekdayOpen;
    }

    public String getWeekdayClose() {
        return weekdayClose;
    }

    public void setWeekdayClose(String weekdayClose) {
        this.weekdayClose = weekdayClose;
    }

    public String getWeekendOpen() {
        return weekendOpen;
    }

    public void setWeekendOpen(String weekendOpen) {
        this.weekendOpen = weekendOpen;
    }

    public String getWeekendClose() {
        return weekendClose;
    }

    public void setWeekendClose(String weekendClose) {
        this.weekendClose = weekendClose;
    }

    public String getClosedInfo() {
        return closedInfo;
    }

    public void setClosedInfo(String closedInfo) {
        this.closedInfo = closedInfo;
    }

    public String getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(String adultPrice) {
        this.adultPrice = adultPrice;
    }

    public String getTeenagerPrice() {
        return teenagerPrice;
    }

    public void setTeenagerPrice(String teenagerPrice) {
        this.teenagerPrice = teenagerPrice;
    }

    public String getChildrenPrice() {
        return childrenPrice;
    }

    public void setChildrenPrice(String childrenPrice) {
        this.childrenPrice = childrenPrice;
    }

    public String getPriceOtherInfo() {
        return priceOtherInfo;
    }

    public void setPriceOtherInfo(String priceOtherInfo) {
        this.priceOtherInfo = priceOtherInfo;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
