package author.hyun.sik.lim.kakao.career.years19.no7;

import java.util.LinkedList;
import java.util.Queue;

// 블록 이동하기
// 준비중 로봇 : 2 * 1 크기
// 0, 1로 이루어진 N*N 크기의 지도에서 2 * 1 크기인 로봇을 움직여 (N,N) 위치까지 이동 가능하도록 
// 로봇 이동하는 지도 : 가장 왼쪽, 상단의 좌표 : (1,1)로 하며
// 0 : 빈칸, 1 : 벽
// 로보슨 벽이 있는 칸 or 지도 밖으로 이동 불가
// 로봇 위치 : 1,1 위치에서 가로방향으로 놓여있는 상태로 시작
// 앞뒤 구분없이 움직임 가능
// 로봇 어디 칸이든 축이 되어 회전 가능
// 로봇이 한 칸 이동 or 90도 회전하는데 걸리는 시간 : 1초
// 로봇 위치가 N,N 위치까지 이동하는데 필요한 최소 시간을 return하기

// 참고 : https://leveloper.tistory.com/102

public class Solution {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] board = 
               {{0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }
    
    // BFS 문제!
    // 0~3번방 : 하,우,상,좌 이동
    // 회전시 : 
    // > 가로방향이 주어지면
    // 로봇 왼쪽 좌표부분
    // 1) 아래로 이동 : 1 -> 0
    // 2) 위로 이동 : 3 -> 0
    // 로봇 오른쪽 좌표 부분
    // 1) 아래로 이동 : 1 -> 2
    // 2) 위로 이동 : 3 -> 2
    // > 세로방향이 주어지면
    // 로봇 아랫쪽 좌표부분
    // 1) 왼쪽으로 이동 : 2 -> 3
    // 2) 오른쪽으로 이동 : 0 -> 3
    // 로봇 윗쪽 좌표 부분
    // 1) 왼쪽으로 이동 : 2 -> 1
    // 2) 오른쪽으로 이동 : 0 -> 1
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Queue<Point> queue;
    static class Point {
        int[] x, y;
        int direction; // 가로 : 0, 세로 : 1
        Point (int[] x, int[] y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    
    public static int solution(int[][] board) {
        int answer = 0;
        // 지도 크기 설정
        final int N = board.length;
        queue = new LinkedList<>();
        int[] x = {0, 1};
        int[] y = {0, 0};
        queue.add(new Point(x,y,0));
        // 초기 값
        // 상단
        // (x,y) = (0,0), (1,0)
        
        // 로봇 이동하는 경우
        // 로봇 회전하는 경우
        // 합해서 구하기
        
        // 도착까지 최소 걸리는 시간을 구하기!
        answer = bfs(board);
        return answer;
    }

    private static int bfs(int[][] board) {
        // TODO Auto-generated method stub
        int second = 0;
        while(!queue.isEmpty()) {
            Point point = queue.remove();
            int[] x = point.x;
            int[] y = point.y;
            
            // 각 방향별 이동 및 회전!
            for (int i = 0; i < 4; i++) {
                switch(i) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                }
            }
            second++;
        }
        return second;
    }
}
