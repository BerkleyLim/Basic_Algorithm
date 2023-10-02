package author.hyun.sik.lim.sw.expert.academy.Q1949;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    // 등산로
    // N * N 크기로 구성
    // 각 칸은 지형의 높이
    // 등산로 시작 : 가장 높은 봉우리
    // 산으로 올라갈 수록 높은 지형에서 낮은 지형으로 가로 or 세로 방향으로 연결
    // 높이가 같거나 낮은 지형, 대각선 방향의 연결은 불가능
    // 긴 등산로 만들기 위해 딱 한곳을 정해서 최대 K 길이 만큼 지형을 깍는 공사 할 수 있음
    // 긴 등산로를 만들기 위해 딱 한곳을 정해서 최대 K 깊이만큼 지형을 깍는 공사 가능
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
            int K = sc.nextInt();
            
            int[][] boards = new int[N][N];
            top = new LinkedList<>();
            
            int max = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    boards[y][x] = sc.nextInt();
                    
                    max = Math.max(max, boards[y][x]);
                    if (max < boards[y][x]) {
                        top.clear();
                        top.offer(new Info(x,y,boards[y][x]));
                    } else if (max == boards[y][x]) {
                        top.offer(new Info(x,y,boards[y][x]));
                    } else {
                        
                    }
                }
            }
            System.out.println("#" + test_case + " " +solution(N, K, boards));
        }
    }
    
    static boolean[][] visited;
    static Queue<Info> top;
    static class Info{
        int x;
        int y;
        int h;
        
        public Info(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
        
    }
    
    final static int[] dx = {1,0,-1,0};
    final static int[] dy = {0,-1,0,1};
    static int max;
    private static int solution(int N, int K, int[][] boards) {
        // TODO Auto-generated method stub
        max = 1;
        int size = top.size();
        while (!top.isEmpty()) {
            Info info = top.poll();
            visited = new boolean[N][N];
            
            
            int x = info.x;
            int y = info.y;
            int height = info.h;
//            
//            System.out.println(x + ", " + y + "\t");
//            System.out.println(top.size());
            
            visited[y][x] = true;
            dfs(N,K,boards,1,x,y,height,0);
            visited[y][x] = false;
        }
        return max;
    }
    
    private static void dfs(int N, int K, int[][] boards, int path, int curX, int curY, int height, int cut) {
        // TODO Auto-generated method stub
//        System.out.print(curX + " " + curY + " path 값 : " + path +" max값 : " + max + " / ");
        for (int i = 0; i < dx.length; i++) {
            max = Math.max(path, max);
            
            int x = curX + dx[i];
            int y = curY + dy[i];
            if (x >= 0 && y >= 0 && x < N && y < N && !visited[y][x]) {
                if (height <= boards[y][x]) {
                    if (cut == 0) {
                        if (height > boards[y][x] - K) {
                            visited[y][x] = true;
                            dfs(N,K,boards,path+1,x,y,height-1,cut+1);
                            visited[y][x] = false;
                        }
                    }
                } else {
                    visited[y][x] = true;
                    dfs(N,K,boards,path+1,x,y,boards[y][x],cut);
                    visited[y][x] = false;
                }
//                System.out.println();
            }
        }
    }
    
    

}
