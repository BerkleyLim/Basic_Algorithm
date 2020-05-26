package author.hyun.sik.lim.backjoon.Q14503;

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


public class NotSolution {
    static int N; // 세로 크기
    static int M; // 가로 크기
    
    // 로봇 청소기 좌표 및 처음 방향
    static int r; // 로봇 청소기 세로 위치
    static int c; // 로봇 청소기 가로 위치
    static int d; // 로봇 청소기 방향
    
    // 북 동 남 서 방향 지정
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};
    
    // 방문 여부 확인
    static boolean[][] visited;
    
    // 지도 정보
    static int[][] map;
    
//    static class Dot {
//        int x;
//        int y;
//        int direction;
//        
//        public Dot(int x, int y, int direction) {
//            this.x = x;
//            this.y = y;
//            this.direction = direction;
//        }
//    }
    
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
        // 풀이과정
        // (1) 아직 로봇 방문 안한 칸!
        // (2) 방향 돌리기
        // (3) 벽인지 아닌지 판별
        // (4) 벽인 경우 방향 검사 표시
        // (6) 반복문 생성 - 각 방향별로 검사를 위해
        // (7) 벽이 아닌 경우 반복문 바로 종료하는 명령구현
        // (8) 이미 청소한 경우(방문한 경우) 및 벽인 경우 - 생략 및 변수 설정
        // (9) 이동 실패 이후 다음으로 4방향 모두 검사시 조건절 수행
        // (10) 만일 4방향 돌았을 때 움직일 수 없고 뒤로 움직이면 벽일 때 시스템 종료
        while(true) {
            // (5)
            //boolean[] directed = new boolean[4];
            
            // (8)
            boolean flags = false;
            
            // (1)
            if (!visited[r][c]) {
                visited[r][c] = true;
                
                // (6)
                for (int i = 0; i < 4; i++) {
                    // (2)
                    int tempD = ((d + 3) - i) % 4;
                    int tempY = r + dy[d];
                    int tempX = c + dx[d];
                    
                    // (3)
                    if (map[tempY][tempX] == 1  || visited[tempY][tempX]) {
                        //(5)
                        //directed[tempD] = true;
                    } else {
                        // (2)
                        d = tempD;
                        r = tempY;
                        c = tempX;
                        // (8)
                        flags = true;
                        
                        // (7)
                        break;
                    }
                }
            } 
                
                
            
            // (9)
            if (!flags) {
                boolean isExist = true;
                // (11) : 4방향 모두 색인 했을 때 찾지 못한 경우 검사
//                for (int i = 0; i < 4; i++) {
//                    if(!directed[i]) {
//                        isExist = false;
//                        break;
//                    }
//                }
                
                // (10)
                //if (isExist) {
                    int tempY = r - dy[d];
                    int tempX = c - dx[d];
                    
                    if (map[tempY][tempX] == 1)
                        break;
                    else {
                        r = tempY;
                        c = tempX;
                    }
                    
                //}
            }
        }
        
        // 여기서 답 찾는 과정 풀이
        int count = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(visited[y][x])
                    count++;
            }
        }
        
        System.out.println(count);
        // 알고리즘 종료
    }
}
