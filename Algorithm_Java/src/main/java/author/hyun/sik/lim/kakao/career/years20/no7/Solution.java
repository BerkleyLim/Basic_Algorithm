package author.hyun.sik.lim.kakao.career.years20.no7;

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

class Solution {
    private static int[][] boards;
    private static int N;
    // BFS 문제!
    /* 다음과 같이 구성 (방향 알고리즘, r : 로봇)
     * 3 3 0
     * 2 r 0
     * 2 1 1
     * */
    // 이동 고려
    static final int[] dx = {1, 0, -1, 0}; 
    static final int[] dy = {0, 1, 0, -1}; 
    
    // 회전 고려
    static final int[] rdx = {1, 1, -1, -1};
    static final int[] rdy = {-1, 1, 1, -1}; 
    
    static Queue<Point> queue;
    static boolean[][][] visited;
    static class Point {
        int x, y;
        int direction; // 가로 : 0, 세로 : 1
        int second; // 초
        Point (int x, int y, int direction, int second) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.second = second;
        }
        public int getOtherX() {
            return x + dx[direction]; 
            
        } 
        
        public int getOtherY() {
            return y + dy[direction];
            
        }
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + ", direction=" + direction
                    + ", second=" + second + "]";
        }
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] board = {{0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        
        System.out.println(solution(board));
    }
    

    
    public static int solution(int[][] board) {
        // 지도 크기 설정
        N = board.length;
        boards = board;
        queue = new LinkedList<>();
        queue.add(new Point(0,0,0,0));
        visited = new boolean[N][N][4];
        visited[0][0][0] = true;
        // 초기 값
        // 상단
        // (x,y) = (0,0), (1,0)
        
        // 로봇 이동하는 경우
        // 로봇 회전하는 경우
        // 합해서 구하기
        
        // 도착까지 최소 걸리는 시간을 구하기!
        return bfs();
    }

    private static int bfs() {
        // TODO Auto-generated method stub
        // Queue에서 꺼낸 로봇의 x, y, 방향, 시간, 다른 x, y 
        int second, ox, oy; 
        int x, y, direction;
        // 로봇이 이동 후 가지게 되는 위치 및 방향 (nox : next other x)
        int nx, ny, nox, noy, ndirection;  
        // 회전할 때 판단해야 할 벽의 위치
        int rx, ry; 

        while(!queue.isEmpty()) {
            Point point = queue.remove();
            x = point.x;
            y = point.y;
            direction = point.direction;
            second = point.second;
            ox = point.getOtherX(); 
            oy = point.getOtherY();
            
            if (isFinish(x, y) || isFinish(ox, oy)) return second; // 도착하면 리턴

            // 각 방향별 이동
            for (int i = 0; i < 4; i++) { 
                nx = x + dx[i]; 
                ny = y + dy[i]; 
                nox = ox + dx[i]; 
                noy = oy + dy[i];
                
                if (outWall(nx,ny) || outWall(nox,noy)) continue;
                if (boards[ny][nx] == 1 || boards[noy][nox] == 1) continue;
                if (visited[ny][nx][direction]) continue;
                visited[ny][nx][direction] = true;
                queue.add(new Point(nx, ny, direction, second + 1));

            }
            // 회전
            for (int i = 1; i < 4; i += 2) { 
                // x, y를 기준으로 90도 회전 
                ndirection = (direction + i) % 4; 
                nox = x + dx[ndirection]; 
                noy = y + dy[ndirection]; 
                int tempDir = (i == 1) ? ndirection : direction;
                
                rx = x + rdx[tempDir]; 
                ry = y + rdy[tempDir]; 
                
                if (outWall(nox, noy) || outWall(rx, ry)) continue; 
                if (boards[noy][nox] == 1 || boards[ry][rx] == 1) continue; 
                if (visited[y][x][ndirection]) continue; 
                
                visited[y][x][ndirection] = true; 
                queue.add(new Point(x, y, ndirection, second + 1)); 
            } 
            
            direction = (direction + 2) % 4; 
            // 방향 반대 처리 
            for (int i = 1; i < 4; i += 2) { 
                // ox, oy를 기준으로 90도 회전 
                ndirection = (direction + i) % 4;
                nx = ox + dx[ndirection];
                ny = oy + dy[ndirection];
                int tempDir = (i == 1) ? ndirection : direction;
                
                rx = ox + rdx[tempDir];
                ry = oy + rdy[tempDir];
                
                ndirection = (ndirection + 2) % 4;
                if (outWall(nx, ny) || outWall(rx, ry)) continue;
                if (boards[ny][nx] == 1 || boards[ry][rx] == 1) continue;
                if (visited[ny][nx][ndirection]) continue;
                
                visited[ny][nx][ndirection] = true;
                queue.add(new Point(nx, ny, ndirection, second + 1));
            }

        }
        return -1;
    }



    private static boolean isFinish(int x, int y) {
        // TODO Auto-generated method stub
        return x == N - 1 && y == N - 1;
    }



    private static boolean outWall(int x, int y) {
        // TODO Auto-generated method stub
        return (x < 0 || y < 0 || x >= N || y >= N);
    }
}
