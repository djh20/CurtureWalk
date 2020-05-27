package Controller;

import Model.Place;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MapManager {
    private WebEngine webEngine = null;
    private JSObject javascriptConnector = null;

    public MapManager(WebView webView) {
        this.webEngine = webView.getEngine();
        // connect to javascript
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", this);
                // get the Javascript connector object.
                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        webEngine.load("http://djh20.iptime.org/comunicationTest.html");

    }

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


//    public MapBoundary getMapBoundary(){
//        JSObject result = (JSObject) javascriptConnector.call("sendMapBoundary");
//        return new MapBoundary(Double.parseDouble(result.getMember("min_lat").toString()),Double.parseDouble(result.getMember("max_lat").toString()),
//                Double.parseDouble(result.getMember("min_long").toString()),Double.parseDouble(result.getMember("max_long").toString()));
//    }
//
//    public void sendPlaceInfo(mapInfo info){
//        javascriptConnector.call("receivePlaceInfo", info.address,info.lat,info.longs);
//    }
//
//    public void makeMarker(Place place){
//        javascriptConnector.call("makeMarker", place.getPlaceName(), place.getLatitude(), place.getLongitude(), place.category);
//    }

    public void send(JSObject object){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(object + "");
        alert.setContentText(object.getMember("name") + "\n" + object.getMember("number"));
        alert.show();
    }

    public void reqMarkerVisible(String category, boolean selected) { javascriptConnector.call("reqMarkerVisible", category, selected); }
    public void reqMarkerSelect(String placeName) {
        javascriptConnector.call("markerSelect", placeName);
    }
    public void reqDrawLoad() {
        javascriptConnector.call("drawLoad");
    }
    public void reqAddPath(double longitude, double latitude) { javascriptConnector.call("addPath", longitude, latitude); }
}
