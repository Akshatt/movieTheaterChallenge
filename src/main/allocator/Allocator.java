package src.main.allocator;

import java.util.Arrays;
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

    public int handleReq(String newReq, int v) {
        String[] req = newReq.split(" ");
        String rNum = req[0];
        int rValue = Integer.parseInt(req[1]);
        
        if (v == 1) System.out.println("NEW REQUEST : " + rNum + " "+rValue);

        if (rValue < 1) {
            reservations.put(rNum, errorMsgs.get(1));
            return -1;
        }
        else if (rValue > vacantSeats){
            reservations.put(rNum, errorMsgs.get(2));
            return 1;
        } 
        else {
            String alloted = "";
            Boolean firstPriorityFull = false;

            // 1st priority seats
            if (!firstPriorityFull) {

                for (int i = mid; i< rows; i++) {
                    if (rValue <= (columns - lastSeatInRow[i]) || rValue > columns) {

                        if (v == 1) System.out.println("Pass 1");

                        int col = lastSeatInRow[i];
                    
                        while (col < columns && rValue > 0 && lastSeatInRow[i] < columns){
                            if (seatMap[i][col] == null) {
                                seatMap[i][col] = rNum;
                                alloted +=  ", " + (char)(i+65) + Integer.toString(col+1);
                                rValue--;
                                vacantSeats--;
                            }
                            col++;
                        }

                        lastSeatInRow[i] = col+3 > 19 ? columns : col+3 ;
                        vacantSeats -= (lastSeatInRow[i] - col);
                        if (rValue == 0) break;
                    }
                }

                if (rValue != 0) {
                    for (int i = mid; i< rows; i++) {
                        int col = lastSeatInRow[i];
                        if (v == 1) System.out.println("Pass 2");

                        while (col < columns && rValue > 0 && lastSeatInRow[i] < columns){
                            if (seatMap[i][col] == null) {
                                seatMap[i][col] = rNum;
                                alloted +=  ", " + (char)(i+65) + Integer.toString(col+1);
                                rValue--;
                                vacantSeats--;
                            }
                            col++;
                        }
    
                        lastSeatInRow[i] = col+3 > 19 ? columns : col+3 ;
                        vacantSeats -= (lastSeatInRow[i] - col);
                        if (rValue == 0) break;
                    }
                }
            }
                

            //2nd Priority seats 
            if (rValue != 0) {
                firstPriorityFull = true;
                
                for (int i = mid -1; i >=0 ; i--) {
                    if (rValue <= (columns - lastSeatInRow[i]) || rValue > columns) {
                        int col = lastSeatInRow[i];
                    
                        while (col < columns && rValue > 0 && lastSeatInRow[i] < columns){
                            if (seatMap[i][col] == null) {
                                seatMap[i][col] = rNum;
                                alloted +=  ", " + (char)(i+65) + Integer.toString(col+1);
                                
                                rValue--;
                                vacantSeats--;
                            }
                            col++;
                        }

                        lastSeatInRow[i] = col+3 > 19 ? columns : col+3 ;
                        vacantSeats -= (lastSeatInRow[i] - col);
                        if (rValue == 0) break;
                    }
                }
                if (rValue != 0){
                    for (int i = mid -1; i >=0 ; i--) {
                        int col = lastSeatInRow[i];
                        
                        while (col < columns && rValue > 0 && lastSeatInRow[i] < columns){
                            if (seatMap[i][col] == null) {
                                seatMap[i][col] = rNum;
                                alloted +=  ", " + (char)(i+65) + Integer.toString(col+1);
                                
                                rValue--;
                                vacantSeats--;
                            }
                            col++;
                        }
    
                        lastSeatInRow[i] = col+3 > 19 ? columns : col+3 ;
                        vacantSeats -= (lastSeatInRow[i] - col);
                        if (rValue == 0) break;
                    }
                }
            }
            if (v == 1) {
                System.out.println( Arrays.toString( lastSeatInRow ));
                printSeatMap();
                System.out.println("Vacant seats : " + vacantSeats + "\n");
            }
            
            reservations.put(rNum, alloted.substring(2));
            return 0;
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
