package author.hyun.sik.lim.backjoon.samsung.basicSDS.Q11399;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ATM 알고리즘
// 대기시간 짧은 알고리즘 작성

public class Main {
    static int N;
    static int[] P;
    static int min;
    static boolean[] isPick;
    static int sum;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        P = new int[N];
        isPick = new boolean[N];
        sum = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        //min = Integer.MAX_VALUE;
        //dfs(0,0);
        
        Arrays.sort(P);
        
        min = 0;
        for (int i = 0; i < N; i++) {
            min += P[i] * (N - i);
        }
        System.out.println(min);
        br.close();
    }
    
    /*
    private static void dfs(int x, int count) {
        if (count == N) {
            min = Math.min(min, sum);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!isPick[i] && min >= sum) {
                isPick[i] = true;
                sum += P[i] * (N - count);
                dfs(i, count+1);
                sum -= P[i] * (N - count);
                isPick[i] = false;               
            }

        }
    }
    */
}