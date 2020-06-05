package author.hyun.sik.lim.backjoon.samsungSW.backjoon12869;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution{
    static int N;
    static int[] scv;
    final static int[][] damage = {{-9,-3,-1},
                                   {-9,-1,-3},
                                   {-3,-9,-1},
                                   {-3,-1,-9},
                                   {-1,-3,-9},
                                   {-1,-9,-3}};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        
        scv = new int[N];
        
        for (int i = 0; i < N; i++) {
            scv[i] = sc.nextInt();
        }
        
        System.out.println(attack(scv, N));
    }
    
    public static int attack(int[] scv, int N) {
        
        int count = 9999;
        int[] arr = new int[N];
        int length = N;
        for (int i = 0; i < 6; i++) {
            if (N == 2) {
                if (i != 0 || i != 2)
                    continue;
            } else if (N == 1) {
                if (i != 0)
                    continue;
            } else {}
            int maxNumber = -9999;
            for (int j = 0; j < N; j++) {
                arr[j] = scv[j] + damage[i][j];
                maxNumber = Math.max(maxNumber, arr[j]);
                
                if (!(j == length - 1) && maxNumber < 1) {
                    length--;
                    for (int temp = 0; temp < length; temp++) {
                        arr[j] = arr[j+1];
                    }
                    arr[length] = -1;
                } else if ((j == length - 1) && maxNumber < 1) {
                    length--;
                }
            }
            if (maxNumber < 1) {
                return 1;
            }
            int sum = attack(arr, length) + 1;
            System.out.println(sum);
            count = Math.min(count, sum);
        }
        
        
        
        return count;
    }
}
