package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SearchView extends VBox {
    public SearchView() {
        setAlignment(Pos.CENTER);
        setMinSize(GuiSizeMetaData.SEARCHVIEW_WIDTH,GuiSizeMetaData.SEARCHVIEW_HEIGHT);
        setMaxSize(GuiSizeMetaData.SEARCHVIEW_WIDTH,GuiSizeMetaData.SEARCHVIEW_HEIGHT);
        getChildren().add(new SearchFragment());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(GuiSizeMetaData.PLACELISTVIEW_WIDTH,GuiSizeMetaData.PLACELISTVIEW_HEIGHT);
        scrollPane.setMinSize(GuiSizeMetaData.PLACELISTVIEW_WIDTH,GuiSizeMetaData.PLACELISTVIEW_HEIGHT);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStyleClass().add("scrollPane");
        scrollPane.setContent(new PlaceListView());
        getChildren().add(scrollPane);
        getChildren().add(new StartGoalView());
        getStyleClass().add("searchView");
        setSpacing(10);
    }
}
