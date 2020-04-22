import java.util.Vector;

public class Edge {
    String placeName;
    double lat;
    double lng;
    Vector<Vertax> vertaxes;

    public Edge(String placeName, double lat, double lng) {
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
    }

    public void addVertax(Vertax vertax){
        vertaxes.add(vertax);
    }

    public void clearVertaxes(){
        vertaxes.clear();
    }
}
