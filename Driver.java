
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Driver {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                File f = new File (args[0]); 
                FileReader reserveReqs = new FileReader(f);
                BufferedReader loadReqs = new BufferedReader(reserveReqs);

                String newReq = loadReqs.readLine();
            
                while (newReq != null) {
                    System.out.println(newReq);
                    newReq = loadReqs.readLine();
                }
            
            } catch (Exception e){
                e.printStackTrace();
            }
        }       
    }
}