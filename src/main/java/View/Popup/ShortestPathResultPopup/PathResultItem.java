package View.Popup.ShortestPathResultPopup;

import MetaData.GuiMetaData;
import MetaData.ShortestPathApiMetaData;
import Model.Path;
import Model.PublicTransportPath;
import View.Map.MapView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class PathResultItem extends VBox {
    private Label startPlace;
    private Label goalPlace;
    private Label pathLabel;
    private Label time;
    private Label distance;
    private BorderPane titleBox;
    private HBox contentsBox;
    private BorderPane infoBox;
    private VBox info;
    private VBox iconBox;
    private VBox checkViewBox;
    private Button icon;
    private boolean isHideMode = true;
    private Button hide;
    private Path path;
    private CheckBox pathDrawCheckBox;
    private MapView mapView;
    private int itemIndex;
    public PathResultItem(Path path, MapView mapView, int idx){
        itemIndex = idx;
        this.path = path;
        this.mapView = mapView;
        initUI();
        getChildren().add(titleBox);
    }
    private void initUI(){
        this.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3+10,30);
        this.setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3);
        setSpacing(2);
        setPadding(new Insets(0,0,0,10));
        initIconBox();//        setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3+10,50);
        //        setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3);
        initTitleBox();
        initInfoBox();
        initContentBox();


    }

    private void initIconBox(){
        iconBox = new VBox();
        iconBox.setAlignment(Pos.CENTER);
        iconBox.setMinSize(50,50);
        createIcon(path.getTrafficType());
        time = new Label(path.getTime()+"분");
        this.time.getStyleClass().add("pathresultTen");
        iconBox.getChildren().addAll(icon,time);
        iconBox.getStyleClass().add("r-orange");
    }

    private void createIcon(int trafficType){
        final HashMap<Integer, String> styleClass = new HashMap<Integer, String>(){{
            put(ShortestPathApiMetaData.PATH_TYPE_WALK,"walkMini");
            put(ShortestPathApiMetaData.PATH_TYPE_CAR,"carMini");
            put(ShortestPathApiMetaData.PATH_TYPE_SUBWAY,"subwayMini");
            put(ShortestPathApiMetaData.PATH_TYPE_BUS,"busMini");
        }};
        icon = new Button();
        icon.getStyleClass().add(styleClass.get(trafficType));
    }

    private void initTitleBox(){
        this.startPlace = new Label(path.getStartPlace().getName());
        this.startPlace.getStyleClass().add("pathresultthird");
        this.goalPlace = new Label(path.getGoalPlace().getName());
        this.goalPlace.getStyleClass().add("pathresultthird");
        PublicTransportPath publicTransportPath = (path instanceof PublicTransportPath ? (PublicTransportPath) path : null);
        double displayDistance = path.getDistanceUnit().equals("m") ? ((double)path.getDistance())/1000 : path.getDistance();
        distance = new Label(displayDistance + "km" + (publicTransportPath == null ? "" : "  "+publicTransportPath.getPublicTransport()));
        this.distance.getStyleClass().add("pathresultTen");
        titleBox = new BorderPane();
        titleBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3 -10,30);
        titleBox.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3 -10,30);
        pathLabel = new Label(path.getStartPlace().getName()+" - "+path.getGoalPlace().getName());
        pathLabel.getStyleClass().add("pathresultfifth");
        pathLabel.setPadding(new Insets(0,0,0,10));
        hide = new Button();
        hide.getStyleClass().add("down");
        titleBox.setLeft(pathLabel);
        titleBox.setRight(hide);
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isHideMode == false){
                    getChildren().remove(contentsBox);
                    isHideMode = true;
                    hide.getStyleClass().clear();
                    hide.getStyleClass().add("down");
                    requestLayout();
                }
                else{
                    getChildren().add(contentsBox);
                    isHideMode = false;
                    hide.getStyleClass().clear();
                    hide.getStyleClass().add("up");
                    requestLayout();
                }
            }
        });
    }

    private void initPathDrawCheckBox(){
        pathDrawCheckBox = new CheckBox();
        pathDrawCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(aBoolean != t1) {
                    if (pathDrawCheckBox.isSelected() == true)
                        mapView.getMapManager().reqAddPath(path,itemIndex);
                    else
                        mapView.getMapManager().removePath(itemIndex);
                }
            }
        });
    }

    private void initInfoBox(){
        infoBox = new BorderPane();
        info = new VBox();
        checkViewBox = new VBox();
        initPathDrawCheckBox();
        checkViewBox.setMinSize(70,50);
        checkViewBox.setMaxSize(70,50);
        checkViewBox.setPadding(new Insets(0,20,0,0));
        info.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3-70,50);
        info.setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3-70);
        infoBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3-20,50);
        infoBox.setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3-20);
        infoBox.setPadding(new Insets(0,0,0,10));
        checkViewBox.getChildren().add(pathDrawCheckBox);
        checkViewBox.setAlignment(Pos.CENTER_LEFT);
        info.getChildren().addAll(this.startPlace,this.distance,this.goalPlace);
        infoBox.setLeft(info);
        infoBox.setRight(checkViewBox);
    }


    private void initContentBox(){
        contentsBox = new HBox();
        contentsBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3,50);
        contentsBox.setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3);
        contentsBox.getChildren().addAll(iconBox,infoBox);
        contentsBox.setStyle("-fx-background-color: #F8E6E0");
    }

    public void checkPathDrawCheckBox(boolean check){
        pathDrawCheckBox.setSelected(check);
    }
}