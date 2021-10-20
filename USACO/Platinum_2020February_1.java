import java.io.*;
import java.util.*;

public class Platinum_2020February_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("deleg.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
        
        int N = Integer.parseInt(f.readLine());
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        ArrayList<Integer> branchLengths = new ArrayList<>();
        
        String s;
        while ((s = f.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        int head = -1;
        
        for (int i = 1; i <= N; i++) {
            if (tree.get(i).size() > 2) {
                head = i;
                break;
            }
        }
        
        if (head == -1) {
            System.out.println(N);
            out.print(N);
        } else {
            for (int i = 0; i < tree.get(head).size(); i++) {
                int j = tree.get(head).get(i);
                int k = head;
                int counter = 1;
                
                while (tree.get(j).size() == 2) {
                    int x = j;
                    j = tree.get(j).get(0) + tree.get(j).get(1) - k;
                    k = x;
                    counter++;
                }
                branchLengths.add(counter);
                //System.out.println(counter);
            }
        }
        Object[] branchLengths1 = branchLengths.toArray();
        int[] branchLengths2 = new int[branchLengths1.length];
        for (int i = 0; i < branchLengths1.length; i++) {
            branchLengths2[i] = (int)branchLengths1[i];
        }
        
        Arrays.sort(branchLengths2);
        
        //System.out.println(branchLengths2[0]);
        
        int min = Integer.MAX_VALUE;
        
        if (branchLengths2.length%2 == 1) {
            min = branchLengths2[branchLengths2.length - 1];
            for (int j = 0; j <= branchLengths2.length - 1 - j; j++) {
                min = Math.min(min, branchLengths2[j] + branchLengths2[branchLengths2.length - 1 - j]);
            }
        } else {
            for (int j = 0; j <= branchLengths2.length - 1 - j; j++) {
                min = Math.min(min, branchLengths2[j] + branchLengths2[branchLengths2.length - 1 - j]);
            }
        }
        
        System.out.println(min);
        out.println(min);
        
        //System.out.println(head);
        out.close();
    }
}
