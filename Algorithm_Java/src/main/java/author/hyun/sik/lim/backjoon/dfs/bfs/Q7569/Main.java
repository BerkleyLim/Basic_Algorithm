package author.hyun.sik.lim.backjoon.dfs.bfs.Q7569;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 토마토
    // https://www.acmicpc.net/problem/7569
    // M * N * H 크기의 상자의 칸이 존재함
    // 보관 후 하루 지날 시, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토에 영향을 받아 익어짐
    // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤에 인접하게 감
    // 토마토가 상자안에 없을 수 있음
    // 출력 값 : 모두 다 익는 다는 전재하에 최소 몇일이면 익을까?
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int H = sc.nextInt();
        int[][][] box = new int[M][N][H];
        boolean flag = false;
        queue = new LinkedList<>();
        
        // -1 : 빈상자칸, 0: 안익은 토마토, 1 : 익은 토마토
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    box[x][y][h] = sc.nextInt();
                    if(box[x][y][h] == 0)
                        flag = true;
                    if (box[x][y][h] == 1)
                        queue.offer(new Info(x,y,h,0));
                }
            }
        }
        
        System.out.println(solution(M,N,H,box,flag));
    }

    static int answer;
    private static int solution(int M, int N, int H, int[][][] box, boolean flag) {
        // TODO Auto-generated method stub
//        answer = 0;
        answer = Integer.MIN_VALUE;
        if (!flag)
           return 0;
        
        visited = new boolean[M][N][H];
        
        // -1 : 빈상자칸, 0: 안익은 토마토, 1 : 익은 토마토
//        for (int h = 0; h < H; h++) {
//            for (int y = 0; y < N; y++) {
//                for (int x = 0; x < M; x++) {
//                    if (visited[x][y][h] || box[x][y][h] == -1 || box[x][y][h] == 0)
//                        continue;
//                    bfs(x,y,h,M,N,H,box);
//                }
//            }
//        }
        
        bfs(M,N,H,box);
        
        // 결과값 출력
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (box[x][y][h] == 0)
                        return -1;
                }
            }
        }
        return answer;
    }
    
    static final int[] dx = {1,0,-1,0,0,0};
    static final int[] dy = {0,1,0,-1,0,0};
    static final int[] dh = {0,0,0,0,1,-1};
    static class Info {
        int x;
        int y;
        int h;
        int count;
        public Info(int x, int y, int h, int count) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.count = count;
        }
    }
    
    static boolean[][][] visited;
    static Queue<Info> queue;
//    private static void bfs(int m, int n, int z, int M, int N, int H, int[][][] box) {
    private static void bfs(int M, int N, int H, int[][][] box) {
        // TODO Auto-generated method stub
        
//        visited[m][n][z] = true;
//        queue.offer(new Info(m,n,z,0));
        while(!queue.isEmpty()) {
            Info info = queue.poll();
            
            int x = info.x;
            int y = info.y;
            int h = info.h;
            int count= info.count;
           
            for (int i = 0; i < dx.length; i++) {
                if (isRun(x, y, h, M, N, H, box, i)) {
                    continue;
                }
//                visited[x+dx[i]][y+dy[i]][h+dh[i]] = true;
                box[x+dx[i]][y+dy[i]][h+dh[i]] = 1;
//                box[x+dx[i]][y+dy[i]][h+dh[i]] = 1 + box[x][y][h];
                queue.offer(new Info(x+dx[i],y+dy[i],h+dh[i],count+1));
                answer = count+1;
//                answer = Math.max(answer, box[x+dx[i]][y+dy[i]][h+dh[i]] -1);
            }
        }
    }

    private static boolean isRun(int x, int y, int h, int M, int N, int H, int[][][] box, int i) {
        // TODO Auto-generated method stub
        if (x+dx[i] < 0) {
            return true;
        } else if (x+dx[i] >= M) {
            return true;
        } else if (y+dy[i] < 0) {
            return true;
        } else if (y+dy[i] >= N) {
            return true;
        } else if (h+dh[i] < 0) {
            return true;
        } else if (h+dh[i] >= H) {
            return true;
        } else if (visited[x+dx[i]][y+dy[i]][h+dh[i]]) {
            return true;
        } else if (box[x+dx[i]][y+dy[i]][h+dh[i]] == -1) {
            return true;
        } else if (box[x+dx[i]][y+dy[i]][h+dh[i]] > 0) {
            return true;
        } else {
            return false;
        }
    }
}
