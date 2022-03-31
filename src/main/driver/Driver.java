package src.main.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import src.main.allocator.Allocator;
import src.test.testAllocation;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        
        if (args.length > 0) {

            Allocator seatAllocator = new Allocator();
            int v = 0;
            if (args.length == 2 && args[1].equals("verbose")) v = 1;

            if (args[0].equals("test")) {
                testAllocation tester = new testAllocation();
                tester.runTests();
            } else {
                try {
                    //fileread
                    File f = new File (args[0]); 
                    FileReader reserveReqs = new FileReader(f);
                    BufferedReader loadReqs = new BufferedReader(reserveReqs);

                    String newReq = loadReqs.readLine();
                    
                    //process requests
                    while (newReq != null) {
                        seatAllocator.handleReq(newReq, v);
                        newReq = loadReqs.readLine();
                    }
                    
                    // check seatmap
                    if (v == 1) {
                        System.out.println("\nFinal Seating Arrangements: \n");
                        seatAllocator.printSeatMap();
                    }
                    

                    reserveReqs.close();

                    // file write
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter("output.txt"));
                        for(Map.Entry<String, String>itr:seatAllocator.getReservations().entrySet()) {
                            writer.write(itr.getKey() + " " + itr.getValue() + "\n");
                        }
                        writer.close();
                        System.out.println("\nOutput File path : "+ System.getProperty("user.dir") + "\\output.txt");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }           
        }       

    }
}