import java.io.*;
import java.util.*;

public class December2018_Bronze_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
        int[][] buckets = new int[3][2];
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            buckets[i][0] = Integer.parseInt(st.nextToken());
            buckets[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < 100; i++) {
            int x = buckets[i%3][1];
            int y = buckets[(i+1)%3][1];
            if(y+x > buckets[(i+1)%3][0]) {
                buckets[(i+1)%3][1] = buckets[(i+1)%3][0];
                buckets[i%3][1] -= buckets[(i+1)%3][0] - y;
            } else {
                buckets[(i+1)%3][1] = y+x;
                buckets[i%3][1] = 0;
            }
        }
        
        for(int i = 0; i < 3; i++) {
            out.println(buckets[i][1]);
        }
        
        out.close();
    }
}
