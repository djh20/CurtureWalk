package View;

import MetaData.GuiSizeMetaData;
import Model.Place;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PlaceListItemFragment extends BorderPane {
    private Place place;
    private Button showPlaceInfo;
    private Button showPlaceLocation;
    private CheckBox checkBox;


    //TODO 버튼 이미지화 및 배열 맞추기
    public PlaceListItemFragment(Place place) {
        setMinSize(GuiSizeMetaData.PLACELISTITEM_WIDTH,GuiSizeMetaData.PLACELISTITEM_HEIGHT);
        setMaxSize(GuiSizeMetaData.PLACELISTITEM_WIDTH,GuiSizeMetaData.PLACELISTITEM_HEIGHT);
        this.place = place;
        this.showPlaceInfo = new Button();
        showPlaceInfo.getStyleClass().add("info");
        this.showPlaceLocation = new Button();
        showPlaceLocation.getStyleClass().add("gps");
        Label label = new Label(place.getPlaceName());
        checkBox = new CheckBox();
        setOnActionShowPlaceInfoButton();
        setOnActionShowPlaceLocationButton();
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

    //TODO 체크박스 기능 작성
    private void setOnActionCheckBox(){
        PlaceListItemFragment placeListItemFragment = this;
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkBox.isSelected()==true)
                    MainViewContainer.getInstance().addCheckPlaceListItem(placeListItemFragment);
                else
                    MainViewContainer.getInstance().removeCheckPlaceListItem(placeListItemFragment);
            }
        });
    }

    //TODO 위치보기, 정보보기 setOnAction 작성
    private void setOnActionShowPlaceInfoButton(){
        showPlaceInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Popup pop = new Popup();
                pop.setAutoHide(true);
                Stage stage = (Stage)showPlaceInfo.getScene().getWindow();

                MeseumPopupContainer meseumPopupContainer = new MeseumPopupContainer();

                ForestPopupContainer forestPopupContainer = new ForestPopupContainer();

                RuinsPopupContainer ruinsPopupContainer = new RuinsPopupContainer();

                ConcerthallPopupContainer concerthallPopupContainer = new ConcerthallPopupContainer();

                pop.getContent().add(meseumPopupContainer); //조건에 따라 종류별 팝업컨테이너 넣기
                pop.getContent().get(0).setStyle("-fx-background-radius: 10; -fx-background-color: #c4c4c4;");
                pop.show(stage);
            }
        });
    }
    private void setOnActionShowPlaceLocationButton(){
        showPlaceLocation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
