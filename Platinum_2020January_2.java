import java.io.*;
import java.util.*;

public class Platinum_2020January_2 {
    public static long[][] numbers;
    public static ArrayList<Integer> sequence;
    public final static long MOD = 1000000007; //10^9+7
    public static long numSequences(List<Integer> array) {
        if (array.isEmpty()) {
            return 1;
        }
        long sum = 1;
        for (int i = 0; i < array.size(); i++) {
            List<Integer> temp = new ArrayList<>();
            int a = array.get(i);
            for (int j = i + 1; j < array.size(); j++) {
                int c = array.get(j);
                if (c >= a) {
                    temp.add(c);
                }
            }
            //System.out.println(temp);
            sum += numSequences(temp);
            sum = sum%MOD;
        }
        return sum%MOD;
    }
    public static void main(String[] args) throws IOException {
        //long time = System.currentTimeMillis();
        
        BufferedReader f = new BufferedReader(new FileReader("nondec.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nondec.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        //int K = Integer.parseInt(st.nextToken());
        numbers = new long[N][N];
        
        st = new StringTokenizer(f.readLine());
        
        sequence = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sequence.add(Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < N; i++) {
            numbers[i][i] = 2;
        }
        
        for (int size = 2; size <= N; size++) {
            for (int i = 0; i <= N - size; i++) {
                numbers[i][i+size-1] = (numbers[i][i+size-2] + numbers[i+1][i+size-1] - Math.max(1, numbers[i+1][i+size-2]))%MOD;
                int a = sequence.get(i);
                int b = sequence.get(i + size - 1);
                if (a <= b) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = i + 1; j < i + size - 1; j++) {
                        int c = sequence.get(j);
                        if (a <= c && c <= b) {
                            temp.add(c);
                        }
                    }
                    numbers[i][i+size-1] = (numbers[i][i+size-1]+numSequences(temp))%MOD;
                }
                //System.out.println(i + " " + (i + size - 1) + " " + numbers[i][i + size - 1]);
            }
        }
        
        int Q = Integer.parseInt(f.readLine());
        
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //System.out.println(sequence.subList(a - 1, b));
            out.println(numbers[a - 1][b - 1]);
        }
        
        out.close();
        
        //System.out.println("Seconds: " + (System.currentTimeMillis() - time)/1000.0);
    }
}
