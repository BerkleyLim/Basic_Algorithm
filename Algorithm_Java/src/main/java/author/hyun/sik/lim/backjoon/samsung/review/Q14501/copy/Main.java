package author.hyun.sik.lim.backjoon.samsung.review.Q14501.copy;

import java.util.Scanner;

// 퇴사 문제
// 퇴사전 상담을 많이 해서 금액을 최대한으로 끌어 들일려는 문제
// 오늘부터 N + 1 퇴사 예정
// 그 안에 돈을 최대한 모을 것
// 상담 일정표
// T : 상담 처리 시간 (단위 : 일)
// P : 수당
// 전제 조건 : 그날에서 T를 더할 때 항상 N범위 안에 있을 것

public class Main {
    static int[] T;
    static int[] P;
    static int N;
    static int[] dp;
    static int payResult;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 알고리즘 시작
        N = sc.nextInt();
        payResult = 0;
        
        T = new int[N+10];
        P = new int[N+10];
        dp = new int[N+10];
        for (int day = 1; day <= N; day++) {
            T[day] = sc.nextInt();
            P[day] = sc.nextInt();
        }
        
        for (int day = 1; day <= N+1; day++) {
            dp[day] = Math.max(dp[day], payResult);
            dp[T[day]+day] = Math.max(dp[T[day]+day], P[day]+dp[day]);
            payResult = Math.max(payResult, dp[day]);
        }
        
        System.out.println(payResult);
        // 알고리즘 종료
        sc.close();
    }

}
