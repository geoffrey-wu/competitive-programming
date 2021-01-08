
import java.io.*;
import java.util.*;

public class Problem1 {
    static int[] barns;
    static int[] cows;

    public static int numberOfWays(int[] newBarns, int[] newCows) {
        int total = 1;
        for (int i = 0; i < newCows.length; i++) {
            int temp = 0;
            for (int j = 0; j < newBarns.length; j++) {
                if (newBarns[j] >= newCows[i]) temp++;
            }
            System.out.println(total + ", " + temp);
            total *= Math.max(temp - i, 1);
        }

        return total;
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int N = sc.nextInt();
        // barns = new int[N];
        // cows = new int[N];

        // for (int i = 0; i < N; i++) {
        //     barns[i] = sc.nextInt();
        // }

        // for (int i = 0; i < N; i++) {
        //     cows[i] = sc.nextInt();
        // }

        long startTime = System.currentTimeMillis();
        System.out.println(numberOfWays(new int[] {3, 2, 2, 1}, new int[] {4, 3, 2, 1}));
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTime)/1000.0 + " seconds");
    }
}