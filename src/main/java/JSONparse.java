import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class JSONparse {


    public static JsonObject parser() throws IOException {
        String sURL = "http://api.open-notify.org/iss-now.json";

        URL url = new URL(sURL);
        URLConnection request = url.openConnection();

        JsonParser jp = new JsonParser();

        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        return root.getAsJsonObject();

        //"https://maps.googleapis.com/maps/api/geocode/json?latlng=32.263200,50.778187&key=<ENTER YOUR PLACE API KEY HEAR >"
    }

    public static double getLongitude(JsonObject root) {
        JsonObject issPosition = root.get("iss_position").getAsJsonObject();
        return Double.valueOf(issPosition.get("longitude").getAsString());
    }

    public static double getLatitude(JsonObject root) {
        JsonObject issPosition = root.get("iss_position").getAsJsonObject();
        return Double.valueOf(issPosition.get("latitude").getAsString());
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
