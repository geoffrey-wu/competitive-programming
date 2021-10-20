
import java.io.*;
import java.util.*;

class Pair {

    int x, y;

    public Pair(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public boolean equals(Pair i) {
        return (this.x == i.x && this.y == i.y);
    }
}

public class Platinum_2020February_2 {

    public static int distance(int[] a, int[] b) {
        //return 5;
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static boolean contains(ArrayList<int[]> a, int[] b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] == b[0] && a.get(i)[1] == b[1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\geoff\\Documents\\filein.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\geoff\\Documents\\fileout.txt")));

        long time = System.currentTimeMillis();
        int answer = 0;
        int counter = 0;

        int N = Integer.parseInt(f.readLine());
        ArrayList<int[]> cows = new ArrayList<>();
        boolean[][] map = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            String s = f.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '*') {
                    cows.add(new int[]{i, j});
                    map[i][j] = true;
                }
            }
        }

        final int numberOfCows = cows.size();
        
        for (int i = 0; i < numberOfCows; i++) {
            for (int j = 0; j < i; j++) {
                int[] cow1 = cows.get(i);
                int[] cow2 = cows.get(j);
                int a = cow1[0];
                int b = cow1[1];
                int c = cow2[0];
                int d = cow2[1];

                if ((a + b - c - d) % 2 == 1) {
                    continue;
                }

                int dist = distance(cow1, cow2);
                
                if (a + b == c + d || a - b == c - d) {
                    /*for (int k = 0; k < numberOfCows; k++) {
                        if (dist == distance(cow1, cows.get(k)) && dist == distance(cow2, cows.get(k))) {
                            answer++;
                            //System.out.println(cows.get(k)[0] + " " + cows.get(k)[1] + " c");
                            //System.out.println("2");
                            counter++;
                        }
                        
                    }*/
                    if (a + b == c + d) {
                        for (int y = Math.max(b, d), x = dist + a + b - y; x >= Math.max(a, c); x--) {
                            y = dist + a + b - x;
                            try {
                                //System.out.println(x + ", " + y + " A: " + a + " " + b + " B: " + c + " " + d);
                                if (map[x][y]) answer++;
                            } catch(Exception e) {
                                //continue;
                            }
                            
                        }
                        for (int y = Math.min(b, d), x = -dist + a + b - y; x <= Math.min(a, c); x++) {
                            y = -dist + a + b - x;
                            try {
                                //System.out.println(x + ", " + y + " A: " + a + " " + b + " B: " + c + " " + d);
                                if (map[x][y]) answer++;
                            } catch(Exception e) {
                                //break;
                            }
                        }
                    }
                    
                    if (a - b == c - d) {
                        for (int y = Math.min(b, d), x = dist + a - b + y; x >= Math.max(a, c); x--) {
                            y = -dist - a + b + x;
                            try {
                                //System.out.println(x + ", " + y + " A: " + a + " " + b + " B: " + c + " " + d);
                                if (map[x][y]) answer++;
                            } catch(Exception e) {
                                //break;
                            }
                        }
                        for (int y = Math.max(b, d), x = -dist - a + b + y; x <= Math.min(a, c); x++) {
                            y = dist + a - b + x;
                            try {
                                //System.out.println(x + ", " + y + " A: " + a + " " + b + " B: " + c + " " + d);
                                if (map[x][y]) answer++;
                            } catch(Exception e) {
                                //break;
                            }
                        }
                    }
                } else {
                    for (int k = 1; k <= 4; k++) {
                        int[] signs = new int[]{1, 1, 1, 1, 1};
                        signs[k] = -1;
                        int determinant = signs[1] * signs[4] - signs[2] * signs[3];
                        int x = (signs[4] * (dist + signs[1] * a + signs[2] * b) - signs[2] * (dist + signs[3] * c + signs[4] * d)) / determinant;
                        int y = (signs[1] * (dist + signs[3] * c + signs[4] * d) - signs[3] * (dist + signs[1] * a + signs[2] * b)) / determinant;
                        if (x < 0 || y < 0 || x >= N || y >= N) {
                            continue;
                        }
                        if (dist == distance(new int[]{x, y}, cow1) && dist == distance(new int[]{x, y}, cow2)) {
                            //if (contains(cows, new int[]{x, y})) answer++;
                            if (map[x][y]) answer++;
                        }
                    }

                    for (int k = 1; k <= 4; k++) {
                        int[] signs = new int[]{-1, -1, -1, -1, -1};
                        signs[k] = 1;
                        int determinant = signs[1] * signs[4] - signs[2] * signs[3];
                        int x = (signs[4] * (dist + signs[1] * a + signs[2] * b) - signs[2] * (dist + signs[3] * c + signs[4] * d)) / determinant;
                        int y = (signs[1] * (dist + signs[3] * c + signs[4] * d) - signs[3] * (dist + signs[1] * a + signs[2] * b)) / determinant;
                        if (x < 0 || y < 0 || x >= N || y >= N) {
                            continue;
                        }
                        if (dist == distance(new int[]{x, y}, cow1) && dist == distance(new int[]{x, y}, cow2)) {
                            //if (contains(cows, new int[]{x, y})) answer++;
                            if (map[x][y]) answer++;
                        }
                    }
                }
            }
        }

        System.out.println(counter/3 + " p");
        System.out.println(answer/3);
        out.println(answer / 3);
        
        out.close();

        System.out.println((System.currentTimeMillis() - time)/1000.0);
    }
}
