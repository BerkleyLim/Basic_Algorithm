package author.hyun.sik.lim.sw.expert.academy.Q2105;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    // 디저트 카페
    // 각 칸에 모두 디저트 카페에 모ㅁ : N * N 정사각형으로 구성
    // 각 칸은 해당 디저트 카페에서 팔고 있는 디저트의 종류
    // 카페들 사이에서 대각선 방향으로 움직임 가능
    // 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        /*
           여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
        */

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[][] boards = new int[N][N];
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    boards[y][x] = sc.nextInt();
                }
            }
            max = -1;
            count = 0;
            visited = new boolean[N][N];
            set = new HashSet<>();
            
            for (int y = 0; y < N -2; y++) {
                for (int x = 1; x < N -1; x++) {
                    visited = new boolean[N][N];
                    set.clear();
                    
                    visited[y][x] = true;
                    set.add(boards[y][x]);
                    dfs(N, boards, y, x, y, x, 0);
                    set.remove(boards[y][x]);
                    visited[y][x] = false;
                }
            }
            
            System.out.println("#" + test_case + " " + max);
        }
    }

    static boolean[][] visited;
    static final int[] dy = {1,1,-1,-1};
    static final int[] dx = {1,-1,-1,1};
    static int max;
    static int count;
    static Set<Integer> set;
    private static void dfs(int N, int[][] boards, int startedY, int startedX, int y1, int x1, int curve) {
        for (int d = curve; d < 4; d++) {
            int x = x1 + dx[d];
            int y = y1 + dy[d];
            if (startedY == y && startedX == x && set.size() >= 3) {
                count = set.size();
                max = max < count? count : max;
                return;
            }
            if (x < 0 || y < 0 || x >= N || y >= N || visited[y][x]) {
                continue;
            }
            
            if (set.contains(boards[y][x]))
                continue;
            
//            System.out.println(startedX + ", " + startedY + " : " + x1 + ", " + y1);
            visited[y][x] = true;
            set.add(boards[y][x]);
            dfs(N, boards, startedY, startedX, y, x, d);
            set.remove(boards[y][x]);
            visited[y][x] = false;
        }
    }
    
}
