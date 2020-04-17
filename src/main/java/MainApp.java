import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import com.sun.javafx.scene.control.LabeledText;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.internal.jline.WindowsTerminal;
import jdk.internal.netscape.javascript.spi.JSObjectProvider;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.controlsfx.*;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import sun.jvm.hotspot.utilities.soql.JSJavaObject;

import javax.script.*;
import javax.swing.*;
import java.applet.Applet;
import java.io.FileNotFoundException;
import java.io.FileReader;


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


    public static void main(String args[]) throws ScriptException, FileNotFoundException {

        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        final String url = "jdbc:mysql://(host=tortue.ml,port=3306,serverTimezone=UTC,allowMultiQueries=TRUE)/mydb?useUnicode=true&characterEncoding=utf8";


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
        tmpF.getChildren().add(searchFeild);

        searchFeild.setAlignment(Pos.CENTER);

        searchFeild.setMinSize(width/2,height/20);
        searchFeild.setMaxSize(width/2,height/20);

        search.setMinSize(width/4, height/20);
        search.setMaxSize(width/4, height/20);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinSize(width/2, height - height/20 -10);
        scrollPane.setMaxSize(width/2, height - height/20 -10);


        VBox select = new VBox();
        select.setMinWidth(width/2);
        select.setMaxWidth(width/2);
        tmpF.getChildren().add(scrollPane);
        scrollPane.setContent(select);



        searchFeild.getChildren().add(search);

        Button button = new Button("Search");
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    select.getChildren().clear();
//                    results.clear();
//                    String inputValue = "'" + search.getText() + "'";
//                    String query = "select * from mydb.loadInfo where match(address) against(+ " + inputValue + ") limit 100";
//                    ResultSet resultSet = stmt.executeQuery(query);
//                    while (resultSet.next()) {
//                        String result = resultSet.getString("address");
//                        String lat = resultSet.getString("lat");
//                        String longs = resultSet.getString("long");
//                        results.add(new mapInfo(result, Double.parseDouble(lat), Double.parseDouble(longs)));
//                        System.out.println("dd");
//                    }
//
//                    for(int i = 0 ; i < results.size(); i++){
//                        Label label = new Label(results.get(i).address);
////                        label.setStyle("-fx-background-color: black");
////                        label.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #aaaaaa;");
//                        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                            @Override
//                            public void handle(MouseEvent mouseEvent) {
//                                if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
//                                    LabeledText thisLabel = (LabeledText) mouseEvent.getTarget();
//                                    mapInfo info = null;
//
//                                    for (int i = 0; i < results.size(); i++) {
//                                        if (results.get(i).address.equals(thisLabel.getText()))
//                                            info = results.get(i);
//                                    }
//                                    System.out.println(info.address);
//                                    javaConnector.sendPlaceInfo(info);
//
//
//                                }
//                            }
//                        });
//                        select.getChildren().add(label);
//                    }
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
        final String searchList = "도시군구읍면동리";
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    select.getChildren().clear();
                    if(search.getText().length() > 0 && searchList.contains(search.getText().charAt(search.getText().length() -1) + "")) {
                        results.clear();
                        String inputValue = "'*" + search.getText() + "*'";
                        String query = "select * from mydb.loadInfo where match(address) against(+" + inputValue + ") limit 30 ";
                        ResultSet resultSet = stmt.executeQuery(query);
                        while (resultSet.next()) {
                            String result = resultSet.getString("address");
                            String lat = resultSet.getString("lat");
                            String longs = resultSet.getString("long");
                            results.add(new mapInfo(result, Double.parseDouble(lat), Double.parseDouble(longs)));
                            System.out.println("dd");
                        }

                        for (int i = 0; i < results.size(); i++) {
                            Label label = new Label(results.get(i).address);
//                        label.setStyle("-fx-background-color: black");
//                        label.setStyle("-fx-normal-background: #101010; -fx-hovered-background: #aaaaaa;");
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
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });







        button.setMinSize(width/10, height/20);
        searchFeild.setSpacing(10);

        searchFeild.getChildren().add(button);
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



//
//            Set<String> autoComplete = new HashSet<>();
//            SuggestionProvider<String> provider = SuggestionProvider.create(autoComplete);
//            autoCompletionBinding = new AutoCompletionTextFieldBinding<>(search, provider);
//
//
//                search.textProperty().addListener(new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                        try {
//
//
//                                autoComplete.clear();
//                                System.out.println("입장");
//                                // 정규식 이용, 느림
////                                String inputValue = "'%" + search.getText() + "%'";
////                                String query = "select * from mydb.loadInfo where address like " + inputValue + " limit 5";
//                                //
//                                // 빠름.
//                                String inputValue = "'" + search.getText() + "'";
//                                String query = "select * from mydb.loadInfo where match(address) against(" + inputValue + ") limit 5";
//                                ResultSet resultSet = stmt.executeQuery(query);
//                                while (resultSet.next()) {
//                                    String result = resultSet.getString("address");
//                                    autoComplete.add(result);
//                                    System.out.println("exe");
//                                }
//                                System.out.println(query);
//
//
//                                Platform.runLater(() -> {
//                                    search.deselect();
//                                    search.end();
//
//                                    provider.clearSuggestions();
//                                    provider.addPossibleSuggestions(autoComplete);
////                                    autoCompletionBinding = new AutoCompletionTextFieldBinding<>(search, provider);
//
//                                    search.getParent().layout();
//                                });
//
//                        }
//                        catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                });




        webEngine.load("http://tortue.ml/comunicationTest.html");

        primaryStage.show();
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

        public void sendPlaceInfo(mapInfo info){
            javascriptConnector.call("receivePlaceInfo", info.address,info.lat,info.longs);
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