package View;

import MetaData.GuiSizeMetaData;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
    public MainView() {
        getStyleClass().add("mainView");
        setWidth(GuiSizeMetaData.WINDOW_WIDTH);
        setHeight(GuiSizeMetaData.WINDOW_HEIGHT);
        setLeft(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH, GuiSizeMetaData.MAPVIEW_HEIGHT));
        setRight(new SearchView());
    }
}
