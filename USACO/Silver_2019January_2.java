
import java.io.*;
import java.util.*;

public class January2019_Silver_2 {

    public static int N;
    public static boolean[][] iceCream;
    public static int[][] floodFill;
    public static int count = 1;

    public static void flood(int i, int j) {
        if (!iceCream[i][j]) {
            return;
        }
        if (floodFill[i][j] != 0) {
            return;
        }
        floodFill[i][j] = count;
        if (i != 0) {
            flood(i - 1, j);
        }
        if (j != 0) {
            flood(i, j - 1);
        }
        if (i != N - 1) {
            flood(i + 1, j);
        }
        if (j != N - 1) {
            flood(i, j + 1);
        }
    }

    public static int numNotAdjacent(int i, int j) {
        int total = 0;
        if (i == 0) {
            total++;
        } else if (!iceCream[i - 1][j]) {
            total++;
        }
        if (j == 0) {
            total++;
        } else if (!iceCream[i][j - 1]) {
            total++;
        }
        if (i == N - 1) {
            total++;
        } else if (!iceCream[i + 1][j]) {
            total++;
        }
        if (j == N - 1) {
            total++;
        } else if (!iceCream[i][j + 1]) {
            total++;
        }
        return total;
    }

    public static int perimeter(int blob) {
        int perimeter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (floodFill[i][j] == blob) {
                    perimeter += numNotAdjacent(i, j);
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));

        N = Integer.parseInt(f.readLine());

        iceCream = new boolean[N][N];
        floodFill = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = f.readLine();
            for (int j = 0; j < N; j++) {
                iceCream[i][j] = (s.charAt(j) == '#');
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!iceCream[i][j]) {
                    continue;
                }
                if (floodFill[i][j] != 0) {
                    continue;
                }
                flood(i, j);
                count++;
            }
        }

        int[] values = new int[count];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                values[floodFill[i][j]]++;
            }
        }

        int max = 0, maxIndex = 0, perimeter = 0;
        for (int i = 1; i < count; i++) {
            if (values[i] > max) {
                max = values[i];
                maxIndex = i;
            }
            if (values[i] == max) {
                if (perimeter == 0) perimeter = perimeter(maxIndex);
                int x = perimeter(i);
                if (x > perimeter) {
                    maxIndex = i;
                    perimeter = x;
                }
            }
        }
        if (perimeter == 0) perimeter = perimeter(maxIndex);

        out.println(max + " " + perimeter);
        //out.println(count);
        out.close();
    }
}
