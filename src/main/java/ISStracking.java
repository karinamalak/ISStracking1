import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ISStracking {
    public static double lat1=0;
    public static double lat2=0;
    public static double lon1=0;
    public static double lon2=0;
    public static boolean coDrugie = false;
    /////
    public static void main(String[] args) throws IOException {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               try {
                                   //w drugiem przejsciu lat1=nowe a lat 2 = lat1 stare
                                   if (lat1==0 && lon1==0) { //wartosci poczatkowe pierwsze odpalenie
                                       lat1 = JSONparse.getLatitude(JSONparse.parser());
                                       lon1 = JSONparse.getLongitude(JSONparse.parser());
                                   } else if (lat1!=0 && lon1!=0 &&lat2==0 && lon2==0) { //2 przejscie
                                       lat2 = JSONparse.getLatitude(JSONparse.parser());
                                       lon2 = JSONparse.getLongitude(JSONparse.parser());
                                       coDrugie = true;
                                   } else {
                                       if (coDrugie==true) {

                                       } else {

                                       }

                                   }
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                               if (lat1!=0 && lat2 !=0 && lon1 != 0 && lon2 !=0) {
                                   Distance.distance(lat1, lon1, lat2, lon2);
                               }
                           }
                       }

                , 0, 5000);

    }
}
