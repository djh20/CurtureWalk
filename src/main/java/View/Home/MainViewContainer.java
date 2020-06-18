package View.Home;

import MetaData.Protocol;
import Model.MapBoundary;
import Model.Place;
import View.Map.MapView;
import View.Popup.InfoPopup.*;

import java.util.HashMap;
import java.util.Vector;

public class MainViewContainer {

    private SearchView searchView;
    private MapView mapView;
    private AutoCompleteTextField startPlace ;
    private AutoCompleteTextField goalPlace ;
    private PlaceListView placeListView;
    private ControlPanel controlPanel;

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void resetToSearch(){
        startPlace.setText("");
        startPlace.setSelectedPlace(null);
        goalPlace.setText("");
        goalPlace.setSelectedPlace(null);
        mapView.getMapManager().removeAllMarkers();
        placeListView.resetPlaceListItems();
    }

    public PlaceListView getPlaceListView() {
        return placeListView;
    }

    public void setPlaceListView(PlaceListView placeListView) {
        this.placeListView = placeListView;
    }

    private final HashMap<String, InfoPopup> popups;
    private final HashMap<String, Integer> popupReqCodes;

    static class LazyHolder{
        public static MainViewContainer mainViewContainer = new MainViewContainer();
    }
    public HashMap<String, InfoPopup> getPopups() {
        return popups;
    }
    public MapView getMapView() {
        return mapView;
    }
    public AutoCompleteTextField getStartPlace() {
        return startPlace;
    }
    public AutoCompleteTextField getGoalPlace() {
        return goalPlace;
    }
    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }
    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
    public void setStartPlace(AutoCompleteTextField startPlace) {
        this.startPlace = startPlace;
    }
    public void setGoalPlace(AutoCompleteTextField goalPlace) {
        this.goalPlace = goalPlace;
    }
    private MainViewContainer() {
        popups = new HashMap<>();
        popups.put("박물관미술관", new MuseumInfoPopup());
        popups.put("휴양림", new ForestInfoPopup());
        popups.put("공연장", new ConcertHallInfoPopup());
        popups.put("공원", new ParkInfoPopup());
        popupReqCodes = new HashMap<>();
        popupReqCodes.put("박물관미술관", Protocol.REQ_MUSEUM_INFO);
        popupReqCodes.put("휴양림", Protocol.REQ_FOREST_INFO);
        popupReqCodes.put("공연장", Protocol.REQ_CONCERTHALL_INFO);
        popupReqCodes.put("공원", Protocol.REQ_PARK_INFO);
    }

    public int getPopupReqCode(Place place) {
        return popupReqCodes.get(place.getType());
    }
    public static MainViewContainer getInstance(){
        return LazyHolder.mainViewContainer;
    }
}
