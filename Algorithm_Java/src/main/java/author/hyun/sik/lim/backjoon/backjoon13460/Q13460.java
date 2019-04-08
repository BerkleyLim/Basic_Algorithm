package author.hyun.sik.lim.backjoon.backjoon13460;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 문제 : https://www.acmicpc.net/problem/13460
// 구슬 탈출 2
// N : 세로 크기 
// M : 가로 크기

// M * N 칸 안에 . / # / O / R / B 
// . : 빈칸
// # : 장애물 혹은 벽
// O : 구멍위치
// R : 빨간 구슬의 위치
// B : 파란 구슬의 위치

public class Q13460 {
    // 생성자 설정!
    static class INFO { // answer : 최소 이동 거리수!
        int ry, rx, by, bx, count;
    }
    
    // 기본 변수 세팅
    private static INFO start;
    private static int N; // 세로
    private static int M; // 가로
    private static String board[][]; // 보드 판
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // -------------- 알고리즘 작성 ----------------
        start = new INFO();
        // 먼저 첫째줄 입력
        N = sc.nextInt();
        M = sc.nextInt();
        
        board = new String[N][M];
        
        sc.nextLine();
        
        // 다음으로는 구슬 탈출 보드의 대한 내용 설정
        for (int y = 0; y < N ; y++) {
            String temp = sc.next();
            
            String[] tempArray = temp.split("");
            for (int x = 0; x < M; x++) {
                board[y][x] = tempArray[x];
                
                if (tempArray[x].equals("R")) {
                    start.ry = y;
                    start.rx = x;
                }
                
                if (tempArray[x].equals("B")) {
                    start.by = y;
                    start.bx = x;
                }
            }
        }
        
        start.count = 0;
        
        // 결과값 출력
        System.out.println(logic());
        
        // 초기화용 (다음 테스트)
        board = new String[N][M];

        // ------------- 알고리즘 끝 -----------------
    }
    
    public static int logic() {
        // 다음은 bfs 방식으로 푼다.
        
        // 좌표 위치!
        final int dy[] = {-1, 1, 0, 0};
        final int dx[] = {0, 0, -1, 1};
        
        
        // BFS 설정!
        int[][][][] visited = new int[N][M][N][M];
        Queue<INFO> queue = new LinkedList<>();
        queue.add(start);
        
        // 여기는 방문했는지 알아보기 위해!
        visited[start.ry][start.rx][start.by][start.bx] = 1;
        
        // 큐가 안비어있을 때
        int ret = -1; 
        while(!queue.isEmpty()) {
            INFO current = queue.element(); 
            queue.remove();
            
            // 이동 횟수 10 이상일 때 빠져나온다
            if (current.count > 10) break;
            
            // 여기서 빨간구슬이 빠져나오고, 파란구슬이 빠져 나오지 않았을 경우
            if (board[current.ry][current.rx].equals("O") 
                    && !(board[current.by][current.bx].equals("O"))) {
                ret = current.count;
                break;
            } 
            
            // 여기서는 dx, dy 위치를 이용하여 움직이기!
            for (int direction = 0; direction < 4; direction++) {
                int nextRY = current.ry;
                int nextRX = current.rx;
                int nextBY = current.by;
                int nextBX = current.bx;
                
                // 여기는 빨간 구슬을 이동 시킬 수 있는 조건이닷!
                while(true) {
                    if (!(board[nextRY][nextRX].equals("#")) 
                            && !(board[nextRY][nextRX].equals("O"))) {
                        nextRY += dy[direction];
                        nextRX += dx[direction];
                    } else {
                        if (board[nextRY][nextRX].equals("#")) {
                            nextRY -= dy[direction];
                            nextRX -= dx[direction];
                        }
                        break;
                    }
                }
                
                // 여기는 파란 구슬을 이동 시킬 수 있는 조건이닷!
                while(true) {
                    if (!(board[nextBY][nextBX].equals("#")) 
                            && !(board[nextBY][nextBX].equals("O"))) {
                        nextBY += dy[direction];
                        nextBX += dx[direction];
                    } else {
                        if (board[nextBY][nextBX].equals("#")) {
                            nextBY -= dy[direction];
                            nextBX -= dx[direction];
                        }
                        break;
                    }
                }
                
                
                // 다음은 빨간구슬과 파란 구슬이 좌표가 일치할 경우를 대비한다
                if (nextRY == nextBY && nextRX == nextBX) {
                    if (!(board[nextRY][nextRX].equals("O"))) {
                        int red_dist = Math.abs(nextRY - current.ry) + Math.abs(nextRX - current.rx);
                        int blue_dist = Math.abs(nextBY - current.by) + Math.abs(nextBX - current.bx);
                        
                        if (red_dist > blue_dist) {
                            nextRY -= dy[direction];
                            nextRX -= dx[direction];
                        }
                        else {
                            nextBY -= dy[direction];
                            nextBX -= dx[direction];
                        }
                    } else {
                        break;
                    }
                }
                
                // 한번도 방문하지 않았을 경우!
                if (visited[nextRY][nextRX][nextBY][nextBX] == 0) {
                    visited[nextRY][nextRX][nextBY][nextBX] = 1;
                    INFO next = new INFO();
                    next.ry = nextRY;
                    next.rx = nextRX;
                    next.by = nextBY;
                    next.bx = nextBY;
                    next.count = current.count + 1;
                    queue.add(next);
                }
            }
        }
        return ret;
    }
    
}

// 참조 : https://na982.tistory.com/82
