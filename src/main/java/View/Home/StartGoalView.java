package View.Home;

import MetaData.GuiMetaData;
import MetaData.Protocol;
import Model.MapBoundary;
import Model.Place;
import Network.DataManager;
import Network.Packet;
import View.CustomAlert;
import View.Popup.ShortestPathResultPopup.PathResultPopup;
import impl.org.controlsfx.skin.AutoCompletePopup;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.border.EmptyBorder;
import java.util.Vector;

public class StartGoalView extends VBox {
    private AutoCompleteTextField start;
    private AutoCompleteTextField goal;
    private ToggleSwitch setGoalAsStart;
    private Button startGps;
    private Button goalGps;
    private Button searchPath;

    public StartGoalView() {
        getStyleClass().add("startGoalView");
        setSpacing(8);
        setMinSize(GuiMetaData.STARTGOALVIEW_WIDTH, GuiMetaData.STARTGOALVIEW_HEIGHT);
        setMaxSize(GuiMetaData.STARTGOALVIEW_WIDTH, GuiMetaData.STARTGOALVIEW_HEIGHT);
        start = new AutoCompleteTextField(GuiMetaData.STARTGOALVIEW_WIDTH - 35, 20);
        goal = new AutoCompleteTextField(GuiMetaData.STARTGOALVIEW_WIDTH - 35, 20);
        MainViewContainer.getInstance().setStartPlace(start);
        MainViewContainer.getInstance().setGoalPlace(goal);
        start.setPromptText("출발지를 입력해 주세요");
        goal.setPromptText("도착지를 입력해 주세요");
        setGoalAsStart = new ToggleSwitch(30, 15);
        setGoalAsStart.switchedOnProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(aBoolean == false) {
                    goal.setEditable(false);
                    goal.setText(start.getText());
                    goal.setSelectedPlace(start.getSelectedPlace());
                    goal.getAutoCompletePopup().hide();
                }
                else
                    goal.setEditable(true);
            }
        });
        setGoalAsStart.getStyleClass().add("setGoalAsStart");
        startGps = new Button("위치");
        startGps.getStyleClass().add("gps");
        goalGps = new Button("위치");
        goalGps.getStyleClass().add("gps");
        searchPath = new Button("경로탐색");

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(new Label("출발지와 도착지를 같게"));
        buttonBox.getChildren().add(setGoalAsStart);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setSpacing(5);

        HBox startBox = new HBox();
        startBox.setSpacing(10);
        startBox.getChildren().addAll(start, startGps);
        HBox goalBox = new HBox();
        goalBox.getChildren().addAll(goal, goalGps);
        startBox.setSpacing(3);
        goalBox.setSpacing(3);
        getChildren().addAll(buttonBox, startBox, goalBox, searchPath);
        setOnActionSearchPath();
        setListnerGoal(goal);
        setListnerStart(start);
        setOnGpsAction();
    }


    private void setOnGpsAction(){
        startGps.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainViewContainer.getInstance().getMapView().getMapManager().setMapCenter(start.getSelectedPlace());
            }
        });

        goalGps.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainViewContainer.getInstance().getMapView().getMapManager().setMapCenter(goal.getSelectedPlace());
            }
        });
    }

    private void setOnActionSearchPath() {
        searchPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Place start = MainViewContainer.getInstance().getStartPlace().getSelectedPlace();
                Place goal = MainViewContainer.getInstance().getGoalPlace().getSelectedPlace();
                Vector<Place> stopOvers = SearchConditions.getInstance().getStopOvers();
                MapBoundary mapBoundary = MainViewContainer.getInstance().getMapView().getMapManager().getMapBoundary();
                System.out.println(mapBoundary.getZoom());
                if (validation(start, goal, stopOvers, mapBoundary) == true) {
                    SearchConditions.getInstance().setMapBoundary(mapBoundary);
                    Stage stage = (Stage) searchPath.getScene().getWindow();
                    Popup pop = new Popup();
                    PathResultPopup pathResult = new PathResultPopup(start, goal, stopOvers);
                    pop.getContent().add(pathResult);
                    pop.show(stage);
                }
            }
        });
    }

    private boolean validation(Place start, Place goal, Vector<Place> stopOvers, MapBoundary mapBoundary) {
        if (start == null || goal == null)
            new CustomAlert("출발지 및 도착지를 입력해주세요");
        else if (stopOvers.size() == 0)
            new CustomAlert("여행할 관광지를 선택해주세요");
        else if (mapBoundary.getZoom() <= 11)
            new CustomAlert("지도 영역이 너무 넓습니다");
        else if ((mapBoundary.getMin_lat() <= start.getLatitude() && start.getLatitude() <= mapBoundary.getMax_lat()) == false ||
                (mapBoundary.getMin_long() <= start.getLongitude() && start.getLongitude() <= mapBoundary.getMax_long()) == false)
            new CustomAlert("출발지가 지도 범위에 존재하지 않습니다 조정 후 다시 시도해주세요");
        else if ((mapBoundary.getMin_lat() <= goal.getLatitude() && goal.getLatitude() <= mapBoundary.getMax_lat()) == false ||
                (mapBoundary.getMin_long() <= goal.getLongitude() && goal.getLongitude() <= mapBoundary.getMax_long()) == false)
            new CustomAlert("도착지가 지도 범위에 존재하지 않습니다 조정 후 다시 시도해주세요");
        else if(isAllPlaceInMap(start,goal,stopOvers,mapBoundary) == false)
            new CustomAlert("출발지 도착지 및 관광지를 지도 영역에 포함시키고 다시 시도해주세요");
        else
            return true;
        return false;
    }

    private boolean isAllPlaceInMap(Place start, Place goal, Vector<Place> stopOvers, MapBoundary mapBoundary){
        Vector<Place> places = (Vector<Place>) stopOvers.clone();
        places.add(start);
        places.add(goal);
        for(Place place : places){
            if(mapBoundary.getMin_lat() > place.getLatitude() || place.getLatitude() > mapBoundary.getMax_lat())
                return false;
            if(mapBoundary.getMin_long() > place.getLongitude() || place.getLongitude() > mapBoundary.getMax_long())
                return false;
        }
        return true;
    }

    private void setListnerStart(AutoCompleteTextField autoCompleteTextField) {
        autoCompleteTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (autoCompleteTextField.getText().length() > 0) {
                autoCompleteTextField.setSelectedPlace(null);
                autoCompleteTextField.getAutoCompletePopup().hide();
                autoCompleteTextField.getAutoCompletePopup().setMinWidth(autoCompleteTextField.getMinWidth());
                String text[] = {autoCompleteTextField.getText()};
                Packet<Place> receive = DataManager.getInstance().requestData(new Packet(Protocol.REQ_PLACELIST_MATCH_NAME, text));
                Place[] response = receive.getData();
                if (response.length > 0) {
                    autoCompleteTextField.getAutoCompletePopup().getSuggestions().clear();
                    autoCompleteTextField.getAutoCompletePopup().getSuggestions().addAll(response);
                    autoCompleteTextField.getAutoCompletePopup().show(autoCompleteTextField);
                }
                autoCompleteTextField.getAutoCompletePopup().setOnSuggestion(new EventHandler<AutoCompletePopup.SuggestionEvent>() {
                    @Override
                    public void handle(AutoCompletePopup.SuggestionEvent suggestionEvent) {
                        Place selected = ((Place) suggestionEvent.getSuggestion());
                        autoCompleteTextField.setText(selected.getName());
                        autoCompleteTextField.setSelectedPlace(selected);
                        autoCompleteTextField.getAutoCompletePopup().getSuggestions().clear();
                        autoCompleteTextField.getAutoCompletePopup().hide();
                        MainViewContainer.getInstance().getMapView().getMapManager().addStartMarker(selected);
                        setMapCenter(selected);
                        if(setGoalAsStart.isOn() == true){
                            goal.setText(selected.getName());
                            goal.setSelectedPlace(selected);
                            goal.getAutoCompletePopup().hide();
                        }
                    }
                });
            }
        });
    }

    private void setListnerGoal(AutoCompleteTextField autoCompleteTextField) {
        autoCompleteTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (autoCompleteTextField.getText().length() > 0) {
                autoCompleteTextField.setSelectedPlace(null);
                autoCompleteTextField.getAutoCompletePopup().hide();
                autoCompleteTextField.getAutoCompletePopup().setMinWidth(autoCompleteTextField.getMinWidth());
                String text[] = {autoCompleteTextField.getText()};
                Packet<Place> receive = DataManager.getInstance().requestData(new Packet(Protocol.REQ_PLACELIST_MATCH_NAME, text));
                Place[] response = receive.getData();
                if (response.length > 0) {
                    autoCompleteTextField.getAutoCompletePopup().getSuggestions().clear();
                    autoCompleteTextField.getAutoCompletePopup().getSuggestions().addAll(response);
                    autoCompleteTextField.getAutoCompletePopup().show(autoCompleteTextField);
                }
                autoCompleteTextField.getAutoCompletePopup().setOnSuggestion(new EventHandler<AutoCompletePopup.SuggestionEvent>() {
                    @Override
                    public void handle(AutoCompletePopup.SuggestionEvent suggestionEvent) {
                        Place selected = ((Place) suggestionEvent.getSuggestion());
                        autoCompleteTextField.setText(selected.getName());
                        autoCompleteTextField.setSelectedPlace(selected);
                        autoCompleteTextField.getAutoCompletePopup().getSuggestions().clear();
                        autoCompleteTextField.getAutoCompletePopup().hide();
                        MainViewContainer.getInstance().getMapView().getMapManager().addGoalMarker(selected);
                        setMapCenter(selected);
                    }
                });
            }
        });
    }

    public void setMapCenter(Place place) {
        Vector<Place> stopOvers = (Vector<Place>) SearchConditions.getInstance().getStopOvers().clone();
        stopOvers.add(place);
        double min_lat = 0;
        double max_lat = 0;
        double min_long = 0;
        double max_long = 0;
        for (Place stopOver : stopOvers) {
            if (min_long > stopOver.getLongitude())
                min_long = stopOver.getLatitude();
            if (max_long < stopOver.getLongitude())
                max_long = stopOver.getLatitude();
            if (min_lat > stopOver.getLatitude())
                min_lat = stopOver.getLatitude();
            if (max_lat < stopOver.getLatitude())
                max_lat = stopOver.getLatitude();
        }
        MainViewContainer.getInstance().getMapView().getMapManager().setMapCenter(new MapBoundary(min_lat, max_lat, min_long, max_long, 13));
    }

}



class ToggleSwitch extends Parent {

    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public boolean isOn(){
        return switchedOn.getValue();
    }

    public ToggleSwitch(int width, int height) {

        Rectangle background = new Rectangle(width, height);
        background.setArcWidth(width/2);
        background.setArcHeight(height);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);

        Circle trigger = new Circle(height/2);
        trigger.setCenterX(height/2);
        trigger.setCenterY(height/2);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(2);
        trigger.setEffect(shadow);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        getChildren().addAll(background, trigger);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setToX(isOn ? width - height : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);
            animation.play();
            System.out.println(isOn);
        });

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });
    }
};