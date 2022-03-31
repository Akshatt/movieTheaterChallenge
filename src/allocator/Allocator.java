package src.allocator;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class Allocator {
  int rows = 10;
  int columns = 20;
  int totalSeats = rows * columns;
  int vacantSeats = totalSeats;
  
  public static Map<Integer, String> errorMsgs;
    static {
        errorMsgs = new HashMap<>();
        errorMsgs.put(1,"Invalid number of seats requested!");
        errorMsgs.put(2,"Number of seats requested are unavailable!");  
    }

  LinkedHashMap<String, String> reservations = new LinkedHashMap<>();
    
    public void handleReq(String newReq) {
        String[] req = newReq.split(" ");
        String rNum = req[0];
        int rValue = Integer.parseInt(req[1]);
        if (rValue < 1) {
            reservations.put(rNum, errorMsgs.get(1));
        }
        else {
            reservations.put(rNum, Integer.toString(rValue));
        }
    }

    public LinkedHashMap<String, String> getReservations() {
        return reservations;
    }
}
