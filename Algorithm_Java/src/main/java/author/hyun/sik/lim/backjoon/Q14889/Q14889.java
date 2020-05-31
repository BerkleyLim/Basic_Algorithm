package author.hyun.sik.lim.backjoon.Q14889;

import java.util.Scanner;

// https://www.acmicpc.net/problem/14889
// 스타트와 링크

public class Q14889 {
    // N :사람 번호! (단 반드시 짝수)
    private static int N;
    private static int[][] teamAbility;
    private static boolean[] isPick;
    private static int[] team1;
    private static int[] team2;
    
    // 이 값이 능력치 차이를 알 수 있다 (즉, 결과값)
    private static int balence = 9999;
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 첫째줄 입력
        N = sc.nextInt();
        teamAbility = new int[N][N];
        isPick = new boolean[N];
        
        // 둘째줄 입력 (i = 열 / j = 행)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                teamAbility[i][j] = sc.nextInt();
            }
        }
        
        // 여기는 dfs 알고리즘을 이용하여 N/2 만큼 재귀 호출한다.
        // 트리로 세워 놓으면 0을 루트로 잡고 순회하는 것을 확인 할 수 있다!
        // 이와 동시에 dfs 알고리즘을 활용하여 team1과 team2를 나눴을 때의 연산을 사용 할 수 있다.
        dfs(0,0);
        System.out.println(balence);
        balence = 9999;
    }
    
    public static void teamDevice() {
        team1 = new int[N/2];
        team2 = new int[N/2];
        
        int team1Size = 0;
        int team2Size = 0;
        
        // 여기서는 Team1과 Team2를 나누기 위해 실행한다.
        for (int i = 0 ; i < N; i++) {
            if (isPick[i]) {
                team1[team1Size++] = i;
            } else {
                team2[team2Size++] = i;
            }
        }
        
        int sum1 = 0;
        int sum2 = 0;
        
        // 능력치 계산
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                 sum1 += (teamAbility[team1[i]][team1[j]] 
                         + teamAbility[team1[j]][team1[i]]); 
                 sum2 += (teamAbility[team2[i]][team2[j]] 
                         + teamAbility[team2[j]][team2[i]]);
            }
        }
        
        if (balence > Math.abs(sum1 - sum2)) {
            balence = Math.abs(sum1 - sum2);
        }
        
    }
    
    // dfs 알고리즘 생성! (일종의 공식 중 하나)
    public static void dfs(int root, int pickCount) {
        if (pickCount == (N /2)) {
            // 여기서 N개 중에서 반만큼 골랐을 경우, isPick 배열대로 결정해본다.
            teamDevice(); //
            return;
        }
        
        for (int i = root; i < N; i++) {
            // 이미 고른것!
            isPick[i] = true;
            dfs(i + 1, pickCount + 1);
            // 다 고른 후, 다시 돌기 위해!
            isPick[i] = false;
        }
        
    }
}
