import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class RouteNavigation {

    public static NaviInfo getRoute(String start, String goal){
        Vector<Point> points = new Vector<Point>();
        int distance = 0;
        try {
            String option = "trafast";
            String apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=" + start + "&goal=" + goal + "&option=" + option;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "cno9s8zo41");
            con.setRequestProperty("X-NCP-APIGW-API-KEY", "2hYhcR31lks0axdLtPUs0nauwb1sSWeHEavqDHc7");



            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
//            System.out.println(response.toString());

            JSONObject jsonObject = new JSONObject(response.toString());
//            distance = jsonObject.getJSONObject("route").getJSONArray("trafast").getJSONObject(0).getInt("distance");
            System.out.println(jsonObject.getJSONObject("route").getJSONArray("trafast").getJSONObject(0).getJSONObject("summary"));
            distance = jsonObject.getJSONObject("route").getJSONArray("trafast").getJSONObject(0).getJSONObject("summary").getInt("distance");
            JSONArray jsonArray = jsonObject.getJSONObject("route").getJSONArray("trafast").getJSONObject(0).getJSONArray("path");
            for (int i = 0; i < jsonArray.length(); i++) {
                points.add(new Point(jsonArray.getJSONArray(i).getDouble(1),jsonArray.getJSONArray(i).getDouble(0)));
            }
            System.out.println(distance);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new NaviInfo(points, distance);
    }


}

class NaviInfo{
    Vector<Point> points;
    int distance;
    public NaviInfo(Vector<Point> points, int distance) {
        this.points = points;
        this.distance = distance;
    }
}

class Point{
    double lat;
    double longit;

    public Point(double lat, double longit) {
        this.lat = lat;
        this.longit = longit;
    }
}