import java.io.*;
import java.util.*;

public class December2018_Silver_2 {
    public static int findCow(int[][] array, int time) {
        int value = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i][0] < value && array[i][0] >= time) {
                value = array[i][0];
                index = i;
            }
        }
        return index;
    }
    public static boolean[] findCows(int[][] array, int time1, int time2) {
        boolean[] b = new boolean[array.length];
        for(int i = 0; i < array.length; i++) {
            if(array[i][0] >= time1 && array[i][0] < time2) {
                b[i] = true;
            }
        }
        return b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
        int N = Integer.parseInt(f.readLine()), cowsLeft = N;
        int[][] cows = new int[N][2];
        int[] delays = new int[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }
        int time = 0;
        boolean waiting = false;
        boolean[] inLine = new boolean[N];
        while(cowsLeft > 0) {
            int nextCow = 0;
            if(!waiting) {
                nextCow = findCow(cows, time);
                time = cows[nextCow][0] + cows[nextCow][1];
            } else {
                for(int i = 0; i < N; i++) {
                    if(inLine[i]) {
                        nextCow = i;
                        break;
                    }
                }
                delays[nextCow] = time - cows[nextCow][0];
                time += cows[nextCow][1];
            }
            waiting = false;
            for(int i = 0; i < N; i++) {
                if(i == nextCow) continue;
                if(cows[i][0] >= time - cows[nextCow][1] && cows[i][0] < time) {
                    inLine[i] = true;
                }
                waiting = waiting || inLine[i];
            }
            inLine[nextCow] = false;
            //System.out.println(time + " " + nextCow);
            cowsLeft--;
        }
        
        int max = 0, index = 0;
        for(int i = 0; i < N; i++) {
            if(delays[i] > max) {
                max = delays[i];
                //index = i;
            }
        }
        
        out.println(max);
        //System.out.println(index);
        out.close();
    }
}
