package author.hyun.sik.lim.backjoon.dfs.bfs.Q2667;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // 단지 번호 붙이기
    // 보드 내에 1 : 집, 0 : 집없는곳
    // 총 단지 갯수와 단지당 집갯수를 오름차순으로 출력
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] boards = new int[N][N];
        
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = sc.next();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boards[i][j] = Integer.parseInt(str[i].split("")[j]);
//                System.out.print(boards[i][j]);
            }
//            System.out.println();
        }
        
        for (int i : solution(N, boards)) {
            System.out.println(i);
        }
    }

    static boolean visited[][];
    public static ArrayList<Integer> solution(int N, int[][] boards) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        visited = new boolean[N][N];
        int count = 0;
        // board가 1일때만 bfs 혹은 dfs
        // boards 0일때는 그냥 스킵
        // 만일 1을 방문시 그냥 스킵
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(visited[y][x] || boards[y][x] == 0) {
                    visited[y][x] = true;
                    continue;
                }
                count++;
                answer.add(bfs(x,y,N,boards));
            }
        }
        
        // Collections.reverseOrder() : 내림차순
        Collections.sort(answer);
        
        answer.add(0,count);
        return answer;
    }
    
    
    static final int[] dx = {1,0,-1,0};
    static final int[] dy = {0,1,0,-1};
    private static int bfs(int startx, int starty, int N, int[][] boards) {
        Queue<XY> queue = new LinkedList<>();
        
        int count = 1;
        visited[starty][startx] = true;
        queue.offer(new XY(startx,starty));
        while(!queue.isEmpty()) {
            XY xy = queue.poll();
            int x = xy.x;
            int y = xy.y;
            for (int i = 0; i < dx.length; i++) {
                if (y+dy[i] < 0 || x+dx[i] < 0 || y+dy[i] >= N || x+dx[i] >= N ||
                        visited[y+dy[i]][x+dx[i]] || boards[y+dy[i]][x+dx[i]] == 0)
                    continue;
                count++;
                visited[y+dy[i]][x+dx[i]] = true;
                queue.offer(new XY(x+dx[i],y+dy[i]));
            }
        }
        return count;
    }
    
    static class XY {
        int x;
        int y;
        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
