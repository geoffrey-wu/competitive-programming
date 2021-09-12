import java.io.*;
import java.util.*;

public class January2019_Silver_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        
        int N = Integer.parseInt(f.readLine());
        int[] degrees = new int[N];
        for(int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            degrees[a-1]++;
            degrees[b-1]++;
        }
        int max = 0;
        for(int i = 0; i < N; i++) {
            if(degrees[i] > max) max = degrees[i];
        }
        
        out.println(max + 1);
        out.close();
    }
}
