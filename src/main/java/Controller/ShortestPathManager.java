package Controller;

import MetaData.ShortestPathApiMetaData;
import Model.*;
import Network.DataManager;
import Network.Packet;

import java.util.Vector;

public class ShortestPathManager {
    private static ShortestPathManager shortestPathManager = null;
    private Vector<Node> nodes;
    private int numNode;
    private int[][] distance;
    private int totalDistance;

    private ShortestPathManager(){
    }

    public static ShortestPathManager getInstance(){
        if (shortestPathManager == null)
            shortestPathManager = new ShortestPathManager();
        return shortestPathManager;
    }

    public ShortestPathSearchResult getShortestPath(Place start, Place goal, Vector<Place> stopOvers, int trafficType){
        initializeVariable(start,goal,stopOvers);
        initialNodes(start, goal, stopOvers, trafficType);
        System.out.println("출발지 " + start.getName() + " 도착지 " + goal.getName());
        totalDistance = completeSearchUsingDP(0,0);
        System.out.println(totalDistance);
        return new ShortestPathSearchResult(getResultPath(totalDistance), totalDistance);

    }


    private void resetAll(){
        nodes = new Vector<>();
        numNode = 0;
    }

    private void initialDistanceArray(){
        for(int i = 0 ; i < distance.length ; i++){
            for(int j = 0 ;  j < distance[0].length ; j++){
                distance[i][j] = 0;
            }
        }
    }

    private void initializeVariable(Place start, Place goal, Vector<Place> stopOvers){
        resetAll();
        numNode = stopOvers.size() + 2;
        distance = new int[(int) Math.pow(2,numNode)][numNode];
        initialDistanceArray();
        totalDistance = 0;


    }

    private void initialNodes(Place start, Place goal, Vector<Place> stopOvers, int trafficType){
        nodes.add(new Node(start));
        for(Place place : stopOvers){nodes.add(new Node(place));}
        nodes.add(new Node(goal));
        for(int i = 0; i < nodes.size() ; i++){
            for (int j = 0 ; j <nodes.size() ; j++){
                if(i!=j){
                    Node node1 = nodes.get(i);
                    Node node2 = nodes.get(j);
                    Place[] data = {node1.getPlace(), node2.getPlace()};
                        if(trafficType == ShortestPathApiMetaData.PATH_TYPE_WALK && i > j){
                            if(i>j){
                                Route result = (Route) DataManager.getInstance().requestData(new Packet<Place>(trafficType, data)).getData()[0];
                                System.out.println("도보, " + node1.getPlace().getName() + ", " + node2.getPlace().getName() + " 거리 = " + result.getDistance());
                                Vertex vertax1 = new Vertex(node1, node2, result);
                                Vertex vertax2 = new Vertex(node2, node1, result);
                                node1.addVertex(vertax1);
                                node2.addVertex(vertax2);
                            }
                        }
                        else{
                            Route result = (Route) DataManager.getInstance().requestData(new Packet<Place>(trafficType, data)).getData()[0];
                            Vertex vertax1 = new Vertex(node1, node2, result);
                            node1.addVertex(vertax1);
                            System.out.println("도보X, " + node1.getPlace().getName() + ", " + node2.getPlace().getName() + " 거리 = " + result.getDistance());
                        }
                    }
            }
        }
    }

    private int completeSearchUsingDP(int visited, int current){
        visited |= (1 << current);

        // 도착만 남겨둔 경우
        if (visited == ((1 << (numNode - 1)) -1 )) {
            distance[visited][current] = getVertexBetweenNodeIndex(current, numNode - 1).getRoute().getDistance();
            return getVertexBetweenNodeIndex(current, numNode - 1).getRoute().getDistance();
        }


        if(distance[visited][current] > 0)
            return distance[visited][current];
        distance[visited][current] = Integer.MAX_VALUE;

        for (int i = 0; i < numNode; i++)
        {
            // i = 다음 위치임 // 현재위치와 같지 않은 경우, 이미 방문한 경로가 아닌경우, 도착지가 아닌경우
            if (i != current && (visited&(1<<i))==0 && i != numNode - 1)
            {
                //최소 비용 갱신
                int temp = completeSearchUsingDP(visited, i) + getVertexBetweenNodeIndex(current,i).getRoute().getDistance();
                if (distance[visited][current] > temp)
                    distance[visited][current] = temp;

            }
        }
        return distance[visited][current];
    }

    private Vertex getVertexBetweenNodeIndex(int startIndex, int goalIndex){
        Vertex vertex = null;
        Node edge = nodes.get(startIndex);
        Vector<Vertex> vertexes = edge.getVertexes();
        for(int i = 0 ; i < vertexes.size(); i++){
            if(vertexes.get(i).getDestNode() == nodes.get(goalIndex))
                vertex = vertexes.get(i);
        }
        return vertex;
    }


    private Vector<Vertex> getResultPath(int result){
            Vector<Vertex> path = new Vector<>();
            int piv = 0;
            int masking = 1;


            for(int i = 0 ; i < numNode ; i++){
                for(int j = 0 ; j < numNode ; j++){
                    if((masking & (1 << j)) > 0)
                        continue;
                    if(result - getVertexBetweenNodeIndex(piv,j).getRoute().getDistance() == distance[(masking + (1 << j))][j]){
                        path.add(getVertexBetweenNodeIndex(piv,j));
                        result = distance[(masking + (1 << j))][j];
                        piv = j;
                        masking += (1 << j);
                    }

                }
            }

            for(Vertex vertex : path){
                System.out.println(vertex.getStartNode().getPlace().getName());
                System.out.println(vertex.getDestNode().getPlace().getName());
            }
            return path;
    }
}
