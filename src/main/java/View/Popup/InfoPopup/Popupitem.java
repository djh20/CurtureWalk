package View.Popup.InfoPopup;


import MetaData.GuiMetaData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Popupitem extends VBox {
    private Button hide;
    private BorderPane labelBox;
    private boolean isHideMode = true;
    private VBox seperator;
    private TextArea contentTextArea;
    public void setContent(String content){
        contentTextArea.setText(content);
    }
    Popupitem(String column){
        setPadding(new Insets(10));
        labelBox = new BorderPane();
        labelBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20, GuiMetaData.PLACELISTITEM_HEIGHT);
        labelBox.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20, GuiMetaData.PLACELISTITEM_HEIGHT);
        seperator = new VBox();
        seperator.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20,2);
        seperator.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20,2);
        seperator.setPadding(new Insets(20));
        seperator.getStyleClass().add("seperator-orange");
        VBox marginBox[] = new VBox[2];
        Label columnLabel = new Label(column);
        for(int i=0;i<2;i++){
            marginBox[i] = new VBox();
            marginBox[i].setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20,10);
            marginBox[i].setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20,10);
        }
        columnLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
        contentTextArea = new TextArea();
        contentTextArea.setEditable(false);
        contentTextArea.setWrapText(true);
        contentTextArea.setPrefRowCount(1);
        contentTextArea.setPrefHeight(5);
        contentTextArea.getStyleClass().add("popupContentArea");
        hide = new Button();
        hide.getStyleClass().add("down");
        labelBox.setLeft(columnLabel);
        labelBox.setRight(hide);
        getChildren().addAll(labelBox,marginBox[0],seperator,marginBox[1]);
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isHideMode == false){
                    getChildren().remove(contentTextArea);
                    isHideMode = true;
                    hide.getStyleClass().clear();
                    hide.getStyleClass().add("down");
                    requestLayout();
                }
                else{
                    getChildren().add(contentTextArea);
                    isHideMode = false;
                    hide.getStyleClass().clear();
                    hide.getStyleClass().add("up");
                    requestLayout();
                }
            }
        });
    }

    public TextArea getContentTextArea() {
        return contentTextArea;
    }

    public void setContentTextArea(TextArea contentTextArea) {
        this.contentTextArea = contentTextArea;
    }
}
