package View.Home;

import MetaData.GuiMetaData;
import MetaData.Protocol;
import Model.MapBoundary;
import Model.Place;
import Network.DataManager;
import Network.Packet;
import View.CustomAlert;
import impl.org.controlsfx.skin.AutoCompletePopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SearchFragment extends BorderPane {
    private AutoCompleteTextField placeSearch;
    private Button search;
    private Button reset;
    private Button mapsearch;

    public SearchFragment() {
        HBox buttonBox = new HBox();
        setStyle("-fx-padding: 0 10 0 10");
        placeSearch = new AutoCompleteTextField(GuiMetaData.WINDOW_WIDTH/4, GuiMetaData.SEARCHFRAGMENT_HEIGHT);
        //TODO 버튼 이미지화
        mapsearch = new Button();
        mapsearch.getStyleClass().add("mapsearch");
        search = new Button();
        search.getStyleClass().add("search");
        reset= new Button();
        setOnActionResetButton();
        setOnActionSearchButton();
        setOnActionMapSearchButton();
        setMinSize(GuiMetaData.SEARCHFRAGMENT_WIDTH, GuiMetaData.SEARCHFRAGMENT_HEIGHT);
        setMaxSize(GuiMetaData.SEARCHFRAGMENT_WIDTH, GuiMetaData.SEARCHFRAGMENT_HEIGHT);

        search.setMinSize(GuiMetaData.WINDOW_WIDTH/16, GuiMetaData.SEARCHFRAGMENT_HEIGHT);
        search.setMaxSize(GuiMetaData.WINDOW_WIDTH/16, GuiMetaData.SEARCHFRAGMENT_HEIGHT);
        reset.setMinSize(GuiMetaData.WINDOW_WIDTH/16, GuiMetaData.SEARCHFRAGMENT_HEIGHT);
        reset.setMaxSize(GuiMetaData.WINDOW_WIDTH/16, GuiMetaData.SEARCHFRAGMENT_HEIGHT);
        placeSearch.setMinWidth(350);
        reset.getStyleClass().add("reset");
        setLeft(placeSearch);
        buttonBox.getChildren().addAll(mapsearch,search,reset);
        setListenerPlaceSearch();
        setRight(buttonBox);
        buttonBox.setSpacing(10);
    }


    private void setListenerPlaceSearch() {
        placeSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if(placeSearch.getText().length() > 0) {
                placeSearch.setSelectedPlace(null);
                placeSearch.getAutoCompletePopup().hide();
                placeSearch.getAutoCompletePopup().setMinWidth(placeSearch.getMinWidth());
                String text[] = {placeSearch.getText()};
                Packet<Place> receive = DataManager.getInstance().requestData(new Packet(Protocol.REQ_PLACELIST_MATCH_NAME, text));
                Place[] response = receive.getData();
                if(response.length > 0) {
                    placeSearch.getAutoCompletePopup().getSuggestions().clear();
                    placeSearch.getAutoCompletePopup().getSuggestions().addAll(response);
                    placeSearch.getAutoCompletePopup().show(placeSearch);
                }
                placeSearch.getAutoCompletePopup().setOnSuggestion(new EventHandler<AutoCompletePopup.SuggestionEvent>() {
                    @Override
                    public void handle(AutoCompletePopup.SuggestionEvent suggestionEvent) {
                        Place selected = ((Place) suggestionEvent.getSuggestion());
                        placeSearch.setText(selected.getName());
                        placeSearch.setSelectedPlace(selected);
                        placeSearch.getAutoCompletePopup().getSuggestions().clear();
                        placeSearch.getAutoCompletePopup().hide();
                    }
                });
            }
        });
    }
    //TODO setOnAction 등록하기
    private void setOnActionSearchButton(){
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MapBoundary mapBoundary[] = {MainViewContainer.getInstance().getMapView().getMapManager().getMapBoundary()};
                if(mapBoundary[0].getZoom() < 11)
                    new CustomAlert("범위가 너무 넓습니다");
                else {
                    MainViewContainer.getInstance().resetToSearch();
                    SearchConditions.getInstance().reset();

                    Packet<Place> result = DataManager.getInstance().requestData(new Packet<MapBoundary>(Protocol.REQ_PLACELIST_IN_AREA, mapBoundary));
                    MainViewContainer.getInstance().getMapView().getMapManager().addMarkers(result.getData());
                    MainViewContainer.getInstance().getPlaceListView().addPlaceItems(result.getData());
                }
            }
        });
    }

    private void setOnActionMapSearchButton(){
        mapsearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainViewContainer.getInstance().resetToSearch();
                SearchConditions.getInstance().reset();
                Place selected = placeSearch.getSelectedPlace();
                if(selected != null)
                    MainViewContainer.getInstance().getMapView().getMapManager().setMapCenter(selected);
                else
                    new CustomAlert("자동완성 기능을 통해 지정해주세요");
            }
        });
    }

    private void setOnActionResetButton(){
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainViewContainer.getInstance().resetToSearch();
                SearchConditions.getInstance().reset();
            }
        });
    }
}
