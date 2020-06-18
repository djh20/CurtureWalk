package View.Popup.InfoPopup;

import Model.Park;
import Model.Place;
import View.Popup.InfoPopup.InfoPopup;
import View.Popup.InfoPopup.Popupitem;

public class ParkInfoPopup extends InfoPopup {

    private final String[] cols = {"공원 분류", "주소", "시설"};

    public ParkInfoPopup(){
        super();
        for(String col : cols){
            popupitems.put(col, new Popupitem(col));
        }
        contentBox.getChildren().addAll(popupitems.values());
    }

    @Override
    public void setContent(Place place) {
        Park park = (Park) place;
        title.setText(park.getName());
        popupitems.get("공원 분류").setContent(park.getParkType());
        popupitems.get("주소").setContent(park.getAddress());
        popupitems.get("시설").setContent(park.getFacilities());
        validateLabels();
        initMapVIew(place);
    }
}
