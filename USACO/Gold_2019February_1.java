
import java.io.*;
import java.util.*;

public class February2019_Gold_1 {
    public static boolean[][] graph;
    public static int[][] paths;
    //first index = start; second index = end
    
    public static boolean hasPath() {
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\Geoffrey\\Documents\\filein.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Geoffrey\\Documents\\fileout.txt")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
        out.close();
        st = new StringTokenizer(f.readLine());
        int[] enjoyment = new int[N];
        for (int i = 0; i < N; i++) {
            enjoyment[i] = Integer.parseInt(st.nextToken());
        }
        graph = new boolean[N][N];
        paths = new int[N][N];
        
        //int[][] enjoymentValues = new int[N][N];
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a][b] = graph[b][a] = true;
            System.out.println(a + " " + b);
            //int c = enjoyment[a]^enjoyment[b];
            //enjoymentValues[a][b] = enjoymentValues[b][a] = c;
        }
        
        
        //System.out.println(path[3][2] + " " + path[1][2]);
        
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(f.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                enjoyment[index] = Integer.parseInt(st.nextToken());
                /*
                int b = path[index][0];
                int c = enjoyment[index]^enjoyment[b];
                enjoymentValues[index][b] = enjoymentValues[b][index] = c;
                if (path[index][2] == 2) {
                    b = path[index][1];
                    c = enjoyment[index]^enjoyment[b];
                    enjoymentValues[index][b] = enjoymentValues[b][index] = c;
                }
                */
            }
            //unnecesscary if statement
            /*
            if (type == 2) {
                int start = Integer.parseInt(st.nextToken()) - 1, end = Integer.parseInt(st.nextToken()) - 1;
                System.out.println(start + " " + end);
                int current;
                if (findPath(path, start, end)) {
                    current = path[start][0];
                } else {
                    current = path[start][1];
                }
                int previous = start;
                int currentEnjoyment = enjoyment[start]^enjoyment[current];
                while (current != end) {
                    System.out.println(current);
                    if (path[current][0] == previous) {
                        previous = current;
                        current = path[current][1];
                    } else {
                        previous = current;
                        current = path[current][0];
                    }
                    currentEnjoyment = currentEnjoyment^enjoyment[current];
                }
                out.println(currentEnjoyment);
                System.out.println(currentEnjoyment);
            }
            */
        }
    }
}
