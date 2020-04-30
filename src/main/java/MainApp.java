import com.mysql.cj.xdevapi.JsonArray;
import com.sun.javafx.scene.control.LabeledText;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSObject;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.script.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MainApp extends Application {
    AutoCompletionBinding autoCompletionBinding;

    private JSObject javascriptConnector;

    /** for communication from the Javascript engine. */
    private JavaConnector javaConnector = new JavaConnector();;
    Connection conn = null;
    java.sql.Statement stmt = null;
    PreparedStatement pstmt = null;
    Vector<mapInfo> results = new Vector<mapInfo>();
    Vector<Place> placeResults = new Vector<Place>();
    Vector<Place> museumAndArtGallery = new Vector<Place>();
    Vector<Place> tour = new Vector<Place>();
    Vector<Place> architecture = new Vector<Place>();
    Vector<Place> concert = new Vector<Place>();


    public static void main(String args[]) throws ScriptException, FileNotFoundException {



        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final String url = "jdbc:mysql://(host=djh20.iptime.org,port=3306,serverTimezone=UTC,allowMultiQueries=TRUE)/mydb?useUnicode=true&characterEncoding=utf8";
        Vector<Place> selected = new Vector<Place>();

        final String dbID = "djh20";
        final String dbPW = "dldd0525!@";

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,dbID ,dbPW);
        stmt = conn.createStatement();

        //
        final int width = 1000;
        final int height = 600;

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webView.setMinSize(width/2,height);
        webView.setMaxSize(width/2,height);

        HBox hbox = new HBox();
        Pane webPane = new Pane();
        Pane infoPane = new Pane();
        infoPane.setMaxSize(width/2,height);
        infoPane.setMinSize(width/2,height);
        Group group = new Group();
        hbox.getChildren().add(webPane);
        hbox.getChildren().add(infoPane);
        webPane.getChildren().add(group);
        infoPane.setStyle("-fx-background-color: #dddddd");
        Scene scene = new Scene(hbox, width, height);
        group.getChildren().add(webView);


        TextField search = new TextField();

        HBox searchFeild = new HBox();
        VBox tmpF = new VBox();

        HBox startPointField = new HBox();
        TextField startPointSearch = new TextField();
        Button startPointGps = new Button("gps");
        startPointField.getChildren().add(startPointSearch);
        startPointField.getChildren().add(startPointGps);
        HBox goalPointField = new HBox();
        TextField goalPointSearch = new TextField();
        Button goalPointGps = new Button("gps");
        goalPointField.getChildren().add(goalPointSearch);
        goalPointField.getChildren().add(goalPointGps);



        goalPointField.setMinSize(width/2,height/20);
        goalPointField.setMaxSize(width/2,height/20);

        startPointField.setMinSize(width/2,height/20);
        startPointField.setMaxSize(width/2,height/20);



        tmpF.getChildren().add(searchFeild);
        tmpF.getChildren().add(startPointField);
        tmpF.getChildren().add(goalPointField);

        searchFeild.setAlignment(Pos.CENTER);
        startPointField.setAlignment(Pos.CENTER);
        goalPointField.setAlignment(Pos.CENTER);

        searchFeild.setMinSize(width/2,height/20);
        startPointField.setMinSize(width/2,height/20);
        goalPointField.setMinSize(width/2,height/20);

        search.setMinSize(width/4, height/20);
        startPointSearch.setMinSize(width/4, height/20);
        goalPointSearch.setMinSize(width/4, height/20);

        startPointField.setSpacing(10);
        goalPointField.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinSize(width/2, height - height/20 -10);
        scrollPane.setMaxSize(width/2, height - height/20 -10);


        VBox select = new VBox();
        select.setMinWidth(width/2);
        select.setMaxWidth(width/2);
        tmpF.getChildren().add(scrollPane);
        scrollPane.setContent(select);

        VBox places = new VBox();

        searchFeild.getChildren().add(search);

        Button button = new Button("검색");
        Button load = new Button("경로탐색");
        Button reset = new Button("초기화");


        Vector<Place> museumAndArtGallery = new Vector<Place>();
        Vector<Place> tour = new Vector<Place>();
        Vector<Place> architecture = new Vector<Place>();
        Vector<Place> concert = new Vector<Place>();

        Label museumAndArtGallery_label = new Label("박물관 / 미술관");
        museumAndArtGallery_label.setStyle("-fx-font-size: 15px");
        museumAndArtGallery_label.setMinWidth(width/2);
        museumAndArtGallery_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        Label tour_label = new Label("관광지");
        tour_label.setStyle("-fx-font-size: 15px");
        tour_label.setMinWidth(width/2);
        tour_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        Label architecture_label = new Label("건축미술");
        architecture_label.setStyle("-fx-font-size: 15px");
        architecture_label.setMinWidth(width/2);
        architecture_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        Label concert_label = new Label("공연장");
        concert_label.setStyle("-fx-font-size: 15px");
        concert_label.setMinWidth(width/2);
        concert_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        VBox museumAndArtGallery_box = new VBox();
        museumAndArtGallery_box.setAlignment(Pos.CENTER_RIGHT);
        museumAndArtGallery_box.getChildren().add(museumAndArtGallery_label);
        VBox tour_box = new VBox();
        tour_box.setAlignment(Pos.CENTER_RIGHT);
        tour_box.getChildren().add(tour_label);
        VBox architecture_box = new VBox();
        architecture_box.setAlignment(Pos.CENTER_RIGHT);
        architecture_box.getChildren().add(architecture_label);
        VBox consert_box = new VBox();
        consert_box.setAlignment(Pos.CENTER_RIGHT);
        consert_box.getChildren().add(concert_label);

        HBox markerCondition = new HBox();
        markerCondition.setAlignment(Pos.BASELINE_CENTER);
        markerCondition.setSpacing(10);
        markerCondition.setMinWidth(width/2);
        markerCondition.setMaxWidth(width/2);
        CheckBox museumAndArtGallery_checkBox = new CheckBox("박물관/미술관");
        museumAndArtGallery_checkBox.setSelected(true);
        CheckBox tour_checkBox = new CheckBox("관광지");
        tour_checkBox.setSelected(true);
        CheckBox architecture_checkBox = new CheckBox("건축미술");
        architecture_checkBox.setSelected(true);
        CheckBox consert_checkBox = new CheckBox("공연장");
        consert_checkBox.setSelected(true);
        markerCondition.getChildren().add(museumAndArtGallery_checkBox);
        markerCondition.getChildren().add(tour_checkBox);
        markerCondition.getChildren().add(architecture_checkBox);
        markerCondition.getChildren().add(consert_checkBox);

        places.getChildren().add(markerCondition);
        places.getChildren().add(museumAndArtGallery_box);
        places.getChildren().add(tour_box);
        places.getChildren().add(architecture_box);
        places.getChildren().add(consert_box);
        select.setSpacing(5);
        places.setSpacing(5);
        museumAndArtGallery_box.setSpacing(5);
        tour_box.setSpacing(5);
        architecture_box.setSpacing(5);
        consert_box.setSpacing(5);


        EventHandler<ActionEvent> checkBoxEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CheckBox target = (CheckBox) actionEvent.getTarget();
                System.out.println(target.isSelected());
                javaConnector.reqMarkerVisible(target.getText(), target.isSelected());
            }
        };
        museumAndArtGallery_checkBox.setOnAction(checkBoxEventHandler);
        tour_checkBox.setOnAction(checkBoxEventHandler);
        consert_checkBox.setOnAction(checkBoxEventHandler);
        architecture_checkBox.setOnAction(checkBoxEventHandler);

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                webEngine.reload();
                scrollPane.setContent(select);
                search.setText("");
                museumAndArtGallery_checkBox.setSelected(true);
                tour_checkBox.setSelected(true);
                consert_checkBox.setSelected(true);
                architecture_checkBox.setSelected(true);

            }
        });

        EventHandler<ActionEvent> placeCheck = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Place target = (Place) actionEvent.getTarget();
                selected.add(target);
                System.out.println(target.placeName);
                javaConnector.reqMarkerSelect(target.placeName);
            }
        };


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // initial

                    placeResults.clear();
                    museumAndArtGallery_box.getChildren().clear();
                    museumAndArtGallery_box.getChildren().add(museumAndArtGallery_label);
                    tour_box.getChildren().clear();
                    tour_box.getChildren().add(tour_label);
                    architecture_box.getChildren().clear();
                    architecture_box.getChildren().add(architecture_label);
                    consert_box.getChildren().clear();
                    consert_box.getChildren().add(concert_label);
                    javaConnector.reqClearMarkers();

                    //
                    MapBoundary mapBoundary = javaConnector.getMapBoundary();
                    String query = "SELECT * FROM mydb.place where " + mapBoundary.min_lat + " < lat and lat < " + mapBoundary.max_lat + " and " +
                            mapBoundary.min_long + "< longit and longit < " + mapBoundary.max_long + " and category != '주소'";
                    ResultSet resultSet = stmt.executeQuery(query);
                    String categoryData[] = {"건축미술" , "공연장", "관광지", "박물관/미술관"};
                    while (resultSet.next()) {
                        String placeName = resultSet.getString("placeName");
                        String category = resultSet.getString("category");;
                        String lat = resultSet.getString("lat");
                        String longs = resultSet.getString("longit");
                        Place place = new Place(placeName, Double.parseDouble(lat), Double.parseDouble(longs),category);
                        place.setOnAction(placeCheck);
                        System.out.println(category);
                        place.setMinWidth(width/2 - 20);
                        place.setMaxWidth(width/2 - 20);
                        placeResults.add(place);
                        javaConnector.makeMarker(place);
                        if (category.equals(categoryData[0])) {
                            architecture.add(place);
                            architecture_box.getChildren().add(place);
                        }
                        else if (category.equals(categoryData[1])){
                            concert.add(place);
                            consert_box.getChildren().add(place);}
                        else if (category.equals(categoryData[2])){
                            tour.add(place);
                            tour_box.getChildren().add(place);}
                        else if (category.equals(categoryData[3])){
                            museumAndArtGallery.add(place);
                            museumAndArtGallery_box.getChildren().add(place);}
                    }
                    scrollPane.setContent(places);
                }

                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
        final String searchList = "도시군구읍면동리";


        ChangeListener<String> mapSearchListener =  new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    if (scrollPane.getContent() != select)
                        scrollPane.setContent(select);
                    select.getChildren().clear();
                    if (search.getText().length() > 1) {
                        results.clear();
                        String inputValue = "'*" + search.getText() + "*'";
                        String query = "select * from mydb.place where match(placeName) against(+" + inputValue + ") limit 30 ";
                        ResultSet resultSet = stmt.executeQuery(query);
                        while (resultSet.next()) {
                            String result = resultSet.getString("placeName");
                            String lat = resultSet.getString("lat");
                            String longs = resultSet.getString("longit");
                            results.add(new mapInfo(result, Double.parseDouble(lat), Double.parseDouble(longs)));
                            System.out.println("dd");
                        }

                        for (int i = 0; i < results.size(); i++) {
                            Label label = new Label(results.get(i).address);
                            label.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #696969;");
                            label.setStyle("-fx-border-color: black;-fx-border-width: 0 0 1px 0");
                            label.setMinWidth(width / 2);
                            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                                        LabeledText thisLabel = (LabeledText) mouseEvent.getTarget();
                                        mapInfo info = null;

                                        for (int i = 0; i < results.size(); i++) {
                                            if (results.get(i).address.equals(thisLabel.getText()))
                                                info = results.get(i);
                                        }
                                        System.out.println(info.address);
                                        javaConnector.sendPlaceInfo(info);
                                    }
                                }
                            });
                            select.getChildren().add(label);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        search.textProperty().addListener(mapSearchListener);
        startPointSearch.textProperty().addListener(mapSearchListener);
        goalPointSearch.textProperty().addListener(mapSearchListener);



        startPointGps.setMinSize(width/15, height/20);
        goalPointGps.setMinSize(width/15, height/20);

        button.setMinSize(width/15, height/20);
        reset.setMinSize(width/15, height/20);
        load.setMinSize(width/15, height/20);
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Vector<Edge> edges = new Vector<Edge>();
                Vector<Vertax> vertaxes = new Vector<Vertax>();
                for(int i = 0 ; i < selected.size() ; i++){
                    Place place = selected.get(i);
                        edges.add(new Edge(place));
                }

                for(int i = 0; i < edges.size() ; i++){
                    for (int j = 0 ; j <edges.size() ; j++){
                        if(i>j){
                            System.out.println("vertax 추가!");
                            Edge edge1 = edges.get(i);
                            Edge edge2 = edges.get(j);
                            NaviInfo result = RouteNavigation.getRoute(edges.get(i).place.longit+","+edges.get(i).place.lat, edges.get(j).place.longit+","+edges.get(j).place.lat);
                            Vertax vertax1 = new Vertax(edge1,edge2,result.distance, result.points);
                            Vertax vertax2 = new Vertax(edge2,edge1, result.distance,result.points);
                            edge1.addVertax(vertax1);
                            edge2.addVertax(vertax2);
                            vertaxes.add(vertax1);
                            vertaxes.add(vertax2);

                        }
                    }
                }
                Vector<Edge> path = RouteNavigation.getShortestPath(edges,0);
                for(int i = 0 ; i < path.size() ; i++){

                    System.out.println(path.get(i).place.placeName +"123");
                }
                for(int i = 0 ; i < path.size()-1 ; i++){
                    for(int j = 0 ; j < vertaxes.size() ; j++){
                        Vertax vertax = vertaxes.get(j);
                        if(vertax.start == path.get(i) && vertax.target == path.get(i+1)){
                            System.out.println("출발지 : " + vertax.start.place.placeName + " 도착지 : " + vertax.target.place.placeName);
                            for(int z = 0 ; z  < vertax.points.size() ; z++) {
                                javaConnector.reqAddPath(vertax.points.get(z).longit, vertax.points.get(z).lat);
                            }
                        }
                        javaConnector.reqDrawLoad();
                    }
                }






            }
        });
        searchFeild.setSpacing(10);

        searchFeild.getChildren().add(button);
        searchFeild.getChildren().add(load);
        searchFeild.getChildren().add(reset);
        infoPane.getChildren().add(tmpF);

        primaryStage.setScene(scene);

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);
                // get the Javascript connector object.
                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        webEngine.load("http://djh20.iptime.org/comunicationTest.html");

        primaryStage.show();
    }

    public class MapBoundary{
        double min_lat;
        double max_lat;
        double min_long;
        double max_long;

        public MapBoundary(double min_lat, double max_lat, double min_long, double max_long) {
            this.min_lat = min_lat;
            this.max_lat = max_lat;
            this.min_long = min_long;
            this.max_long = max_long;
        }
    }
    public class JavaConnector {
        /**
         * called when the JS side wants a String to be converted.
         *
         * @param value
         *         the String to convert
         */
        public void toLowerCase(String value) {
            if (null != value) {
                javascriptConnector.call("showResult", value.toLowerCase());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.show();
            }
        }

        public void reqClearMarkers(){
            javascriptConnector.call("clearMarkers");
        }
        public MapBoundary getMapBoundary(){
            JSObject result = (JSObject) javascriptConnector.call("sendMapBoundary");
            return new MapBoundary(Double.parseDouble(result.getMember("min_lat").toString()),Double.parseDouble(result.getMember("max_lat").toString()),
                    Double.parseDouble(result.getMember("min_long").toString()),Double.parseDouble(result.getMember("max_long").toString()));
        }

        public void sendPlaceInfo(mapInfo info){
            javascriptConnector.call("receivePlaceInfo", info.address,info.lat,info.longs);
        }

        public void makeMarker(Place place){
            javascriptConnector.call("makeMarker", place.placeName, place.lat, place.longit, place.category);
        }

        public void sendData(){
//            class tmp{
//                String name;
//                int number;
//
//                public tmp(String name, int number) {
//                    this.name = name;
//                    this.number = number;
//                }
//            };
            javascriptConnector.call("receiveData", "asdad".toLowerCase());
        }


        public qwe request(){
            return new qwe("hello", 81);
        }

        public void send(JSObject object){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(object + "");
            alert.setContentText(object.getMember("name") + "\n" + object.getMember("number"));
            alert.show();
        }

        public void reqMarkerVisible(String category, boolean selected) {
            System.out.println("암ㄴ인망ㅁ인ㅁㅁㄴㅇㅁㄴㅇㅁㄴㅇㄴㅁ");
                    javascriptConnector.call("reqMarkerVisible", category, selected);
        }

        public void reqMarkerSelect(String placeName) {
            javascriptConnector.call("markerSelect", placeName);
        }

        public void reqDrawLoad() {
            javascriptConnector.call("drawLoad");
        }

        public void reqAddPath(double longit, double lat) {
            javascriptConnector.call("addPath", longit, lat);
        }
    }
}

class qwe{
    String name;
    int number;

    public qwe(String name, int number) {
        this.name = name;
        this.number = number;
    }
}


class mapInfo{
    String address;
    double lat;
    double longs;

    public mapInfo(String address, double lat, double longs) {
        this.address = address;
        this.lat = lat;
        this.longs = longs;
    }
}

class Place extends CheckBox{
    String placeName;
    double lat;
    double longit;
    String category;

    public Place(String placeName, double lat, double longit, String category) {
        super.setText(placeName);
        this.placeName = placeName;
        this.lat = lat;
        this.longit = longit;
        this.category = category;
    }
}