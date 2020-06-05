package author.hyun.sik.lim.backjoon.samsungSW.Q14503;

import java.util.Scanner;

// 로봇 청소기!
// N * M 크기의 직사각형으로 주어짐
// 동, 서, 남, 북 으로 이동 가능
// 지도의 각 칸 : R, C 로 나타남
// R : 북쪽으로부터 떨어진 칸의 갯수
// C : 서쪽으로부터 떨어진 칸의 갯수

// 로봇 청소기는 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색 진행
// 청소 완료시, 다음 방향으로 회전 후 다음 한칸을 전진하여 실행
// 네방향 모두 청소가 이미 되어있거나 벽인 경우, 바라보는 방향을 유지한 채로  한 칸 후진하고 다시 실행
// 네 방향 모두 청소 되거나 벽이면서 뒤쪽 방향이 벽이라 후진도 할 수 없을 시 작동 중단
// 이미 청소한 칸을 또 청소하지 않고, 벽을 통과 안함

// 입력 조건 : 세로 크기 : N, 가로 크기 : M
// 첫째줄  : 3 ≤ N, M ≤ 50 (
// 둘째 줄 : 로봇 청소기가 있는 칸의 좌표 : (r, c) 방향 d가 주어짐
//     0 : 북쪽, 1 : 동쪽, 2 : 남쪽, 3 : 서쪽
// 셋째줄 : N개의 줄에 장소가 상태가 북쪽부터 남쪽 순서, 서쪽부터 동쪽 순서대로 주어짐
//     빈칸 : 0, 벽 : 1
//     로봇 청소기가 있는 칸의 상태는 항상 빈칸, 

// 참조 : https://heekim0719.tistory.com/347?category=816705
public class Solution {
    static int N; // 세로 크기
    static int M; // 가로 크기
    
    // 로봇 청소기 좌표 및 처음 방향
    static int r; // 로봇 청소기 세로 위치
    static int c; // 로봇 청소기 가로 위치
    static int d; // 로봇 청소기 방향
    
    // 북 동 남 서 방향 지정 (회전 좌표)
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};
    
    // 후진 좌표
    static final int[] bx = {1, 0, -1, 0};
    static final int[] by = {0, -1, 0, 1};    
    
    // 방문 여부 확인
    static boolean[][] visited;
    
    // 지도 정보
    static int[][] map;
    
    // 결과물 출력
    static int result;
    
    public static void main(String[] args) {
        // 입력 조건 수행
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        r = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        
        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                map[y][x] = sc.nextInt();
            }
        }
        visited = new boolean[N][M];
        
        // 입력 조건 끝
        
        // ---------------------------
        
        // 알고리즘 시작
        visited[r][c] = true;
        result = 1;
        cleaning(r, c, d, 0);
        // 알고리즘 종료
        System.out.println(result);
    }
    
    private static void cleaning(int x, int y, int direction, int count) {
        if (count == 4) {
            // 후진
            int nx = x + bx[direction];
            int ny = y + by[direction];
            
            if (map[nx][ny] == 1 
                    || nx <= 0 
                    || ny <= 0 
                    || nx >= N - 1 
                    || ny >= M - 1) {
                return;
            } else {
                cleaning(nx, ny, direction, 0);
            }
        } else {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            
            // 청소 가능 상태
            if (map[nx][ny] == 0 && !(visited[nx][ny])) {
                result++;
                visited[nx][ny] = true;
                if (direction - 1 < 0) direction = 4;
                cleaning(nx, ny, direction - 1, 0);
            } else {
                // 청소 불가능 상태
                if (direction - 1 < 0) direction = 4;
                cleaning(x, y, direction - 1, count + 1);
            }
        }
    }
}
