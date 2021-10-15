package author.hyun.sik.lim.sw.expert.academy.Q12223.not.solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    // 감시 카메라
    // N : 카메라를 해킹한 갯수
    // (x1,x2,y1,y2)
    // 순열과 조합 문제
    // 경우의 수
    
    static class Info {
        int startNode;
        int endNode;
        int x1;
        int x2;
        int y1;
        int y2;
    }
    
    static int answer;
    static int[] x1;
    static int[] x2;
    static int[] y1;
    static int[] y2;
    
    static int tempX1;
    static int tempX2;
    static int tempY1;
    static int tempY2;
    
    static Queue<Info>  queue;
    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
//            int[][] boards = new int[1001][1001];
            answer = 0;
            queue = new LinkedList<>();
            int N = sc.nextInt();
            x1 = new int[N];
            x2 = new int[N];
            y1 = new int[N];
            y2 = new int[N];
            
            for (int i =0; i < N; i++) {
                x1[i] = sc.nextInt();
                y1[i] = sc.nextInt();
                x2[i] = sc.nextInt();
                y2[i] = sc.nextInt();
            }
            
            permutation(1,0,N,x1[0],y1[0],x2[0],y2[0]);
            System.out.println("#" + test_case + " " + answer);
        }
    }
    
    // 2) 순열, 조합 알고리즘 (dfs 방식)
    private static void permutation(int depth, int i, int N, int x11, int y11, int x22, int y22) {
        // TODO Auto-generated method stub
//        System.out.println(combination);
        if (3 == depth) {
            answer++;
            return;
        } 
        
        for (int x = i+1; x < N; x++) {
            if (N-i == 1 && depth == 1) {
                break;
            }
            tempX1 = 0;
            tempX2 = 0;
            tempY1 = 0;
            tempY2 = 0;
            
            if(isTrue(x, i)) {
                x11 = tempX1;
                x22 = tempX2;
                y11 = tempY1;
                y22 = tempY2;
                
//                permutation(depth + 1, x, N, x11,y11,x22,y22);
            } else {
                continue;
            }
            
        }
    
        
    }

    private static boolean isTrue(int x, int i) {
        // TODO Auto-generated method stub
        
        
        // x의 대하여
        if (x1[i] == x1[x]) {
            tempX1 = x1[i];
            
            if (x2[x] == x2[i]) {
                tempX2 = x2[i];
            } else if (x2[x] < x2[i]) {
                tempX2 = x2[x];
            } else {
                tempX2 = x2[i];
            }
            
            
            
        } else if (x1[i] < x1[x]) {
            if (x1[x] > x2[i])
                return false;
            tempX1 = x1[x];
            
            if (x2[x] == x2[i]) {
                tempX2 = x2[i];
            } else if (x2[x] < x2[i]) {
                tempX2 = x2[x];
            } else {
                tempX2 = x2[i];
            }
            
        } else {
            if (x1[i] > x2[x])
                return false;
            tempX1 = x1[i];
            
            if (x2[x] == x2[i]) {
                tempX2 = x2[i];
            } else if (x2[x] < x2[i]) {
                tempX2 = x2[x];
            } else {
                tempX2 = x2[i];
            }
        }
        
        // y의 대하여
        if (y1[i] == y1[x]) {
            tempY1 = y1[i];
            
            if (y2[x] == y2[i]) {
                tempY2 = y2[i];
            } else if (y2[x] < y2[i]) {
                tempY2 = y2[x];
            } else {
                tempY2 = y2[i];
            }
            
        } else if (y1[i] < y1[x]) {
            if (y1[x] > y2[i])
                return false;
            tempY1 = y1[x];
            
            if (y2[x] == y2[i]) {
                tempY2 = y2[i];
            } else if (x2[x] < x2[i]) {
                tempY2 = y2[x];
            } else {
                tempY2 = y2[i];
            }
            
        } else {
            if (y1[i] > y2[x])
                return false;
            tempY1 = y1[i];
            
            if (y2[x] == y2[i]) {
                tempY2 = y2[i];
            } else if (x2[x] < x2[i]) {
                tempY2 = y2[x];
            } else {
                tempY2 = y2[i];
            }
        }
        
        return true;
        
    }
}
