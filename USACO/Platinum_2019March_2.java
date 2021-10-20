
import java.io.*;
import java.util.*;

public class Platinum_2019March_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\Geoffrey\\Documents\\filein.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Geoffrey\\Documents\\fileout.txt")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        
        //Gates that are horizontal
        int[][] xGates = new int[N][K - 1];
        
        //Gates that are vertical
        int[][] yGates = new int[N - 1][K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < K - 1; j++) {
                xGates[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < N - 1; j++) {
                yGates[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        
        out.close();
    }
}
