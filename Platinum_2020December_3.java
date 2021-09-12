
import java.io.*;
import java.util.*;

public class Problem3 {

    static int K;
    static int two;

    public static int getSmallestPowerOfTwo(int n) {
        double d = Math.log(n) / Math.log(2);
        return (int) Math.pow(2.0, Math.ceil(d));
    }

    /**
     * both start and stop are inclusive
     */
    public static int checkPermutations(int[] numbers) {
        int total = 0;
        for (int a = 0; a < numbers.length; a++) {
            for (int b = 0; b < a; b++) {
                int x = numbers[a];
                int y = numbers[b];
                if ((x ^ y) > K)
                    continue;
                for (int c = 0; c < b; c++) {
                    int z = numbers[c];
                    if ((x ^ z) <= K && (y ^ z) <= K) {
                        total++;
                    }
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        K = sc.nextInt();
        two = getSmallestPowerOfTwo(K);
        int[][] boxes = new int[N][2];
        ArrayList<HashSet<Integer>> chemicals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            boxes[i][0] = sc.nextInt();
            boxes[i][1] = sc.nextInt();

            while (boxes[i][1] > chemicals.size() * two) {
                chemicals.add(new HashSet<>());
            }

            for (int j = boxes[i][0]; j <= boxes[i][1]; j++) {
                chemicals.get((int)(j/two)).add(j%two);
            }
        }

        long startTime = System.currentTimeMillis();
        long total = 0;

        // for (int a = 0; a < two/2; a++) {
        //     for (int b = 0; b < a; b++) {
        //         for (int c = 0; c < b; c++) {
        //             for (HashSet set : chemicals) {
        //                 if (set.contains(a) && set.contains(b) && set.contains(c)) {
        //                     total++;
        //                 }
        //             }
        //         }
        //         if (total > 1000000007) {
        //             total = total%1000000007;
        //         }
        //     }
        // }

        for (HashSet set : chemicals) {
            Iterator it = set.iterator();
            int num = 0;
            while (it.hasNext()) {
                if ((int)it.next() < two/2) {
                    num++;
                }
            }
            total = (total + num*(num-1)*(num-2)/6)%1000000007;
        }

        for (int a = two/2; a < two; a++) {
            for (int b = 0; b < a; b++) {
                if ((a^b) > K) continue;
                for (int c = 0; c < b; c++) {
                    // if ((b^c) <= K && (c^a) <= K) {
                        for (HashSet set : chemicals) {
                            if (set.contains(a) && set.contains(b) && set.contains(c)) {
                                total++;
                            }
                        }
                    // }
                }
                if (total > 1000000007) {
                    total = total%1000000007;
                }
            }
        }

        // long total = 0;
        // for (ArrayList<Integer> arrayList : chemicals) {
        //     int[] array = new int[arrayList.size()];
        //     for (int i = 0; i < array.length; i++) {
        //         array[i] = arrayList.get(i);
        //     }
        //     total += checkPermutations(array);
        //     // System.out.println(checkPermutations(array));
        // }

        System.out.println(total);
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
        sc.close();
    }
}
