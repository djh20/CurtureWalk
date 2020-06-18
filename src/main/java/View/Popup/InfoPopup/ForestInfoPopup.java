package View.Popup.InfoPopup;

import Model.Forest;
import Model.Place;

public class ForestInfoPopup extends InfoPopup {
    private final String[] cols = {"전화번호", "주소", "입장료", "홈페이지", "숙박시설유무", "보유시설물"};
    public ForestInfoPopup(){
        super();
        for(String col : cols){
            popupitems.put(col, new Popupitem(col));
        }
        contentBox.getChildren().addAll(popupitems.values());
    }

    @Override
    public void setContent(Place place) {
        Forest park = (Forest) place;
        title.setText(park.getName());
        popupitems.get("전화번호").setContent(park.getPhoneNum());
        popupitems.get("주소").setContent(park.getAddress());
        popupitems.get("입장료").setContent(park.getEntrancePrice());
        popupitems.get("홈페이지").setContent(park.getSiteUrl());
        popupitems.get("숙박시설유무").setContent(park.getCanStay());
        popupitems.get("보유시설물").setContent(park.getFacilities());
        validateLabels();
        initMapVIew(place);
    }
}
