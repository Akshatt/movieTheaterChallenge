package src.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import src.allocator.Allocator;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        
        if (args.length > 0) {

            Allocator seatAllocator = new Allocator();

            try {
                //fileread
                File f = new File (args[0]); 
                FileReader reserveReqs = new FileReader(f);
                BufferedReader loadReqs = new BufferedReader(reserveReqs);

                String newReq = loadReqs.readLine();
                
                //process requests
                while (newReq != null) {
                    seatAllocator.handleReq(newReq);
                    newReq = loadReqs.readLine();
                }
                
                // check seatmap
                seatAllocator.printSeatMap();

                reserveReqs.close();

                // file write
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter("output.txt"));
                    for(Map.Entry<String, String>itr:seatAllocator.getReservations().entrySet()) {
                        writer.write(itr.getKey() + " " + itr.getValue() + "\n");
                    }
                    writer.close();
                    System.out.println("Output File path : "+ System.getProperty("user.dir") + "\\output.txt");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e){
                e.printStackTrace();
            } 
        }       

    }
}