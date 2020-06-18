package View.Popup;

import MetaData.GuiMetaData;
import View.Map.MapView;
import View.Popup.InfoPopup.Popupitem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.util.HashMap;

public class CustomPopup extends BorderPane {
    protected VBox contentBox = new VBox();
    protected ScrollPane scroll = new ScrollPane();
    protected VBox titleBox = new VBox();
    protected HashMap<String, Popupitem> popupitems = new HashMap<>();
    protected VBox rightBox = new VBox();
    protected BorderPane topBox = new BorderPane();
    protected MapView mapView = new MapView(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3, GuiMetaData.popupMapURL);
    private Button close = new Button();
    private Popup stage = null;
    private double xOffset = 0;
    private double yOffset = 0;

    public CustomPopup() {
        getStyleClass().add("popupMain");
        titleBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20, 50);
        close.getStyleClass().add("close");
        topBox.setRight(close);
        scroll.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3-60);
        scroll.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3-60);
        scroll.setContent(contentBox);
        scroll.getStyleClass().add("passingPane");
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentBox.setMinWidth(GuiMetaData.MAPVIEW_WIDTH/4*3-20);
        contentBox.setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3-20);
        rightBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3);
        rightBox.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3);
        this.setPadding(new Insets(10));
        VBox mapBox = new VBox();
        mapBox.getChildren().add(mapView);
        mapBox.setPadding(new Insets(0.3));
        mapBox.setStyle("-fx-border-width: 3;  -fx-border-color: #F5D0A9;");
        rightBox.getChildren().addAll(titleBox,scroll);
        setonAction();
        stageDragableMoveWindow();
        this.setTop(topBox);
        this.setLeft(mapBox);
        this.setRight(rightBox);
    }

    private void setonAction(){
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Popup stage = (Popup)close.getScene().getWindow();
                stage.hide();
            }
        });
    }

    private void stageDragableMoveWindow() {
        this.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged((event) -> {
            stage = (Popup) this.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            stage.setOpacity(0.8f);
        });
        this.setOnDragDone((event) -> {
            stage = (Popup) this.getScene().getWindow();
            stage.setOpacity(1.0f);
        });
        this.setOnMouseReleased((event) -> {
            stage = (Popup) this.getScene().getWindow();
            stage.setOpacity(1.0f);
        });
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
}
