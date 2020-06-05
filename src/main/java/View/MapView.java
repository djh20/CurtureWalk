package View;

import Controller.MapManager;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

public class MapView extends Pane {
    WebView webView = null;
    MapManager mapManager = null;
    public MapView(double width, double height) {
        webView = new WebView();
        setMinSize(width, height);
        setMaxSize(width, height);
        webView.setMinSize(width, height);
        webView.setMaxSize(width, height);
        getChildren().add(webView);
        mapManager = new MapManager(webView);
    }
}
