package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class PathResultContainer extends BorderPane {

    private Button car;
    private Button publicTransport;
    private Button walk;
    private Label predictTimeInCar;
    private Label predictDistanceInCar;
    private Label predictTimeInPublic;
    private Label predictDistanceInPublic;
    private Label predictTimeInWalk;
    private Label predictDistanceInWalk;
    private Label startPlace;
    private Label endPlace;
    private Label passingPlaceIndex[];
    private Label passingPlace[];
    private Button save;

    PathResultContainer(int passingPlaceCount){
        this.car = new Button();
        this.publicTransport = new Button();
        this.walk = new Button();

        passingPlace = new Label[passingPlaceCount];
        passingPlaceIndex = new Label[passingPlaceCount];

        car.getStyleClass().add("car");
        publicTransport.getStyleClass().add("publicTransport");
        walk.getStyleClass().add("walk");

        HBox buttonbox = new HBox();
        buttonbox.getChildren().addAll(car,publicTransport,walk);
        buttonbox.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH,60);
        buttonbox.setAlignment(Pos.TOP_CENTER);
        VBox contents = new VBox();
        contents.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH,GuiSizeMetaData.MAPVIEW_HEIGHT);
        contents.setAlignment(Pos.CENTER);
        GridPane predictData = new GridPane();
        predictData.setMinHeight(GuiSizeMetaData.MAPVIEW_HEIGHT/7);
        predictData.setAlignment(Pos.CENTER);

        Label indexTime = new Label("소요시간");
        settingLabel(indexTime);

        this.predictTimeInCar = new Label("12분");
        settingLabel(this.predictTimeInCar);

        Label indexDistance = new Label("예상 거리");
        settingLabel(indexDistance);

        this.predictDistanceInCar = new Label("1km");
        settingLabel(this.predictDistanceInCar);


        predictData.add(indexTime,0,0);
        predictData.add(this.predictTimeInCar,0,1); //button에 따라 다르게 넣기
        predictData.add(indexDistance,1,0);
        predictData.add(this.predictDistanceInCar,1,1); //button에 따라 다르게 넣기
        predictData.getColumnConstraints().add(new ColumnConstraints(130));
        predictData.getColumnConstraints().add(new ColumnConstraints(130));

        GridPane passingPlaceList = new GridPane();
        passingPlaceList.setAlignment(Pos.CENTER);

        Label startIndex = new Label("출발지");
        settingLabel(startIndex);
        this.startPlace = new Label("출발지데이터");
        settingLabel(this.startPlace);
        passingPlaceList.add(startIndex,0,0);
        passingPlaceList.add(this.startPlace,1,0);
        for(int i=0; i<passingPlaceCount; i++){
            this.passingPlaceIndex[i] = new Label(Integer.toString(i));
            settingLabel(this.passingPlaceIndex[i]);
            this.passingPlace[i] = new Label("경유지 "+Integer.toString(i));
            settingLabel(this.passingPlace[i]);
            passingPlaceList.add(this.passingPlaceIndex[i],0,i+1);
            passingPlaceList.add(this.passingPlace[i],1,i+1);
        }
        Label endIndex = new Label("도착지");
        settingLabel(endIndex);
        this.endPlace = new Label("도착지데이터");
        settingLabel(this.endPlace);
        passingPlaceList.add(endIndex,0,passingPlaceCount+1);
        passingPlaceList.add(endPlace,1,passingPlaceCount+1);
        passingPlaceList.getColumnConstraints().add(new ColumnConstraints(130));
        passingPlaceList.getColumnConstraints().add(new ColumnConstraints(130));
        passingPlaceList.setMinWidth(GuiSizeMetaData.MAPVIEW_WIDTH-10);
        ScrollPane passingPane = new ScrollPane();
        passingPlaceList.setGridLinesVisible(true);
        passingPane.setContent(passingPlaceList);
        passingPane.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH-10,GuiSizeMetaData.MAPVIEW_HEIGHT/2);

        HBox seperator = new HBox();
        seperator.getStyleClass().add("seperator");
        seperator.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH+5,3);

        HBox sequence = new HBox();
        sequence.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH,50);
        Button paperPlane = new Button();
        paperPlane.getStyleClass().add("paperPlane");
        Label sequenceText = new Label("순서");
        settingLabel(sequenceText);
        sequence.getChildren().addAll(paperPlane,sequenceText);
        sequence.setAlignment(Pos.CENTER);


        VBox contentsbottom = new VBox();
        contentsbottom.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH-10,30);
        this.save = new Button("저장");
        this.save.setMinSize(100,25);
        this.save.getStyleClass().add("popupButton");
        contentsbottom.getChildren().add(this.save);
        contentsbottom.setAlignment(Pos.CENTER);
        contents.getChildren().addAll(buttonbox,seperator,predictData,sequence,passingPane,contentsbottom);
        contents.setMargin(sequence,new Insets(0,0,20,0));
        this.setRight(contents);
        this.setLeft(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH, GuiSizeMetaData.MAPVIEW_HEIGHT));
    }

    private void settingLabel(Label label){
        label.setMaxWidth(Double.MAX_VALUE);
        label.setStyle("-fx-font-size: 30;");
        label.setAlignment(Pos.CENTER);
    }
}
