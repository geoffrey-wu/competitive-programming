
import java.io.*;
import java.util.*;

public class March2019_Gold_1 {
    public static int[][] costs;
    public static final int MAX_VALUE = 500000000; //5 * 10^8
    
    public static int max(List<Integer> a) {
        int max = 0;
        for (int i : a) {
            max = Math.max(max, i);
        }
        return max;
    }
    public static int computeCost(List<Integer>a) {
        int max = 0;
        int total = 0;
        for (int i : a) {
            total += - i;
            if (i > max) max = i;
        }
        return max * a.size() + total;
    }
    public static int minWasted(List<Integer> a, int K) {
        int size = a.size();
        if (K == 0) {
            costs[size][0] = computeCost(a);
            return costs[size][0];
        }
        if (size <= K + 1) {
            costs[size][K] = 0;
            return 0;
        }
        int min = MAX_VALUE;
        for (int i = K; i < size; i++) {
            if (costs[i][K - 1] == -1) {
                costs[i][K - 1] = minWasted(a.subList(0, i), K - 1);
            }
            min = Math.min(min, costs[i][K - 1] + computeCost(a.subList(i, size)));
            //System.out.println(i + " " + K);
        }
        costs[size][K] = min;
        return min;
    }
    public static void main(String[] args) throws IOException {
        //"C:\\Users\\Geoffrey\\Documents\\filein.txt"
        
        BufferedReader f = new BufferedReader(new FileReader("snakes.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }
        
        costs = new int[N + 1][K + 1];
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < K; j++) {
                costs[i][j] = -1;
            }
        }
        out.println(minWasted(a, K));
        out.close();
    }
}
