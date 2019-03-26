import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class JSONparse {


    public static JsonObject parserLocation() throws IOException {
        String sURL = "http://api.open-notify.org/iss-now.json";

        URL url = new URL(sURL);
        URLConnection request = url.openConnection();

        JsonParser jp = new JsonParser();

        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        // JsonObject rootobj = root.getAsJsonObject();
        return root.getAsJsonObject();

        //https://maps.googleapis.com/maps/api/distancematrix/json?origins=40.6655101,-73.891889&destinations=40.6655101,-73.891009&key=...
    }

    public static JsonObject parserPeople() throws IOException {
        String sURL = "http://api.open-notify.org/astros.json";

        URL url = new URL(sURL);
        URLConnection request = url.openConnection();

        JsonParser jp = new JsonParser();

        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        JsonElement rootobj1 = rootobj.get("people").getAsJsonObject();
        // System.out.println(rootobj1.get("name").getAsString());
        return root.getAsJsonObject();

        //https://maps.googleapis.com/maps/api/distancematrix/json?origins=40.6655101,-73.891889&destinations=40.6655101,-73.891009&key=...
    }


    public static double getLongitude() {
        try {
            JsonObject root= parserLocation();
            JsonObject issPosition = root.get("iss_position").getAsJsonObject();
            return Double.valueOf(issPosition.get("longitude").getAsString());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double getLatitude() {
        JsonObject root= null;
        try {
            root = parserLocation();
            JsonObject issPosition = root.get("iss_position").getAsJsonObject();
            return Double.valueOf(issPosition.get("latitude").getAsString());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static long getDate(JsonObject root) {
//        getDate(timestamp);
//        Date date = new Date(timestamp * 1000);
//        SimpleDateFormat ft =
//                new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
//        System.out.println("Current Date: " + ft.format(date));
        return root.get("timestamp").getAsLong();
    }

}
