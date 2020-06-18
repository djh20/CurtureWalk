package View.Popup.ShortestPathResultPopup;

import Controller.ShortestPathManager;
import MetaData.GuiMetaData;
import MetaData.Protocol;
import Model.Place;
import Model.ShortestPathSearchResult;
import View.Home.MainViewContainer;
import View.Home.SearchConditions;
import View.Popup.CustomPopup;
import View.Popup.LoadingPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Vector;

public class PathResultPopup extends CustomPopup {

    private Button showRouteByCar;
    private Button showRouteByPublicTransport;
    private Button showRouteByWalk;
    private PathResultDisplayFragment pathResultDisplayByCar;
    private PathResultDisplayFragment pathResultDisplayByPublicTransport;
    private PathResultDisplayFragment pathResultDisplayByWalk;
    private Place startPlace;
    private Place goalPlace;
    private Vector<Place> stopOvers;
    private LoadingPopup loadingPopup;

    public PathResultPopup(Place start, Place goal, Vector<Place> stopOvers){
        super();
        this.startPlace = start;
        this.goalPlace = goal;
        this.stopOvers = stopOvers;
        initUI();
    }

    private void initUI(){
        loadingPopup = new LoadingPopup();
        initButton();
        initTitleFragment();
        resizeScrollPane();
        initContentBox();
    }

    private void initContentBox(){
        Label message = new Label("  원하는 안내를 선택하세요");
        message.setTextFill(Color.web(("#000000")));
        HBox pane = new HBox();
        pane.setMinWidth(contentBox.getMinWidth());
        pane.setMinHeight(contentBox.getMinHeight());
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.getChildren().add(message);

        contentBox.getChildren().add(pane);
    }

    private void initButton(){
        HBox buttonBox;
        showRouteByCar = new Button();
        showRouteByPublicTransport = new Button();
        showRouteByWalk = new Button();
        showRouteByCar.getStyleClass().addAll("car","imageButton");
        showRouteByPublicTransport.getStyleClass().addAll("publicTransport","imageButton");
        showRouteByWalk.getStyleClass().addAll("walk","imageButton");
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(showRouteByCar,showRouteByPublicTransport,showRouteByWalk);
        buttonBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3,40);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.getStyleClass().add("b-orange");
        buttonBox.setMinWidth(GuiMetaData.MAPVIEW_WIDTH/4*3+10);
        titleBox.getChildren().add(buttonBox);
        setOnActions();
    }

    private void initTitleFragment(){
        HBox titleFragment;
        titleFragment = new HBox();
        titleFragment.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3,30);
        Button paperPlane = new Button();
        paperPlane.getStyleClass().add("paperPlane");
        Label title = new Label("경로 안내");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setStyle("-fx-font-size: 13; -fx-text-fill: black; -fx-padding: 10");
        title.setAlignment(Pos.CENTER);
        titleFragment.getChildren().addAll(paperPlane,title);
        titleFragment.setAlignment(Pos.CENTER);
        titleFragment.getStyleClass().add("b-orange");
        titleFragment.setMinWidth(GuiMetaData.MAPVIEW_WIDTH/4*3+10);
        titleBox.getChildren().addAll(titleFragment);
    }

    private void setOnActions(){
        showRouteByCar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double startTime = System.currentTimeMillis();
                switchPathResultDisplay(getPathResultDisplayByCar());
                double endTime = System.currentTimeMillis();
                System.out.println("소요시간 : " + (endTime - startTime));
            }
        });

        showRouteByPublicTransport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double startTime = System.currentTimeMillis();
                switchPathResultDisplay(getPathResultDisplayByPublicTransport());
                double endTime = System.currentTimeMillis();
                System.out.println("소요시간 : " + (endTime - startTime));
            }
        });

        showRouteByWalk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double startTime = System.currentTimeMillis();
                switchPathResultDisplay(getPathResultDisplayByWalk());
                double endTime = System.currentTimeMillis();
                System.out.println("소요시간 : " + (endTime - startTime));
            }
        });
    }

    private void resizeScrollPane(){
        scroll.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3-70);
        scroll.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3, GuiMetaData.MAPVIEW_HEIGHT/4*3-70);
        scroll.setPadding(new Insets(0, 0, 0, 0));
    }

    private void switchPathResultDisplay(PathResultDisplayFragment pathResultDisplayFragment){

        mapView.getMapManager().clearPaths();
        contentBox.getChildren().clear();
        contentBox.getChildren().add(pathResultDisplayFragment);

    }

    private ShortestPathSearchResult getShortestPathSearchResult(int code){
        ShortestPathSearchResult shortestPath = ShortestPathManager.getInstance().getShortestPath(startPlace,goalPlace,stopOvers, code);
        return shortestPath;
    }

    private PathResultDisplayFragment getPathResultDisplayByCar() {
        if(pathResultDisplayByCar == null)
            pathResultDisplayByCar = new PathResultDisplayFragment(getShortestPathSearchResult(Protocol.REQ_PATH_SEARCH_RESULT_BY_CAR), getMapView(),startPlace,goalPlace);
        return pathResultDisplayByCar;
    }

    private PathResultDisplayFragment getPathResultDisplayByPublicTransport() {
        if(pathResultDisplayByPublicTransport == null)
            pathResultDisplayByPublicTransport = new PathResultDisplayFragment(getShortestPathSearchResult(Protocol.REQ_PATH_SEARCH_RESULT_BY_PUBLIC_TRANSPORT), mapView,startPlace,goalPlace);
        return pathResultDisplayByPublicTransport;
    }

    private PathResultDisplayFragment getPathResultDisplayByWalk() {
        if(pathResultDisplayByWalk == null)
            pathResultDisplayByWalk = new PathResultDisplayFragment(getShortestPathSearchResult(Protocol.REQ_PATH_SEARCH_RESULT_BY_WALK), mapView,startPlace,goalPlace);
        return pathResultDisplayByWalk;
    }
}
