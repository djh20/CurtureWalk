package Controller;

import Model.*;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.Vector;

public class MapManager {
    private WebEngine webEngine = null;
    private JSObject javascriptConnector = null;

     public  MapManager(WebView webView, String url) {
        this.webEngine = webView.getEngine();
         webEngine.load(url);
        javascriptConnector = (JSObject) webEngine.executeScript("window");
    }

    public MapBoundary getMapBoundary(){
        JSObject result = (JSObject) javascriptConnector.call("sendMapBoundary");
        return new MapBoundary(Double.parseDouble(result.getMember("min_lat").toString()), Double.parseDouble(result.getMember("max_lat").toString()),
                Double.parseDouble(result.getMember("min_long").toString()), Double.parseDouble(result.getMember("max_long").toString()), Integer.parseInt(result.getMember("zoom").toString()));
    }
    public void setMapCenter(Place place){
         try {
             javascriptConnector.call("setMapCenter", place.getLatitude(), place.getLongitude());
         }
         catch (Exception e){
             e.printStackTrace();
         }
    }

    public void setMapCenter(Place place,int zoom){
        try {
            javascriptConnector.call("setMapCenterWithZoom", place.getLatitude(), place.getLongitude(), zoom);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setMapCenter(MapBoundary mapBoundary){
        try {
            javascriptConnector.call("setMapCenterWithZoom", (mapBoundary.getMax_lat() + mapBoundary.getMin_lat())/2,
                    (mapBoundary.getMax_long()+mapBoundary.getMin_long())/2, mapBoundary.getZoom()-1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reqAddPath(double longitude, double latitude) { javascriptConnector.call("addPath", longitude, latitude); }

    public void reqAddPath(Path path, int index) {
        System.out.println("추가 " + index);
         Vector<Point> points = path.getPoints();
         for(Point point : points){
             javascriptConnector.call("addPath", point.getLongit(), point.getLat());
         }
        javascriptConnector.call("drawPath", path.getTrafficType(), index);
     }
     public void removePath(int index){
         System.out.println("삭제" + index);
         javascriptConnector.call("removePath", index);
     }

    public void removeAllMarkers(){
        javascriptConnector.call("removeAllMarkers");
    }

    public void addMarkers(Place[] places) {
        removeAllMarkers();
        for(Place place : places){
            javascriptConnector.call("addMarker", place.getName(), place.getLatitude(), place.getLongitude(), place.getType());
        }
    }

    public void addMarker(Place place) {
        javascriptConnector.call("addMarker", place.getName(), place.getLatitude(), place.getLongitude(), place.getType());
    }

    public void setMarkerSelected(Place place) {
        javascriptConnector.call("setMarkerSelected", place.getName(), place.getType());
    }

    public void setMarkerUnSelected(Place place) {
        javascriptConnector.call("setMarkerUnSelected", place.getName(), place.getType());
    }

    public void showMarkerName(Place place){
        javascriptConnector.call("showMarkerName", place.getName(), place.getType());
    }

    public void drawPath(ShortestPathSearchResult shortestPath) {
        Vector<Vertex> orderedVertexes = shortestPath.getOrderedNode();
        for(Vertex vertex : orderedVertexes){
            Vector<Path> paths = vertex.getRoute().getPathes();
            for(Path path : paths) {
                for (Point point : path.getPoints()) {
                    reqAddPath(point.getLongit(), point.getLat());
                }
                javascriptConnector.call("drawLoad", path.getTrafficType());
            }
        }

    }

    public void clearPaths() {
        javascriptConnector.call("clearPaths");
    }

    public void addStartMarker(Place place) {
        System.out.println("시작마커");
        javascriptConnector.call("addStartMarker", place.getName(),place.getLatitude(),place.getLongitude(),place.getType());
    }

    public void addNumberMarker(Place place , int index) {
        System.out.println("중간마커");
        javascriptConnector.call("addNumberMarker", place.getName(),place.getLatitude(),place.getLongitude(),place.getType(), index);
    }

    public void addGoalMarker(Place place) {
        System.out.println("도착지마커");
        javascriptConnector.call("addGoalMarker", place.getName(),place.getLatitude(),place.getLongitude(),place.getType());
    }

    public void setMapCenterWthoutZoom(Place place) {
        javascriptConnector.call("setMapCenterWithoutZoom", place.getLatitude(), place.getLongitude());
    }

    public void removeAllPlaceMarkers() {
        javascriptConnector.call("removeAllPlaceMarkers");
    }
}
