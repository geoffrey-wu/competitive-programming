
import java.io.*;
import java.util.*;

public class March2019_Gold_3 {
    //public final static int CONST = 20;
    public static int N;
    public static int num1L, num2L, num1R, num2R;
    public static int ones1L, ones2L, ones1R, ones2R;
    public static int triangle(int a) {
        long b = a;
        return (int)((b*(b+1))/2);
    }
    public static void swap(boolean[] array, int i, int j) {
        boolean temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static int numberOfOnes(boolean[] array) {
        int count = 0;
        for (boolean b : array) {
            if(b) count++;
        }
        return count;
    }
    public static int numberOfInversions(boolean[] array) {
        ArrayList<Integer> ones = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if(array[i]) {
                ones.add(i);
            }
        }
        return numberOfInversions(ones, array.length);
    }
    //Determines the number of inversions given the indexes of where the 1s are
    //N is the size of the encompassing array
    public static int numberOfInversions(ArrayList<Integer> array, int N) {
        long total = 0;
        N--;
        for (int i : array) {
            total += N - i;
        }
        int size = array.size();
        total -= triangle(size - 1);
        return (int)total;
    }
    /*
    * There are two operations that matter:
    * 1) bring a 1 from the left side to the right side
    * 2) bring a 1 from the right side to the left side
    * after each operation, we can simply check the number of inversions and do |inversions1 - inversions2|
    */

    //returns the number of swaps needed to accomplish this
    public static int move1ToLeft(boolean[] array1, boolean[] array2) {
        int swapsNeeded = 0;
        
        int i = N - 1;
        //int ones = numberOfOnes(array1);
        if (ones1L == N) return -1;
        while (array1[i] == true) {
            i--;
            //if (i < 0) return -1;
        }
        swapsNeeded += N - 1 - i;
        num1L += swapsNeeded;
        num1L -= ones1L;
        array1[i] = true;
        
        /* Second array */
        i = 0;
        //ones = numberOfOnes(array2);
        if (ones2L == 0) return -1;
        while (array2[i] == false) {
            i++;
            //if (i >= N) return -1;
        }
        swapsNeeded += i;
        num2L += i;
        num2L -= N - ones2L;
        array2[i] = false;
        //System.out.println(swapsNeeded + 1 + "!");
        ones1L++;
        ones2L--;
        return swapsNeeded + 1;
    }
    public static int move1ToRight(boolean[] array1, boolean[] array2) {
        int swapsNeeded = 0;
        
        int i = N - 1;
        //int ones = numberOfOnes(array1);
        if (ones1R == 0) return -1;
        while (array1[i] == false) {
            i--;
            //if (i < 0) return -1;
        }
        swapsNeeded += N - 1 - i;
        num1R -= swapsNeeded;
        num1R += ones1R;
        array1[i] = false;
        
        /* Second array: */
        //ones = numberOfOnes(array2);
        if (ones2R == N) return -1;
        i = 0;
        while (array2[i] == true) {
            i++;
            //if (i >= N) return -1;
        }
        swapsNeeded += i;
        num2R -= i;
        num2R += N - ones2R;
        array2[i] = true;
        //System.out.println(swapsNeeded + 1 + "!");
        ones1R--;
        ones2R++;
        return swapsNeeded + 1;
    }
    
    public static void main(String[] args) throws IOException {
        //"C:\\Users\\Geoffrey\\Documents\\filein.txt"
        BufferedReader f = new BufferedReader(new FileReader("balance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
        N = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        boolean[] array1L = new boolean[N];
        boolean[] array1R = new boolean[N];
        for (int i = 0; i < N; i++) {
            boolean temp = st.nextToken().equals("1");
            array1L[i] = temp;
            array1R[i] = temp;
        }
        boolean[] array2L = new boolean[N];
        boolean[] array2R = new boolean[N];
        for (int i = 0; i < N; i++) {
            boolean temp = st.nextToken().equals("1");
            array2L[i] = temp;
            array2R[i] = temp;
        }
        
        ones1L = ones1R = numberOfOnes(array1L);
        ones2L = ones2R = numberOfOnes(array2L);
        num1L = num1R = numberOfInversions(array1L);
        num2L = num2R = numberOfInversions(array2L);
        
        /* The above code initializes all instance variables */
        
        //Do nothing between the two arrays; only swap within each array itself
        int bestTrySoFar = Math.abs(num1L - num2L);
        //System.out.println(bestTrySoFar);
        
        int currentTry = 0;
        int oldTry = Integer.MAX_VALUE;
        while(true) {
            int temp = move1ToLeft(array1L, array2L);
            if (temp == -1) break;
            currentTry += temp;
            temp = Math.abs(num1L - num2L);
            int temp2 = currentTry + temp;
            if (temp2 > oldTry) break;
            bestTrySoFar = Math.min(bestTrySoFar, temp2);
            //System.out.println(temp2 + " " + num1L + " " + num2L + " " + currentTry);
            oldTry = temp2;
            //if (temp2 > bestTrySoFar) break;
        }
        currentTry = 0;
        //oldTry = Integer.MAX_VALUE;
        while(true) {
            int temp = move1ToRight(array1R, array2R);
            if (temp == -1) break;
            currentTry += temp;
            temp = Math.abs(num1R - num2R);
            int temp2 = currentTry + temp;
            if (temp2 > oldTry) break;
            bestTrySoFar = Math.min(bestTrySoFar, temp2);
            //System.out.println(temp2 + " " + num1R + " " + num2R + " " + currentTry);
            oldTry = temp2;
            //if (temp2 > bestTrySoFar) break;
        }
        out.println(bestTrySoFar);
        out.close();
    }
}