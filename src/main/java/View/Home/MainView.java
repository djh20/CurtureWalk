package View.Home;

import MetaData.GuiMetaData;
import View.Map.MapView;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
    public MainView() throws InterruptedException {
        getStyleClass().add("mainView");
        setWidth(GuiMetaData.WINDOW_WIDTH);
        setHeight(GuiMetaData.WINDOW_HEIGHT);
        System.out.println(1);
        MapView mapView =new MapView(GuiMetaData.MAPVIEW_WIDTH, GuiMetaData.MAPVIEW_HEIGHT, GuiMetaData.mainMapURL);
        MainViewContainer.getInstance().setMapView(mapView);
        setLeft(mapView);
        Thread.sleep(2000);
        SearchView searchView = new SearchView();
        MainViewContainer.getInstance().setSearchView(searchView);
        System.out.println(2);
        setRight(searchView);
//        setRight(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH, GuiSizeMetaData.MAPVIEW_HEIGHT, "http://djh20.iptime.org/Museumpopup.html"));

    }
}
