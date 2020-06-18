package Model;

import java.io.Serializable;

public class ConcertInfo implements Serializable {
    private static final long serialVersionUID = -3140220789751481340L;
    private String concertName;
    private String concertHallName;
    private String concertStartDay;
    private String concertEndDay;
    private String introduce;
    private String concertTime;
    private String isFree;
    private String entrancePrice;
    private String entranceAge;

    public ConcertInfo(String concertName, String concertHallName, String concertStartDay, String concertEndDay, String introduce, String concertTime, String isFree, String entrancePrice, String entranceAge) {
        this.concertName = concertName;
        this.concertHallName = concertHallName;
        this.concertStartDay = concertStartDay;
        this.concertEndDay = concertEndDay;
        this.introduce = introduce;
        this.concertTime = concertTime;
        this.isFree = isFree;
        this.entrancePrice = entrancePrice;
        this.entranceAge = entranceAge;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getConcertHallName() {
        return concertHallName;
    }

    public void setConcertHallName(String concertHallName) {
        this.concertHallName = concertHallName;
    }

    public String getConcertStartDay() {
        return concertStartDay;
    }

    public void setConcertStartDay(String concertStartDay) {
        this.concertStartDay = concertStartDay;
    }

    public String getConcertEndDay() {
        return concertEndDay;
    }

    public void setConcertEndDay(String concertEndDay) {
        this.concertEndDay = concertEndDay;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getConcertTime() {
        return concertTime;
    }

    public void setConcertTime(String concertTime) {
        this.concertTime = concertTime;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getEntrancePrice() {
        return entrancePrice;
    }

    public void setEntrancePrice(String entrancePrice) {
        this.entrancePrice = entrancePrice;
    }

    public String getEntranceAge() {
        return entranceAge;
    }

    public void setEntranceAge(String entranceAge) {
        this.entranceAge = entranceAge;
    }
}
