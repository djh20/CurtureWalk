package View.Map;

import Controller.MapManager;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

public class MapView extends Pane {
    private WebView webView = null;
    private MapManager mapManager = null;
    public MapView(double width, double height, String url) {
        webView = new WebView();
        setMinSize(width, height);
        setMaxSize(width, height);
        webView.setMinSize(width, height);
        webView.setMaxSize(width, height);
        getChildren().add(webView);
        mapManager = new MapManager(webView, url);
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public MapManager getMapManager() {
        System.out.println("Dasd");
        return mapManager;
    }

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }
}
