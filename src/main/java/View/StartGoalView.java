package View;

import MetaData.GuiSizeMetaData;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartGoalView extends VBox {
    AutoCompleteTextField start;
    AutoCompleteTextField goal;
    ToggleSwitch setGoalAsStart;
    Button startGps;
    Button goalGps;
    Button searchPath;
    public StartGoalView() {
        getStyleClass().add("startGoalView");
        setSpacing(8);
        setMinSize(GuiSizeMetaData.STARTGOALVIEW_WIDTH, GuiSizeMetaData.STARTGOALVIEW_HEIGHT);
        setMaxSize(GuiSizeMetaData.STARTGOALVIEW_WIDTH, GuiSizeMetaData.STARTGOALVIEW_HEIGHT);
        start = new AutoCompleteTextField();
        goal = new AutoCompleteTextField();
        start.setPromptText("출발지를 입력해 주세요");
        goal.setPromptText("도착지를 입력해 주세요");
        setGoalAsStart = new ToggleSwitch(30,15);
        setGoalAsStart.getStyleClass().add("setGoalAsStart");
        startGps = new Button("위치");
        startGps.getStyleClass().add("gps");
        goalGps = new Button("위치");
        goalGps.getStyleClass().add("gps");
        searchPath = new Button("경로탐색");
        searchPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Popup pop = new Popup();
                pop.setAutoHide(true);
                Stage stage = (Stage)searchPath.getScene().getWindow();
                PathResultContainer pathResultContainer = new PathResultContainer(5);
                pop.getContent().add(pathResultContainer);
                pop.getContent().get(0).setStyle("-fx-background-radius: 10; -fx-background-color: #c4c4c4;");
                pop.show(stage);
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(new Label("출발지와 도착지를 같게"));
        buttonBox.getChildren().add(setGoalAsStart);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setSpacing(5);

        HBox startBox = new HBox();
        startBox.setSpacing(10);
        start.setMinWidth(GuiSizeMetaData.STARTGOALVIEW_WIDTH - 35);
        startBox.getChildren().addAll(start, startGps);
        HBox goalBox = new HBox();
        goal.setMinWidth(GuiSizeMetaData.STARTGOALVIEW_WIDTH - 35);
        goalBox.getChildren().addAll(goal, goalGps);
        startBox.setSpacing(3);
        goalBox.setSpacing(3);

        getChildren().addAll(buttonBox, startBox, goalBox, searchPath);
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
        });

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });
    }
}