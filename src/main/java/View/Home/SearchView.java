package View.Home;

import MetaData.GuiMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SearchView extends VBox {
    public SearchView() {
        System.out.println("??");
        setAlignment(Pos.CENTER);
        setMinSize(GuiMetaData.SEARCHVIEW_WIDTH, GuiMetaData.SEARCHVIEW_HEIGHT);
        setMaxSize(GuiMetaData.SEARCHVIEW_WIDTH, GuiMetaData.SEARCHVIEW_HEIGHT);
        getChildren().add(new SearchFragment());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(GuiMetaData.PLACELISTVIEW_WIDTH, GuiMetaData.PLACELISTVIEW_HEIGHT);
        scrollPane.setMinSize(GuiMetaData.PLACELISTVIEW_WIDTH, GuiMetaData.PLACELISTVIEW_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(new PlaceListView());
        getChildren().add(scrollPane);
        getChildren().add(new StartGoalView());
        getStyleClass().add("searchView");
        setSpacing(10);
    }
}
