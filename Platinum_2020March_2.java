import java.io.*;
import java.util.*;

public class Platinum_2020March_2 {
    public static long[] factorials;
    public static long mod;
    public static long powerMod(long a, long b) {
        if (b == 0) return 1;
        long result = a;
        String binary = Long.toBinaryString(b);
        for (int i = 1; i < binary.length(); i++) {
            result = (result * result) % mod;
            if (binary.charAt(i) == '1') result = (result * a) % mod;
        }
        return result;
    }
    public static long permutation(long n, long x) {
        long result = 1;
        for (int i = 0; i < x; i++) {
            result = (result * (n - i)) % mod;
        }
        
        return result;
    }
    public static long gcd(long a, long b) {
        if (a == 0 || b == 0) return a+b;
        return gcd(b, a%b);
    }
    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a * b) / gcd(a, b);
    }
    public static void arrayProduct(int steps, HashMap<Integer, Long> map, HashMap<Integer, Long> array2, int n) {
        long value = permutation(n - 1, steps - 1);
        
        //System.out.println(result + " " + array + " ");
        for (int i : array2.keySet()) {
            add(map, (int)lcm(i, steps), (array2.get(i) * value) % mod);
        }
    }
    
    public static void add(HashMap<Integer, Long> map, int index, long value) {
        map.put(index, (value + map.getOrDefault(index, new Long("0"))) % mod);
    }
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        
        BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\geoff\\Documents\\filein.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\geoff\\Documents\\fileout.txt")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        mod = Integer.parseInt(st.nextToken()) - 1;
        
        factorials = new long[N];
        factorials[0] = 1;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = (factorials[i - 1] * i) % mod;
        }
        
        System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
        
        HashMap<Integer, Long>[] numberOfWays = new HashMap[N+1];
        for (int i = 0; i < numberOfWays.length; i++) {
            numberOfWays[i] = new HashMap<>();
        }
        
        System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
        
        numberOfWays[1].put(1, new Long("1"));
        for (int n = 2; n <= N; n++) {
            for (int i = 1; i < n; i++) { //taking the first i terms
                arrayProduct(i, numberOfWays[n], numberOfWays[n - i], n);
                //System.out.println(n + " " + Arrays.toString(numberOfWays[n]));
            }
            add(numberOfWays[n], n, factorials[n - 1]);
            //System.out.println(n + " " + numberOfWays[n]);
            //out.println(makeString(numberOfWays[n]));
        }
        
        //System.out.println(numberOfWays[N]);
        
        System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
        
        mod++;
        long answer = 1;
        HashMap<Integer, Long> map = numberOfWays[N];
        for (int i : map.keySet()) {
            answer = (answer * powerMod(i, map.get(i))) % mod;
        }
        
        System.out.println(answer);
        out.println(answer);
        
        out.close();
        
        System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
    }
}
