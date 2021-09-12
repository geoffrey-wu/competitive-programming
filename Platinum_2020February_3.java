import java.io.*;
import java.util.*;

public class Platinum_2020February_3 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\geoff\\Documents\\filein.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\geoff\\Documents\\filein.txt")));
        
        double p = 0.9;
        int N = 75;
        out.println(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.random() < p) {
                    out.print("*");
                } else {
                    out.print(".");
                }
            }
            out.print("\n");
        }
        out.close();
    }
}
