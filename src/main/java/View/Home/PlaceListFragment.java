package View.Home;

import MetaData.GuiMetaData;
import Model.Place;
import Network.DataManager;
import Network.Packet;
import View.CustomAlert;
import View.Popup.InfoPopup.InfoPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Vector;

public class PlaceListFragment extends VBox {
    private Label label;
    private VBox itemBox;
    private Vector<PlaceListItemFragment> placeListItemFragments;
    private Button hide;
    private boolean isHideMode = false;

    public Vector<PlaceListItemFragment> getPlaceListItemFragments() {
        return placeListItemFragments;
    }

    public void setPlaceListItemFragments(Vector<PlaceListItemFragment> placeListItemFragments) {
        this.placeListItemFragments = placeListItemFragments;
    }

    //TODO hide 버튼 이미지화, setOnAction 작성
    public PlaceListFragment(String typeName) {
        this.label = new Label(typeName);
        hide = new Button();
        itemBox = new VBox();

        BorderPane labelBox = new BorderPane();
        labelBox.setMinSize(GuiMetaData.PLACELISTITEM_WIDTH, GuiMetaData.PLACELISTITEM_HEIGHT);
        labelBox.setMaxSize(GuiMetaData.PLACELISTITEM_WIDTH, GuiMetaData.PLACELISTITEM_HEIGHT);
        labelBox.setLeft(label);
        labelBox.setRight(hide);
        labelBox.getStyleClass().add("placeListLabelBox");
        placeListItemFragments = new Vector<>();
        itemBox.getChildren().addAll(placeListItemFragments);
        itemBox.getStyleClass().add("itemBox");
        getChildren().addAll(labelBox, itemBox);
        setOnActionHideButton();
        hide.getStyleClass().add("up");
        label.getStyleClass().add("placeLabel");
        itemBox.setSpacing(7);
        setSpacing(10);


    }

    private void setOnActionHideButton(){
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isHideMode == false){
                    getChildren().remove(itemBox);
                    isHideMode = true;
                    hide.getStyleClass().clear();
                    hide.getStyleClass().add("down");
                    requestLayout();
                }
                else{
                    getChildren().add(itemBox);
                    isHideMode = false;
                    hide.getStyleClass().clear();
                    hide.getStyleClass().add("up");
                    requestLayout();
                }
            }
        });
    }

    public void addPlaceListItem(Place place){
        PlaceListItemFragment placeListItemFragment = new PlaceListItemFragment(place);
        setOnActionShowPlaceInfoButton(placeListItemFragment.getShowPlaceInfo(), place);
        placeListItemFragments.add(placeListItemFragment);
        itemBox.getChildren().add(placeListItemFragment);
    }
    
    

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void reset() {
        itemBox.getChildren().removeAll(placeListItemFragments);
        placeListItemFragments.clear();
    }
    private void setOnActionShowPlaceInfoButton(Button button, Place place){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Popup pop = new Popup();
                Stage stage = (Stage)button.getScene().getWindow();
                InfoPopup infoPopup = MainViewContainer.getInstance().getPopups().get(label.getText());
                Place[] tmp ={place};
                Packet receive = DataManager.getInstance().requestData(new Packet(MainViewContainer.getInstance().getPopupReqCode(place), tmp));
                Place result = receive == null ? null : (Place)receive.getData()[0];
                if(result != null) {
                    infoPopup.setContent(result);
                    pop.getContent().add(infoPopup);
                    pop.show(stage);
                }
                else
                    new CustomAlert("정보가 없습니다");
            }
        });
    }
}
