package author.hyun.sik.lim.backjoon.dp.Q15486;

import java.util.Scanner;

public class Main {
    // 퇴사 2
    // 퇴사전 오늘부터 N +`1일 동안 최대한 많이 상담한다
    // 각 상담을 완료하는데 걸리는 기간 및 상담 했을때 받을 수 있는 금액이 주어짐
    // 상담을 최대한 많이 해서 최대 이윤 얻을 수 있는 방법
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] T = new int[N+2];
        int[] P = new int[N+2];
        
        for (int i = 1; i < N+1; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }
        
        System.out.println(solution(N, T, P));
    }

    private static int solution(int N, int[] T, int[] P) {
        // TODO Auto-generated method stub
        
        int[] dp = new int[N+2];
        int maxPrice = -100;
        
        for (int i = 1; i <= N+1; i++) {
            maxPrice = Math.max(maxPrice, dp[i]);
            
            if (i + T[i] <= N+1) {
                dp[i+T[i]]= Math.max(dp[i+T[i]], maxPrice + P[i]);
//                System.out.println(dp[i+T[i]]);
            }
        }
        
        return maxPrice;
    }

}
