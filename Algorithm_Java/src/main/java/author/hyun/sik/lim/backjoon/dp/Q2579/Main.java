package author.hyun.sik.lim.backjoon.dp.Q2579;

import java.util.Scanner;

public class Main {
    // 계단 오르기
    // 걔던울 1계단 or 2계단씩 올라갈 수 있다.
    // 여기서 총 점수의 최댓값을 구하는 프로그램 작성
    // 단, 연속 3번 이상 올라가기 불가능
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] stair = new int[N];
        for (int i = 0; i < N; i++)
            stair[i] = sc.nextInt();
        
        System.out.println(solution(N, stair));
    }

    private static int solution(int N, int[] stair) {
        // TODO Auto-generated method stub
        
        int[] dp = new int[N];
        /*
         *  1. 마지막 계단 전의 계단을 밟은 경우.
            2. 마지막 계단 전의 계단을 밟지 않은 경우.
        */
        // dp[n] = dp[n-3] + stair[n-1] + stair[n]
        // dp[n] = dp[n-2] + stair[n]
        
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dp[i] = stair[i];
                continue;
            }
            
            if (i == 1) {
                dp[i] = Math.max(stair[i] + stair[i-1], stair[i]);
                continue;
            }
            
            if (i == 2) {
                dp[i] = Math.max(dp[i-2] + stair[i], stair[i-1] + stair[i]);
                continue;
            }
            
            dp[i] = Math.max(dp[i-2] + stair[i], dp[i-3] + stair[i] + stair[i-1]);
        }
        
        return dp[N-1];
    }

}
