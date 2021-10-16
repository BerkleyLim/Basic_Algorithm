package author.hyun.sik.lim.backjoon.dfs.bfs.Q2178;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // 미로탐색
    // N * M 미로 크기 주어짐
    // 미로칸 구성 요소 : 1 일경우 - 이동 가능, 0 일경우 - 이동 불가
    // 출발점 : (1,1) -> 도착점 (N, M)
    // 최소의 칸 수를 구하라!
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = sc.next();
        }
        System.out.println(solution(N,M,str));
        
//        System.out.println(solution(4,6,new String[]{"101111", "101010", "101011", "111011"}));
//        System.out.println(solution(4,6,new String[]{"110110", "110110", "111111", "111101"}));        
//        System.out.println(solution(2,25,new String[]{"1011101110111011101110111", "1110111011101110111011101"}));
//        System.out.println(solution(7,7,new String[]{"1011111", "1110001", "1000001", "1000001", "1000001", "1000001", "1111111"}));  
    }
    
    public static int solution(int N, int M, String[] str) {
        int answer = 0;
        
        char[][] boards = new char[N][M];
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                boards[y][x] = str[y].charAt(x);
            }
        }
        
        answer = BFS(N,M,boards);
        
        return answer;
    }
    
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static class Info {
        int x;
        int y;
        int count;
        boolean visited[][];
        
        public Info (int N, int M) {
            this.x = 0;
            this.y = 0;
            this.count = 1;
            this.visited = new boolean[N][M];
            this.visited[0][0] = true;
        }
        
        public Info(int N, int M, int x, int y, int count, boolean visited[][]) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.visited = visited;
            this.visited[y][x] = true;
        }
    }
    private static int BFS(int N, int M, char[][] boards) {
        Queue<Info> queue = new LinkedList<>();
        
        queue.offer(new Info(N, M));
        int answer = 1;
        boolean isExit = false;
        while(!queue.isEmpty()) {
            Info info = queue.poll();
            int x = info.x;
            int y = info.y;
            int count = info.count;
            boolean[][] visited = info.visited;
            
            for (int i = 0; i < dx.length; i++) {
                if (x +dx[i] < 0 || y + dy[i] < 0  || x + dx[i] >= M || y + dy[i] >= N
                        || boards[y+dy[i]][x+dx[i]] == '0' || visited[y+dy[i]][x+dx[i]])
                    continue;
               
//               System.out.println((y + dy[i]) + " " + (x + dx[i])); 
               if (x+dx[i] >= M-1 && y+dy[i] >= N-1)
                   isExit = true;
               queue.offer(new Info(N, M, x+dx[i], y+dy[i], count+1, visited));
               answer = count+1;
               
            }
            
            if (isExit)
                break;
        }
        return answer;
    }
    

}
