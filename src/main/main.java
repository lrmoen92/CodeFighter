package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Logan.Moen on 7/11/2017.
 */
public class main {

    public static void main(String[] args) {

        char[][] grid = new char[][]
                    {
                        new char[]{'.', '.', '5', '.', '.', '.', '.', '.', '.'},
                        new char[]{'1', '.', '.', '2', '.', '.', '.', '.', '.'},
                        new char[]{'.', '.', '6', '.', '.', '3', '.', '.', '.'},
                        new char[]{'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                        new char[]{'3', '.', '1', '5', '2', '.', '.', '.', '.'},
                        new char[]{'.', '.', '.', '.', '.', '.', '.', '4', '.'},
                        new char[]{'.', '.', '6', '.', '.', '.', '.', '.', '.'},
                        new char[]{'.', '.', '.', '.', '.', '.', '.', '9', '.'},
                        new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    };
        if(sudoku(grid))
            System.out.println("Aww yeah");
        else
            System.out.println("Aww nooo");
    }

    public static boolean sudoku(char[][] grid){
        for(int i = 0; i < 9; i++){
            List<Character> list = new ArrayList();
            for(int j = 0; j < 9; j++) {
                list.add(j, grid[i][j]);
            }
            List<Character> newlist = list.stream().filter(z -> !(z == '.')).collect(Collectors.toList());
            int z = newlist.size();
            for(int c = 0; c < z; c++){
                char d = newlist.get(0);
                newlist.remove(0);
                if(newlist.contains(d)){
                    return false;
                }
            }
        }

        for(int i = 0; i < 9; i++){
            List<Character> list = new ArrayList();
            for(int j = 0; j < 9; j++) {
                list.add(j, grid[j][i]);
            }
            List<Character> newlist = list.stream().filter(z -> !(z == '.')).collect(Collectors.toList());
            int z = newlist.size();
            for(int c = 0; c < z; c++){
                char d = newlist.get(0);
                newlist.remove(0);
                if(newlist.contains(d)){
                    return false;
                }
            }
        }

        for(int i = 0; i < 3; i++){
            for(int h = 0; h < 3; h++) {
                List<Character> list = new ArrayList();
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        list.add((j*3) + k, grid[(h*3) + j][(i*3) + k]);
                    }
                }
                List<Character> newlist = list.stream().filter(z -> !(z == '.')).collect(Collectors.toList());
                int z = newlist.size();
                for (int c = 0; c < z; c++) {
                    char d = newlist.get(0);
                    newlist.remove(0);
                    if (newlist.contains(d)) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    public static int[][] rotateImage(int[][] a){
        int s = a.length;
        int[][] b = new int[s][s];
        for(int i = 0; i < s; i++){
            for(int j = 0; j < s; j++){
                b[i][j] = a[s-j-1][i];
            }
        }
        return b;
    }

    public static boolean mageParade(int w, int f, int d) {
        return 3*w+2*d+3>f&f+2>d;
    }

    public static boolean mageParade2(int water, int fire, int death){
        boolean canHave = false;

        if(water+fire+death != 0) {
            if (fire > (water*3)+(death*2)+3)
                canHave = false;
            else if (death >= (fire + water) * 2 && fire+water != 0)
                canHave = false;
            else if(death * 2 > water + fire && death != 1)
                canHave = false;
            else
                canHave = true;
        }else{
            canHave = false;
        }
        return canHave;
    }

    public static int mazeEscape(int w, int h, int[] impassable){
        List<Integer> index1 = new ArrayList();
        List<Integer> index2 = new ArrayList();

        for(int x = 0; x < impassable.length; x++){
            if(x%2 == 0){
                index1.add(impassable[x]);
            }else if(x%2 != 0){
                index2.add(impassable[x]);
            }
        }

        return -1;
    }

    public static char firstNotRepeatingCharacter(String s){
        char c;
        HashSet hs = new HashSet();
        List<Character> list = new ArrayList<Character>();
        char[] sAr = s.toCharArray();
        for(int i = sAr.length - 1; i >= 0; i--){
                if(!hs.contains(sAr[i])){
                    hs.add(sAr[i]);
                    list.add(sAr[i]);
                } else if(hs.contains(sAr[i])){
                    list.remove(new Character(sAr[i]));
                }
        }
        if(list.isEmpty()){
            c = '_';
        }else {
            c = list.get(list.size() - 1);
        }
        return c;
    }

    public static int[] racers(int track, int[] accel, int[] spd){

        float[] time = new float[accel.length];
        int[] res = new int[accel.length];

        for (int i = 0; i < accel.length; i++) {
            int t = 0;
            int curSpd = 0;
            float s = 0;
            while (t < track) {
                if(accel[i] > track) {
                    s += (track/accel[i]);
                    curSpd += accel[i];
                }else{
                    if (accel[i] > spd[i] && curSpd != spd[i]) {
                        s += (spd[i] / accel[i]);
                    } else {
                        s++;
                    }
                    if (curSpd < spd[i]) {
                        curSpd += accel[i];
                        if (curSpd > spd[i]) {
                            curSpd = spd[i];
                        }
                    }
                }
                t += curSpd;
            }
            time[i] = s;
        }
        HashSet ties = new HashSet();
        int tieCounter = 0;
        for (int i = 0; i < time.length; i++) {
            List slower = new ArrayList();
            for (int j = 0; j < time.length; j++) {

                if (time[i] > time[j] && i != j) {
                    slower.add(i);
                }
                if (time[i] == time[j] && i != j) {
                    ties.add(i);
                }
            }
            for (int k = 0; k < time.length; k++) {
                if (slower.size() == k && ties.contains(i) && tieCounter == 0) {
                    res[i] = k;
                    ties.remove(i);
                    tieCounter++;
                } else if (slower.size() == k && tieCounter == 0) {
                    res[i] = k;
                } else if (slower.size() == k) {
                    res[i] = k + tieCounter;
                }
            }
        }

        for (int x = 0; x < res.length; x++) {
            System.out.println(res[x]);
        }

        return res;
    }

    public static int firstDuplicate(int[] a){
        HashSet numbersCounted = new HashSet();
        int count = 0;
        int solution = 0;
        for(int i = 0; i < a.length; i++){
            numbersCounted.add(a[i]);
            if(!(numbersCounted.size() == i + 1 - count)){
                if(count == 1){
                    return solution;
                }
                solution = a[i];
                count++;
            }

        }


        return -1;
    }
}
