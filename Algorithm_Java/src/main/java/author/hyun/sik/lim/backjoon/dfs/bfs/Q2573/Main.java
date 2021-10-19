package author.hyun.sik.lim.backjoon.dfs.bfs.Q2573;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 빙산
    // 지구온난화로 빙산이 높는 환경
    // 빙산의 구조를 나타내면
    // 해당 칸이 0 일때 : 바다
    // 해당 칸이 1 이상 일때 : 빙산의 높이
    // 빙산 높이의 영향 : 1년 후 동서남북 인접한 바다의 갯수 만큼 높이가 1씩 감소
    // 이후, 빙산의 동어리가 최초 2개 이상이 발생할 경우를 출력
    // 빙산이 녹고 2덩어리 이상이 발생할 경우 걸리는 최소 시간, 빙산이 다 녹을때까지 분리되지 않을시(칸이 전체가 바다가 아닐경우 포함) : 0 을 출력
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] boards = new int[N][M];
        int[][] boards_copy = new int[N][M];
        boolean flag = true;
        
        int heightCount = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                boards[y][x] = sc.nextInt();
                boards_copy[y][x] = boards[y][x];
                
                if (boards[y][x] > 0) {
                    heightCount++;
                } else {
                    flag = false; // 바다가 포함될 경우 (분리 못할경우 굳이 알고리즘 실행 필요 없음)
                }
            }
        }
        System.out.println((flag)?0:solution(N, M, boards, boards_copy, heightCount));

    }
    
    static boolean[][] visited;
    private static int solution(int N, int M, int[][] boards, int[][] boards_copy, int heightCount) {
        // TODO Auto-generated method stub
        int second = 0;
        while (heightCount > 0) {
            
            int count = 0;
            visited = new boolean[N][M];
            
            // 영역 구하기
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (boards[y][x] > 0 && !visited[y][x]) {
                        bfs(N, M, boards, x, y, boards_copy);
                        count++;
//                        System.out.println("run()");
                    }
                }
            }
            
//            System.out.println(count);
            // 덩어리(영역)이/가 2개 이상일때
            if (count > 1)
                return second;
            
//            System.out.println("run()");
            // 분열
            for (int startY = 0; startY < N; startY++) {
                for (int startX = 0; startX < M; startX++) {
                    if (boards[startY][startX] > 0) {
//                        System.out.println("run()");
                        int minus = 0;
                        for (int i = 0; i < dx.length; i++) {
                            int x = startX + dx[i];
                            int y = startY + dy[i];
                            
                            if (x < 0 || y < 0 || x >= M || y >= N) {
                                continue;
                            }
                            
                            if (boards[y][x] > 0) {
                                continue;
                            }
                            
                            minus++;
                        }
                        boards_copy[startY][startX] -= minus;
                        if (boards_copy[startY][startX] <= 0)
                            heightCount--;
                    }
                }
            }
            
            
            second++;
            
            
            // 1년후 결과물
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    boards[y][x] = boards_copy[y][x];
//                    System.out.print(boards[y][x] + "\t");
                }
//                System.out.println();
            }
//            System.out.println();
        }
        return 0;
    }
    
    static final int[] dx = {1,0,-1,0};
    static final int[] dy = {0,1,0,-1};
    private static void bfs(int N, int M, int[][] boards, int startX, int startY, int[][] boards_copy) {
        // TODO Auto-generated method stub
        boolean flag = true;
        Queue<int[]> queue = new LinkedList<>();
        visited[startY][startX] = true;
        queue.offer(new int[] {startX, startY});
        
        while(!queue.isEmpty()) {
            int[] xy = queue.poll();
            int currentX = xy[0];
            int currentY = xy[1];
           
            for (int i = 0; i < dx.length; i++) {
                int x = currentX + dx[i];
                int y = currentY + dy[i];
                
                if (x < 0 || y < 0 || x >= M || y >= N) {
                    continue;
                }
                
                if (visited[y][x] || boards[y][x] < 1) {
                    continue;
                }
                
                visited[y][x] = true;
                queue.offer(new int[] {x,y});
            }
            
        }
        
    }

}
