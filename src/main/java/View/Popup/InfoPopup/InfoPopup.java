package View.Popup.InfoPopup;

import Model.Place;
import View.Popup.CustomPopup;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public abstract class InfoPopup extends CustomPopup {
    protected Label title =new Label("이름");
    public InfoPopup() {
        title.setStyle("-fx-font-size: 22");
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);
    }
    abstract public void setContent(Place place);
    public void initMapVIew(Place place){
        mapView.getMapManager().removeAllMarkers();
        mapView.getMapManager().addMarker(place);
        mapView.getMapManager().setMapCenter(place, 18);
    }
    protected void validateLabels(){
        for(String key : popupitems.keySet()){
            Popupitem popupitem = popupitems.get(key);
            if(popupitem.getContentTextArea().getText().equals("") == true)
                popupitem.setContent("정보 없음");
        }
    }
}
