package src.allocator;

import java.util.HashMap;
import java.util.Map;

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
    
    public String handleReq(String newReq) {
        String[] req = newReq.split(" ");
        String rNum = req[0];
        int rValue = Integer.parseInt(req[1]);
        if (rValue < 1) {
            return rNum + " "+ errorMsgs.get(1);
        }
        return rNum + " " + rValue; 
    }
}
