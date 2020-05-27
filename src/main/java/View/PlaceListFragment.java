package View;

import MetaData.GuiSizeMetaData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Vector;

public class PlaceListFragment extends VBox {
    private Label label;
    private VBox itemBox;
    private Vector<PlaceListItemFragment> placeListItemFragments;
    private Button hide;
    private boolean isHideMode = false;

    //TODO hide 버튼 이미지화, setOnAction 작성
    public PlaceListFragment(String typeName) {
        this.label = new Label(typeName);
        hide = new Button();
        itemBox = new VBox();

        BorderPane labelBox = new BorderPane();
        labelBox.setMinSize(GuiSizeMetaData.PLACELISTITEM_WIDTH,GuiSizeMetaData.PLACELISTITEM_HEIGHT);
        labelBox.setMaxSize(GuiSizeMetaData.PLACELISTITEM_WIDTH,GuiSizeMetaData.PLACELISTITEM_HEIGHT);
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

    public void addPlaceListItem(PlaceListItemFragment placeListItemFragment){
        placeListItemFragments.add(placeListItemFragment);
        itemBox.getChildren().add(placeListItemFragment);
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
