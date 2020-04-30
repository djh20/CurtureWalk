import java.util.Vector;

public class Edge {

    Place place;
    Vector<Vertax> vertaxes= new Vector<Vertax>();


    public Edge(Place place) {
        this.place = place;
    }

    public void addVertax(Vertax vertax){
        vertaxes.add(vertax);
    }

    public void clearVertaxes(){
        vertaxes.clear();
    }
}
