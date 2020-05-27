package View;

import Controller.MapManager;

import java.util.Vector;

public class MainViewContainer {
    private static MainViewContainer viewContainer = null;
    private SearchView searchView;
    private MapManager mapManager;
    private Vector<PlaceListFragment> placeListFragments;
    private AutoCompleteTextField startPlace;
    private AutoCompleteTextField goalPlace;
    private Vector<PlaceListItemFragment> checkedPlaceListItems;

    public void addCheckPlaceListItem(PlaceListItemFragment placeListItemFragment){
        checkedPlaceListItems.add(placeListItemFragment);
    }
    public AutoCompleteTextField getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(AutoCompleteTextField startPlace) {
        this.startPlace = startPlace;
    }

    public AutoCompleteTextField getGoalPlace() {
        return goalPlace;
    }

    public void setGoalPlace(AutoCompleteTextField goalPlace) {
        this.goalPlace = goalPlace;
    }

    private MainViewContainer() {
        placeListFragments = new Vector<>();
        checkedPlaceListItems = new Vector<>();
    }

    public static MainViewContainer getInstance(){
        if(viewContainer == null)
            viewContainer = new MainViewContainer();

        return viewContainer;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public Vector<PlaceListFragment> getPlaceListFragments() {
        return placeListFragments;
    }

    public void addPlaceListFragment(PlaceListFragment placeListFragment) {
        this.placeListFragments.add(placeListFragment);
    }

    public PlaceListFragment getPlaceListFragmentByLabelName(String labelName){
        for(PlaceListFragment placeListFragment : placeListFragments){
            if(placeListFragment.getLabel().getText().equals(labelName))
                return placeListFragment;
        }
        return null;
    }

    public void removeCheckPlaceListItem(PlaceListItemFragment placeListItemFragment) {
        checkedPlaceListItems.remove(placeListItemFragment);
    }
}
