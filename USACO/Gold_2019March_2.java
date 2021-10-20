
import java.io.*;
import java.util.*;

public class March2019_Gold_2 {

    public static void main(String[] args) throws IOException {
        //"C:\\Users\\Geoffrey\\Documents\\filein.txt"
        BufferedReader f = new BufferedReader(new FileReader("walk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        
        /*
        int[][] distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                distances[i][j] = distances[j][i] = -84 * (j + 1) - 48 * (i + 1);
            }
        }
        */
        //wtf why is this so easy
        out.println(2019201997 - 84 * (K - 1) - 48 * N);
        out.close();
    }
}
