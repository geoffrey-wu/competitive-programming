
import java.io.*;
import java.util.*;

public class January2019_Silver_3 {

    /* returns:
     * -1 if the first mountain covers the second mountain (you can't see the second mountain)
     * 1 if the second mountain covers the first mountain (you can't see the first mountain)
     * 0 if both mountains can be seen
     */
    public static int compare(int x1, int y1, int x2, int y2) {
        boolean onLine1 = (y2 == y1 - x1 + x2);
        boolean onLine2 = (y2 == x1 + y1 - x2);
        boolean a = (y2 >= y1 - x1 + x2); //left of or on the right line
        boolean b = (y2 >= x1 + y1 - x2); //right of on or the left line
        if (a && b) {
            return 1;
        }
        if (a && !b && !onLine1) {
            return 0;
        }
        if (b && !a && !onLine2) {
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));

        int N = Integer.parseInt(f.readLine());

        boolean[] visible = new boolean[N];
        int[] peaks_x = new int[N];
        int[] peaks_y = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            peaks_x[i] = Integer.parseInt(st.nextToken());
            peaks_y[i] = Integer.parseInt(st.nextToken());
            boolean seen = true;
            for (int j = 0; j < i; j++) {
                if (visible[j]) {
                    int result = compare(peaks_x[i], peaks_y[i], peaks_x[j], peaks_y[j]);
                    if (result == -1) {
                        visible[j] = false;
                    }
                    if (result == 1) {
                        seen = false;
                        break;
                    }
                }
            }
            visible[i] = seen;
        }

        System.out.println(compare(7, 2, 4, 6));
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (visible[i]) {
                count++;
                System.out.println(i);
            }
        }

        out.println(count);
        out.close();
    }
}
