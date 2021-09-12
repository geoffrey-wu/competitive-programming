import java.io.*;
import java.util.*;

public class December2018_Silver_1 {
    public static int answer = Integer.MAX_VALUE;
    public static void search(int[] cows, int N, int M, int C, int max) {
        if(N == cows.length) {
            answer = (answer < max) ? answer : max;
        }
        else {
            if(M == 0 || M*C + N < cows.length) return;
            for(int i = 0; i < C && N+i < cows.length; i++) {
                if(N == 0) search(cows, N+i+1, M-1, C, cows[N+i] - cows[N]);
                else search(cows, N+i+1, M-1, C, Math.max(cows[N+i] - cows[N], max));
                //if(cows[N+i] - cows[N] < max) max = cows[N+i] - cows [N];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("convention.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
        int[] cows = new int[N];
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        search(cows, 0, M, C, 0);
        
        out.println(answer);
        out.close();
    }
}
