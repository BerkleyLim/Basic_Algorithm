package author.hyun.sik.lim.backjoon.dp.Q1520;

import java.util.Scanner;

public class Main {
    // 내리막길
    // 지도가 주어지고, 각 지점 사이에 이동은 지도에서 상하좌우 이웃한 곳 끼리 이동 가능
    // 각 칸에 지점의 높이가 주어짐
    // 현재 위치보다 더 낮은 지점으로만 이동한다
    // 이동 가능한 경우의 수를 구하라!
    static int[][] dp;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] boards = new int[M][N];
        dp = new int[M][N];
        
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                boards[y][x] = sc.nextInt();
                dp[y][x] = -1; // dp를 0이 아닌 -1로 한 후에 아예 목적지에 도달 불가능하게 한다 (재귀 호출 최소화)
                               // 초기값 0일 경우 현재 위치에서 목적지가 아예 없을 경우 방지용
            }
        }
        
//        long start = System.currentTimeMillis(); //시작하는 시점 계산
        
        /*
        실행시간을 측정하고싶은 코드
        */
        System.out.println(solution(M,N,boards));
         
//        long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
//        System.out.println( "실행 시간 : " + (double)( end - start ) / 1000  + "초"); //실행 시간 계산 및 출력

    }

    final static int[] dx = {1,0,-1,0};
    final static int[] dy = {0,-1,0,1};
    private static int solution(int M, int N, int[][] boards) {
        // TODO Auto-generated method stub
        
        return backTracking(M,N,boards,0,0);
    }
    
    private static int backTracking(int M, int N, int[][] boards, int curX, int curY) {
        if (M - 1 == curY && N - 1 == curX) {
            return 1;
        }
        
        // 가지치기 용도(먼저 경로로 이동한 위치가 있을 때 굳이 이동할 필요 없음)
        // 초기값을 0으로 두나 -1로 두나 차이가 크다
        // 즉 매번 for문을 사용할거를 한번으로 지정하기 위해
        if (dp[curY][curX] != -1) return dp[curY][curX];
        //if (dp[curY][curX] > 0) return dp[curY][curX];
        dp[curY][curX] = 0;
        
        for (int i = 0; i < dx.length; i++) {
            int x = curX + dx[i];
            int y = curY + dy[i];
            
            if (x >= 0 && x < N && y >= 0 && y < M) {
                if (boards[y][x] < boards[curY][curX]) {
                    dp[curY][curX] += backTracking(M, N, boards, x, y);
                }
            }
        }
        
        return dp[curY][curX];
    }
    
}
