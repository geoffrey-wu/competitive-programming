
import java.io.*;
import java.util.*;

public class boxes extends Grader {
    
    //Stores whether or not two farms have a road in between them:
    private boolean[][] roads;
    //Stores the locations of the farms
    private int[][] farms;
    private int[] numChildren;
    private int N;
    private boolean[] checked;
    private int[] parent;
    
    public boxes() {
        System.out.println("hmm");
        N = getN();
        roads = new boolean[N][N];
        farms = new int[N][2];
        checked = new boolean[N];
        parent = new int[N];
    }
    
    //@Override
    public static void main(String args[]) throws IOException {
        boxes b = new boxes();
        b.run();
    }

    @Override
    public void addRoad(int a, int b) {
        roads[a][b] = roads[b][a] = true;
    }

    @Override
    public void buildFarms() {
        System.out.println("roads done");
        parent[0] = -1;
        findChildren(0);
        buildFarms(0, 1, 1);
    }
    
    private int findChildren(int n) {
        checked[n] = true;
        for (int i = 0; i < N; i++) {
            if (roads[n][i] && !checked[i]) {
                numChildren[n]++;
                numChildren[n] += findChildren(i);
                parent[i] = n;
            }
        }
        return numChildren[n];
    }
    
    private void buildFarms(int id, int x, int y) {
        setFarmLocation(id, x, y);
        farms[id] = new int[] {x, y};
        x = N;
        /*
        for (int i = 0; i < N; i++) {
            if (roads[id][i]) {
                x += numChildren[i] + 1;
            }
        }*/
        for (int i = 0; i < N; i++) {
            if (roads[id][i] && i != id && i != parent[id]) {
                x -= (numChildren[i] + 1);
                buildFarms(i, x, y);
                y += numChildren[i] + 1;
            }
        }
    }
    
    @Override
    public void notifyFJ(int a, int b) {
        if (a == 0 || b == 0) {
            addBox(farms[0][0], farms[0][1], farms[a+b][0], farms[a+b][1]);
            return;
        }
        int temp = b;
        ArrayList<Integer> parentB = new ArrayList<>();
        while (temp != -1) {
            parentB.add(temp);
            temp = parent[temp];
        }
        if (parentB.contains(a)) {
            addBox(farms[a][0], farms[a][1], farms[b][0], farms[b][1]);
            return;
        }
        temp = a;
        while (!parentB.contains(temp)) {
            temp = parent[temp];
        }
        if (temp == b) {
            addBox(farms[b][0], farms[b][1], farms[a][0], farms[a][1]);
            return;
        }
        addBox(farms[temp][0], farms[temp][1], farms[a][0], farms[a][1]);
        int previous = parentB.get(parentB.indexOf(temp) - 1);
        addBox(farms[previous][0], farms[previous][1], farms[b][0], farms[b][1]);
    }
}