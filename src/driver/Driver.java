package src.driver;

import java.io.BufferedReader;
import java.io.FileReader;

import src.allocator.Allocator;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        
        if (args.length > 0) {

            Allocator seatAllocator = new Allocator();

            try {
                File f = new File (args[0]); 
                FileReader reserveReqs = new FileReader(f);
                BufferedReader loadReqs = new BufferedReader(reserveReqs);

                String newReq = loadReqs.readLine();
            
                while (newReq != null) {
                    String output = seatAllocator.handleReq(newReq);
                    System.out.println(output);
                    newReq = loadReqs.readLine();
                }
                

                reserveReqs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }       
    }
}