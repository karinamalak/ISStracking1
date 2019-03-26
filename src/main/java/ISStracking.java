import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ISStracking {
    public static double lat1 = 0;
    public static double lat2 = 0;
    public static double lon1 = 0;
    public static double lon2 = 0;
    public static boolean coDrugie = false;
    public static double distance = 0;

    /////
    public static void main(String[] args) {


        try {
            JSONparse.parserPeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               //w drugiem przejsciu lat1=nowe a lat 2 = lat1 stare
                               if (lat1 == 0 && lon1 == 0) { //wartosci poczatkowe pierwsze odpalenie
                                   lat1 = JSONparse.getLatitude();
                                   lon1 = JSONparse.getLongitude();
                               } else if (lat1 != 0 && lon1 != 0 && lat2 == 0 && lon2 == 0) { //2 przejscie
                                   lat2 = JSONparse.getLatitude();
                                   lon2 = JSONparse.getLongitude();
                                   coDrugie = true;
                               } else {
                                   if (coDrugie) {
                                       lat1 = lat2;
                                       lon1 = lon2;

                                       lat2 = JSONparse.getLatitude();
                                       lon2 = JSONparse.getLongitude();
                                   }
                               }
                               if (lat1 != 0 && lat2 != 0 && lon1 != 0 && lon2 != 0) {
                                   distance = Distance.distance(lat1, lon1, lat2, lon2);
                               }
                               System.out.println(lat1+" "+lon1+" "+lat2+" "+lon2);
                               System.out.println("distace" + distance);
                               System.out.println("speed: "+(distance*12*60)+ " km/h");
                           }
                       }

                , 0, 5000);

    }
}
