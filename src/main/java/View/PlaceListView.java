package View;

import MetaData.PlaceMetaData;
import Model.Place;
import javafx.scene.layout.VBox;

import java.util.Vector;

public class PlaceListView extends VBox {
    Vector<PlaceListFragment> placeListFragments;

    public PlaceListView() {
        getStyleClass().add("placeListVIew");
        this.placeListFragments = new Vector<>();
        listInitialize();
        setSpacing(10);
    }

    private void listInitialize() {
        String[] place_types = PlaceMetaData.CULTURE_PLACE_TYPES;
        MainViewContainer mainViewContainer = MainViewContainer.getInstance();

        for(String placeType : place_types){
            PlaceListFragment placeListFragment = new PlaceListFragment(placeType);
            for(int i = 0 ; i < 10 ; i++){
                placeListFragment.addPlaceListItem(new PlaceListItemFragment(new Place("가나다라마바사",1.0,1.0,"1")));
            }
            mainViewContainer.addPlaceListFragment(placeListFragment);
            addPlaceListFragment(placeListFragment);
        }
    }


    public void addPlaceListFragment(PlaceListFragment placeListFragment){
        placeListFragments.add(placeListFragment);
        getChildren().add(placeListFragment);
    }

    public void removePlaceListFragment(PlaceListFragment placeListFragment) {
        placeListFragments.remove(placeListFragment);
        getChildren().remove(placeListFragment);
    }
}
