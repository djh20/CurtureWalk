package View.Home;

import MetaData.GuiMetaData;
import Model.Place;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlaceListItemFragment extends BorderPane {
    private Place place;
    private Button showPlaceInfo;
    private Button showPlaceLocation;
    private CheckBox checkBox;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    //TODO 버튼 이미지화 및 배열 맞추기
    public PlaceListItemFragment(Place place) {
        setMinSize(GuiMetaData.PLACELISTITEM_WIDTH, GuiMetaData.PLACELISTITEM_HEIGHT);
        setMaxSize(GuiMetaData.PLACELISTITEM_WIDTH, GuiMetaData.PLACELISTITEM_HEIGHT);
        this.place = place;
        this.showPlaceInfo = new Button();
        showPlaceInfo.getStyleClass().add("info");
        this.showPlaceLocation = new Button();
        showPlaceLocation.getStyleClass().add("gps");
        Label label = new Label(place.getName());
        checkBox = new CheckBox();
        setOnActionShowPlaceInfoButton();
        setOnActionShowPlaceLocationButton();
        setOnActionCheckBox();
        setLeft(label);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(showPlaceInfo);
        buttonBox.getChildren().add(showPlaceLocation);
        buttonBox.getChildren().add(checkBox);
        buttonBox.setSpacing(7);
        setRight(buttonBox);
        setMinHeight(20);
        getStyleClass().add("listItem");
    }

    public Button getShowPlaceInfo() {
        return showPlaceInfo;
    }

    public void setShowPlaceInfo(Button showPlaceInfo) {
        this.showPlaceInfo = showPlaceInfo;
    }

    //TODO 체크박스 기능 작성
    private void setOnActionCheckBox(){
        PlaceListItemFragment placeListItemFragment = this;
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkBox.isSelected()==true) {
                    SearchConditions.getInstance().addStopOver(place);
                    if(MainViewContainer.getInstance().getControlPanel().isChecked() == true)
                        MainViewContainer.getInstance().getMapView().getMapManager().addMarker(place);
                }
                else
                    SearchConditions.getInstance().removeStopOver(place);
            }
        });

    }

    //TODO 위치보기, 정보보기 setOnAction 작성
    private void setOnActionShowPlaceInfoButton(){
        showPlaceInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
    private void setOnActionShowPlaceLocationButton(){
        showPlaceLocation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainViewContainer.getInstance().getMapView().getMapManager().setMapCenterWthoutZoom(place);
            }
        });
    }
}
