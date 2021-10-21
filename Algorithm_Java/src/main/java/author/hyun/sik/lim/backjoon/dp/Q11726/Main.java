package author.hyun.sik.lim.backjoon.dp.Q11726;

import java.util.Scanner;

public class Main {
    // 2*n 타일링
    // n을 입력하여
    // 타일링 할 수 있는 경우의 수를 구하라 (경우의 수가 10007 이상일때 10007로 나눈 나머지 값으로 나타내라)!
    // dfs 대신 dp로 해결할것!
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solution(N));
    }

    private static long solution(int N) {
        // TODO Auto-generated method stub
        // 1 * 2 와 2 * 1 타일이 주어졌을때
        // 직접 연습장에 그리서 타일을 그려보면 규칙이 주어진다
        // dp[0] = 0 (아무것도 타일에 못붙이는 경우, 즉 타일이 필요 없을 시)
        // dp[1] = 1
        // dp[2] = 2
        // dp[3] = 3
        // dp[4] = 5
        // dp[5] = 8
        // dp[n] = dp[n-1] + dp[n-2]
        // 점화식이 세워진다
        long[] dp = new long[N+1];
        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[i] = 1;
                continue;
            }
                
            if (i == 2) {
                dp[i] = 2;
                continue;
            }
            
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            
        }
        return dp[N] ;
    }

}
