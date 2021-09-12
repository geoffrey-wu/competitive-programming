import java.io.*;
import java.util.*;

public class December2018_Bronze_3 {
    public static int[] copy(int[] array) {
        int[] a = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            a[i] = array[i];
        }
        return a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
        
        boolean[] amounts = new boolean[2001];
        
        int[] buckets1 = new int[11];
        int[] buckets2 = new int[12];
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        for(int i = 0; i < 10; i++) {
            buckets1[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < 10; i++) {
            buckets2[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int a0 = 0; a0 < 10; a0++) {
            for(int a1 = 0; a1 < 11; a1++) {
                for(int a2 = 0; a2 < 11; a2++) {
                    for(int a3 = 0; a3 < 12; a3++) {
                        int[] sizes1 = copy(buckets1), sizes2 = copy(buckets2);
                        int tank1 = 1000;
                            
                            tank1 -= sizes1[a0];
                            sizes2[10] = sizes1[a0];
                            sizes1[a0] = 0;
                            
                            tank1 += sizes2[a1];
                            sizes1[10] = sizes2[a1];
                            sizes2[a1] = 0;
                            
                            if(sizes1[a2] == 0) continue;
                            
                            tank1 -= sizes1[a2];
                            sizes2[11] = sizes1[a2];
                            sizes1[a2] = 0;
                            
                            if(sizes2[a3] == 0) continue;
                            tank1 += sizes2[a3];
                            amounts[tank1] = true;
                    }
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i < amounts.length; i++) {
            if(amounts[i]) {
                count++;
                System.out.println(i);
            }
        }
        
        out.println(count);
        out.close();
    }
}
