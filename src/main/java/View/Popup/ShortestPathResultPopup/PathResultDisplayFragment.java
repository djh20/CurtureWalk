package View.Popup.ShortestPathResultPopup;

import MetaData.GuiMetaData;
import Model.Path;
import Model.Place;
import Model.ShortestPathSearchResult;
import Model.Vertex;
import View.Home.MainViewContainer;
import View.Home.SearchConditions;
import View.Map.MapView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Vector;

public class PathResultDisplayFragment extends VBox {
    private ShortestPathSearchResult result;
    private Vector<PathResultItem> pathResultItems;
    private HBox startGoalBox;
    private Label finalStartPlace;
    private Label finalGoalPlace;
    private Label checkLabel;
    private HBox[] startGoals;
    private MapView mapView;
    private CheckBox checkAll;
    private Place startPlace;
    private Place goalPlace;

    public PathResultDisplayFragment(ShortestPathSearchResult shortestPathSearchResult, MapView mapView, Place startPlace, Place goalPlace) {
        this.startPlace = startPlace;
        this.goalPlace = goalPlace;

        this.mapView = mapView;
        result = shortestPathSearchResult;
        pathResultItems = new Vector<>();
        addPathResultItems();
        initTitle();

        setMinWidth(GuiMetaData.MAPVIEW_WIDTH/4*3 + 10);
        setMaxWidth(GuiMetaData.MAPVIEW_WIDTH/4*3 + 10);
        setPadding(new Insets(5,5,0,5));
        setAlignment(Pos.CENTER);

        getChildren().addAll(pathResultItems);
        setSpacing(3);
        addMarkers();
        this.mapView.getMapManager().setMapCenter(SearchConditions.getInstance().getMapBoundary());
    }

    private void addMarkers(){
        Vector<Vertex> vertices = result.getOrderedNode();
        int index = 0;
        for(int i = 0 ; i < vertices.size() ; i++){
            if(i == 0)
                mapView.getMapManager().addStartMarker(vertices.get(i).getStartNode().getPlace());
            else
                mapView.getMapManager().addNumberMarker(vertices.get(i).getStartNode().getPlace(), ++index);
            if(i == vertices.size() -1)
                mapView.getMapManager().addGoalMarker(vertices.get(i).getDestNode().getPlace());
        }
    }

    private void addPathResultItems(){
        Vector<Vertex> vertices = result.getOrderedNode();
        int itemIdx = 0;
        for(Vertex vertex : vertices){
            Vector<Path> paths = vertex.getRoute().getPathes();
            for(Path path : paths){
                pathResultItems.add(new PathResultItem(path, mapView,itemIdx++));
            }
        }
    }

    private void initTitle(){
        startGoalBox = new HBox();
        startGoals = new HBox[3];
        for(int i=0;i<3;i++){
            startGoals[i] = new HBox();
            startGoals[i].setMaxSize((GuiMetaData.MAPVIEW_WIDTH/4*3 + 10)/3,30);
            startGoals[i].setMinSize((GuiMetaData.MAPVIEW_WIDTH/4*3 + 10)/3,30);
            startGoals[i].setAlignment(Pos.CENTER);
        }
        startGoalBox.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3 + 10,30);
        startGoalBox.setMaxSize(GuiMetaData.MAPVIEW_WIDTH/4*3 + 10,30);
        finalStartPlace = new Label(startPlace.getName()); // 데이터 필요
        finalStartPlace.getStyleClass().add("pathresultfifth");
        startGoals[0].getChildren().add(finalStartPlace);
        finalGoalPlace = new Label(goalPlace.getName()); // 데이터 필요
        finalGoalPlace.getStyleClass().add("pathresultfifth");
        startGoals[1].getChildren().add(finalGoalPlace);
        checkAll = new CheckBox();
        checkLabel = new Label("모든 경로 보기");
        checkLabel.getStyleClass().add("pathresultTen");
        startGoals[2].getChildren().addAll(checkLabel,checkAll);
        allCheck();
        startGoalBox.getChildren().addAll(startGoals);
        startGoalBox.setAlignment(Pos.CENTER);
        this.getChildren().add(startGoalBox);
    }



    public void allCheck(){
        checkAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(PathResultItem pathResultItem :pathResultItems){
                    pathResultItem.checkPathDrawCheckBox(checkAll.isSelected());
                }
            }
        });
    }
}
