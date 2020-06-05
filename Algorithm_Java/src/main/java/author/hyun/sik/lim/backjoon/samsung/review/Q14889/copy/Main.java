package author.hyun.sik.lim.backjoon.samsung.review.Q14889.copy;

import java.util.Scanner;

// 스타트와 링크 문제
// 여기서 다시 풀어보기
// 축구를 할 사람 : 총 N명
// N은 짝수
// N/2명으로 이루어진 스타트 팀과 링크 팀으로 나누기
// 스타트 팀 vs 링크 팀
// Sij 배열 구성
// N이 4명이면
// 1,2 vs 3,4 or 1,3 vs 2,4 or 1,4 vs 2,3
// 여기서 스타트팀의 능력치와 링크 팀의 능력치의 차이를 최소화!

// 입력 : 
// 첫째 줄 - N(4 <= N <= 20, N은 짝수)
// 츨째 줄 - 각각의 능력치

// 이것은 DP 기반으로 응용하는 문제
// dfs 문제

public class Main {
    static int N; // 사람 수
    static int[][] map; // 각 능력치
    static int balance; // 스타트 vs 링크 능력치
    static boolean[] isPick;    // 팀 선택 (visited 개념으로 생각해라)
    static int[] startTeam;     // 스타트 팀
    static int[] linkTeam;      // 링크 팀
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 알고리즘 시작
        N = sc.nextInt();
        map = new int[N][N];
        isPick = new boolean[N];
        balance = 99999999;
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = sc.nextInt();
            }
            
        }
        
        // 처음 시작, pick 횟수 
        dfs(0,0);
        System.out.println(balance);
    }
    
    // dfs 공식
    private static void dfs(int root, int pickCount) {
        // 팀 정했을 때
        if (pickCount == N/2) {
            team(); // 로직
            return;
        }
        
        // 1 부터 20까지 팀 고르기!
        for (int i = root; i < N; i++) {
            isPick[i] = true;
            dfs(i + 1, pickCount + 1);
            isPick[i] = false;
        }
    }
    
    
    // 기본 알고리즘 : Team 배치 완료 가정하에 의해 작성
    // 여기서 최소값을 구한다!
    private static void team() {
        // TODO Auto-generated method stub
        startTeam = new int[N/2];
        linkTeam = new int[N/2];
        
        int startTeamSize = 0;
        int linkTeamSize = 0;
        
        for (int i = 0; i < N; i++) {
            if(isPick[i]) {
                startTeam[startTeamSize++] = i;
            } else {
                linkTeam[linkTeamSize++] = i;
            }
        }
        
        int startTeamResult = 0;
        int linkTeamResult = 0;
        
        for (int y = 0; y < N/2; y++) {
            for (int x = y + 1; x < N/2; x++) {
                startTeamResult += 
                        map[startTeam[y]][startTeam[x]] 
                                + map[startTeam[x]][startTeam[y]] ;
                linkTeamResult += map[linkTeam[y]][linkTeam[x]] 
                        + map[linkTeam[x]][linkTeam[y]];
            }
        }
        
        balance = Math.min(balance, Math.abs(startTeamResult-linkTeamResult));
    }
}
