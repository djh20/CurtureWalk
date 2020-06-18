package View.Home;

import Model.MapBoundary;
import Model.Place;

import java.util.Vector;

public class SearchConditions {
    private static SearchConditions searchConditions = null;
    private Place startPlace;
    private Place goalPlace;
    private Vector<Place> stopOvers;
    private MapBoundary mapBoundary;

    public void reset(){
        startPlace = null;
        goalPlace = null;
        stopOvers.clear();
        mapBoundary = null;
    }

    private SearchConditions() {
        stopOvers = new Vector<>();
    }
    public static SearchConditions getInstance(){
        if(searchConditions == null)
            searchConditions = new SearchConditions();
        return searchConditions;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getGoalPlace() {
        return goalPlace;
    }

    public void setGoalPlace(Place goalPlace) {
        this.goalPlace = goalPlace;
    }

    public Vector<Place> getStopOvers() {
        return stopOvers;
    }

    public void setStopOvers(Vector<Place> stopOvers) {
        this.stopOvers = stopOvers;
    }

    public MapBoundary getMapBoundary() {
        return mapBoundary;
    }

    public void setMapBoundary(MapBoundary mapBoundary) {
        this.mapBoundary = mapBoundary;
    }

    public void addStopOver(Place place){
        stopOvers.add(place);
    }
    public void removeStopOver(Place place){
        stopOvers.remove(place);
    }
}
