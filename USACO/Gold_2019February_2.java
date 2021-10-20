
import java.io.*;
import java.util.*;

public class February2019_Gold_2 {
    public static ArrayList<ArrayList<Integer>> soapyStacks = new ArrayList<>();
    public static boolean tryToAdd(int a) {
        if (soapyStacks.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(a);
            soapyStacks.add(temp);
            System.out.println("empty");
            return true;
        }
        for (ArrayList<Integer> stack : soapyStacks) {
            if (a < stack.get(stack.size() - 1)) {
                stack.add(a);
                System.out.println("top" + stack.get(stack.size() - 2));
                return true;
            }
            if (a < stack.get(0)) {
                break;
            }
        }
        int worst = soapyStacks.get(soapyStacks.size() - 1).get(0);
        if (a > worst) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(a);
            soapyStacks.add(temp);
            System.out.println("worst");
            return true;
        }
        return false;   
    }
    public static int remove(int a) {
        System.out.println("remove");
        int b = -1;
        while (soapyStacks.isEmpty() == false) {
            ArrayList<Integer> temp = soapyStacks.get(0);
            if (temp.get(temp.size() - 1) < a) {
                b = temp.get(temp.size() - 1);
                soapyStacks.remove(0);
            }
            else {
                while(temp.get(0) < a) {
                    b = temp.get(0);
                    temp.remove(0);
                }
                return b;
            }
        }
        return b;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dishes.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
        int N = Integer.parseInt(f.readLine());
        
        int[] dirtyStack = new int[N];
        for (int i = 0; i < N; i++) {
            dirtyStack[i] = Integer.parseInt(f.readLine());
        }
        
        boolean works = true;
        int topOfCleanStack = -1;
        int prefix = 0;
        for (int i = 0; i < N; i++) {
            if (dirtyStack[i] < topOfCleanStack){
                prefix = i;
                break;
            } else if (tryToAdd(dirtyStack[i]) == false) {
                System.out.println("beep");
                topOfCleanStack = remove(dirtyStack[i]);
                if (dirtyStack[i] < topOfCleanStack) {
                    prefix = i;
                    break;
                }
                tryToAdd(dirtyStack[i]);
            }
            System.out.println(dirtyStack[i] + " " + topOfCleanStack);
        }
        System.out.println(prefix);
        
        if (prefix == 0) prefix = N;
        out.println(prefix);
        
        out.close();
    }
}
