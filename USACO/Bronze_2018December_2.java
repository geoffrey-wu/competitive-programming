import java.io.*;
import java.util.*;

public class December2018_Bronze_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("blist.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
        int N = Integer.parseInt(f.readLine());
        int[] array = new int[1001];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken()), 
                end = Integer.parseInt(st.nextToken()), 
                amount = Integer.parseInt(st.nextToken());
            for(int j = start; j <= end; j++) {
                array[j] += amount;
            }
        }
        
        int max = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] > max) max = array[i];
        }
        
        out.println(max);
        
        out.close();
    }
}
