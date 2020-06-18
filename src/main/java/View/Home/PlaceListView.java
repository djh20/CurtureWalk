package View.Home;

import MetaData.PlaceMetaData;
import Model.Place;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Vector;

public class PlaceListView extends VBox {
    private Vector<PlaceListFragment> placeListFragments;
    private Vector<Place> selectedPlaces;

    public PlaceListView() {
        selectedPlaces = new Vector<>();
        getStyleClass().add("placeListVIew");
        this.placeListFragments = new Vector<>();
        ControlPanel controlPanel = new ControlPanel(placeListFragments);
        getChildren().add(controlPanel);
        listInitialize();
        setSpacing(10);
        MainViewContainer.getInstance().setPlaceListView(this);
        MainViewContainer.getInstance().setControlPanel(controlPanel);
    }

    private void listInitialize() {
        String[] place_types = PlaceMetaData.CULTURE_PLACE_TYPES;
        for(String placeType : place_types){
            PlaceListFragment placeListFragment = new PlaceListFragment(placeType);
            addPlaceListFragment(placeListFragment);
        }
    }


    public void addPlaceListFragment(PlaceListFragment placeListFragment){
        placeListFragments.add(placeListFragment);
        getChildren().add(placeListFragment);
    }

    public void addPlaceItems(Place[] places){
        resetPlaceListItems();
        for (Place place : places){
            findListFragment(place).addPlaceListItem(place);
        }
    }
    public void resetPlaceListItems(){
        for(PlaceListFragment placeListFragment : placeListFragments){
            placeListFragment.reset();
        }
    }


    private PlaceListFragment findListFragment(Place place){
        for(PlaceListFragment placeListFragment : placeListFragments){
            if (placeListFragment.getLabel().getText().equals(place.getType()))
                return placeListFragment;
        }
        return null;
    }

}

class ControlPanel extends HBox {
    private CheckBox seeMarkerCondition;
    private Label seeMarkerConditionLabel;

    public ControlPanel(Vector<PlaceListFragment> placeListFragments) {
        setSpacing(5);
        seeMarkerCondition = new CheckBox();
        seeMarkerConditionLabel = new Label("선택된 장소만 지도에 표시");
        getChildren().add(seeMarkerConditionLabel);
        getChildren().add(seeMarkerCondition);

        seeMarkerCondition.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(aBoolean == false){
                    MainViewContainer.getInstance().getMapView().getMapManager().removeAllPlaceMarkers();
                    Vector<Place> stopOvers = SearchConditions.getInstance().getStopOvers();
                    for(Place stopOver : stopOvers){
                        MainViewContainer.getInstance().getMapView().getMapManager().addMarker(stopOver);
                    }
                }
                else{
                    MainViewContainer.getInstance().getMapView().getMapManager().removeAllPlaceMarkers();
                    for(PlaceListFragment placeListFragment :placeListFragments){
                        for(PlaceListItemFragment placeListItemFragment : placeListFragment.getPlaceListItemFragments()){
                            MainViewContainer.getInstance().getMapView().getMapManager().addMarker(placeListItemFragment.getPlace());
                        }
                    }
                }
            }
        });
    }

    public boolean isChecked(){
        return seeMarkerCondition.isSelected();
    }

}
