import java.io.*;
import java.util.*;

public class Platinum_2020March_1 {
    final static int P = 1000000007;
    public static int countEmpty(boolean[][] field, int x, int y, boolean bottomLeft) {
        if (x < 0) return 0;
        int count = -1;
        if (bottomLeft == true) {
            for (int i = x; i < field.length; i++) {
                for (int j = y; j >= 0; j--) {
                    if (field[i][j]) count++;
                }
            }
        } else if (bottomLeft == false) {
            for (int i = x; i >= 0; i--) {
                for (int j = y; j < field[0].length; j++) {
                    if (field[i][j]) count++;
                }
            }
        }
        return count;
    }
    public static boolean[][] copyOfRange(boolean[][] array, int x, int y) {
        if (x >= array.length || y >= array[0].length) return null;
        boolean[][] result = new boolean[array.length - x][array[0].length - y];
        
        for (int i = x; i < array.length; i++) {
            for (int j = y; j < array[0].length; j++) {
                result[i - x][j - y] = array[i][j];
            }
        }
        
        return result;
    }
    public static long powerMod(long a, long b, long mod) {
        if (b == 0) return 1;
        long result = a;
        String binary = Long.toBinaryString(b);
        for (int i = 1; i < binary.length(); i++) {
            result = (result * result) % mod;
            if (binary.charAt(i) == '1') result = (result * a) % mod;
        }
        return result;
    }
    public static long count(boolean[][] field) {
        if (field == null) return 1;
        long currentCount = 0;
        
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col <= field[0].length; col++) {
                int temp = -1; for (int i = col + 1; i < field[0].length; i++) temp += field[row][i] ? 1 : 0;
                if (temp < 0) temp = 0;
                long x = count(copyOfRange(field, row + 1, col + 1)) 
                        * powerMod(2, countEmpty(field, row, col, true), P) 
                        * powerMod(2, countEmpty(field, row - 1, 0, false), P)
                        * powerMod(2, temp, P);
                currentCount += x;
                System.out.println(row + " " + col + " " + x);
            }
        }
        
        currentCount += powerMod(2, countEmpty(field, field.length - 1, 0, false), P);
        
        return currentCount;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\geoff\\Documents\\filein.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\geoff\\Documents\\fileout.txt")));
        int N = Integer.parseInt(f.readLine());
        boolean[][] grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = f.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = (s.charAt(j) == '.');
            }
        }
        
        //System.out.println(powerMod(2, 1, 9));
        //System.out.println(count(grid));
        System.out.println(count(new boolean[][] {{true, true}, {true, true}}));
        
        //System.out.println(powerMod(2, 30000, 555));
        //System.out.println(countEmpty(grid, 0, 0, true));
        //System.out.println(countEmpty(grid, 0, 1, false));
        
        //System.out.println(Arrays.toString(grid[0]));
        //System.out.println(Arrays.toString(grid[1]));
        
        out.close();
    }
}
