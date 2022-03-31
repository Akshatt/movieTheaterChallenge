package src.allocator;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class Allocator {
  int rows = 10;
  int columns = 20;
  int totalSeats = rows * columns;
  int vacantSeats = totalSeats;
  String[][] seatMap = new String[rows][columns];
  String[][] seatXOMap = new String[rows][columns];
  int mid = (rows/2) - 2;
  int[] lastSeatInRow = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  
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
        
        System.out.println("NEW PASS : " + rNum + " "+rValue);

        if (rValue < 1) {
            reservations.put(rNum, errorMsgs.get(1));
        }
        else if (rValue > vacantSeats){
            reservations.put(rNum, errorMsgs.get(2));
        } else {
            String alloted = "";
            Boolean firstPriorityFull = false;

            // 1st and 2nd priority seats
           
            for (int i = mid; i< rows; i++) {
                //System.out.println((char) (i + 65) + "\n");
                int col = lastSeatInRow[i];
                while (col < 20 && rValue > 0 && lastSeatInRow[i] != 19){
                    if (seatMap[i][col] == null) {
                        seatMap[i][col] = rNum;
                        alloted +=  ", " + (char)(i+65) + Integer.toString(col+1);
                        
                        rValue--;
                        vacantSeats--;
                    }
                    col++;
                }
                lastSeatInRow[i] = col+3;
                
                if (rValue == 0){
                    vacantSeats -= 3;
                    break;
                }
            }

            //3rd seats 
            if (rValue != 0) {
                for (int i = 0; i< mid; i++) {
                    //System.out.println((char) (i + 65) + "\n");
                    int col = lastSeatInRow[i];
                    while (col < 20 && rValue > 0 && lastSeatInRow[i] != 19){
                        if (seatMap[i][col] == null) {
                            seatMap[i][col] = rNum;
                            alloted +=  ", " + (char)(i+65) + Integer.toString(col+1);
                            
                            rValue--;
                            vacantSeats--;
                        }
                        col++;
                    }
                    lastSeatInRow[i] = col+3;
                    if (rValue == 0){
                        vacantSeats -= 3;
                        break;
                    }
    
                }
            }
            
            //printSeatMap();
            reservations.put(rNum, alloted.substring(2));
        }
    }

    public LinkedHashMap<String, String> getReservations() {
        return reservations;
    }

    public void printSeatMap() {
        for (int i = 0; i < rows; i++) {
			System.out.print((char) (i + 65) + " ");
			for (int j = 0; j < columns; j++) {
				System.out.print(" " + seatMap[i][j]);
			}
			System.out.println();
		}
    }
    
    public void printSeatXOMap() {
        for (int i = 0; i < rows; i++) {
			System.out.print((char) (i + 65) + " ");
			for (int j = 0; j < columns; j++) {
				System.out.print(" " + seatXOMap[i][j]);
			}
			System.out.println();
		}
    }
}
