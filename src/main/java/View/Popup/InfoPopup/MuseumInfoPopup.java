package View.Popup.InfoPopup;

import Model.Museum;
import Model.Place;
import View.Popup.InfoPopup.InfoPopup;
import View.Popup.InfoPopup.Popupitem;

public class MuseumInfoPopup extends InfoPopup {


    private final String[] cols = {"전화번호","주소","홈페이지","관리시설","평일 관람시간","주말 관람시간","휴관정보","성인 입장료","청소년 입장료","유아 입장료","소개"};
    public MuseumInfoPopup(){
        super();
        for(String col : cols){
            popupitems.put(col, new Popupitem(col));
        }
        contentBox.getChildren().addAll(popupitems.values());
    }

    @Override
    public void setContent(Place place) {
        Museum museum = (Museum) place;
        title.setText(museum.getName());
        popupitems.get("주소").setContent(museum.getAddress());
        popupitems.get("전화번호").setContent(museum.getPhoneNum());
        popupitems.get("홈페이지").setContent(museum.getSiteUrl());
        popupitems.get("관리시설").setContent(museum.getFacilities());
        popupitems.get("평일 관람시간").setContent(museum.getWeekdayOpen() + " ~ " + museum.getWeekdayClose());
        popupitems.get("주말 관람시간").setContent(museum.getWeekendOpen() + " ~ " + museum.getWeekendClose());
        popupitems.get("휴관정보").setContent(museum.getClosedInfo());
        popupitems.get("성인 입장료").setContent(museum.getAdultPrice());
        popupitems.get("청소년 입장료").setContent(museum.getTeenagerPrice());
        popupitems.get("유아 입장료").setContent(museum.getChildrenPrice());
        popupitems.get("소개").setContent(museum.getIntroduce());
        validateLabels();
        initMapVIew(place);
    }
}
