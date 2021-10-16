package author.hyun.sik.lim.backjoon.dfs.bfs.Q1260;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // DFS와 BFS
    // DFS와 BFS로 나타낸 결과물 보여주기
    // N : 1 ~ 1000
    // M : 1 ~ 10000
    // https://www.acmicpc.net/problem/1260
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int start = sc.nextInt();
        int[][] connect = new int[M][2];
        
        for (int x = 0; x < M; x++) {
            connect[x][0] = sc.nextInt();
            connect[x][1] = sc.nextInt();
        }
        
        for (int[] i : solution(N, M, start, connect)) {
            for (int answer : i) {
                if (answer == 0)
                    break;
                System.out.print(answer + " ");
                
            }
            System.out.println();
        }        
        
        
//        for (int[] i : solution(4, 5, 1, new int[][] {
//                {1,2}, {1,3}, {1,4}, {2,4}, {3,4}
//        })) {
//            for (int answer : i) {
//                if (answer == 0)
//                    break;
//                System.out.print(answer + " ");
//                
//            }
//            System.out.println();
//        }
//        
//        for (int[] i : solution(5, 5, 3, new int[][] {
//            {5, 4}, {5, 2}, {1, 2}, {3, 4}, {3, 1}
//        })) {
//            for (int answer : i) {
//                if (answer == 0)
//                    break;
//                System.out.print(answer + " ");
//                
//            }
//            System.out.println();
//        }
//        
//        for (int[] i : solution(1000, 1, 1000, new int[][] {
//            {999, 1000}
//        })) {
//            for (int answer : i) {
//                if (answer == 0)
//                    break;
//                System.out.print(answer + " ");
//                
//            }
//            System.out.println();
//        }
    }
    
    static boolean visited[];
    static boolean edge[][];
    static int[][] answer;
    static int nodeCount;
    private static int[][] solution(int N, int M, int start, int[][] connect) {
        answer = new int[2][N];
        
        edge = new boolean[N+1][N+1];
        
        for (int i = 0; i < connect.length; i++) {
            edge[connect[i][0]][connect[i][1]] = true;
            edge[connect[i][1]][connect[i][0]] = true;
        }
        
        visited = new boolean[N+1];
        nodeCount = 0;
        DFS(start, 0, N);

        visited = new boolean[N+1];
        nodeCount = 0;
        BFS(start, N);
        return answer;
    }
    
    public static void DFS(int start, int depth, int N) {
//        
//        if (depth == N) {
//            return;
//        }
//        
//        for (int i = 0; i < 1; i++) {
//            
//        }
        visited[start] = true;
        answer[0][nodeCount++] = start;
        for (int i = 1; i <= N; i++) {
            if (visited[i])
                continue;
            
            if (edge[start][i]) {
                DFS(i, depth + 1, N);
//                visited[i] = false;
                
            }
            
        }
    }
    
    static Queue<Integer> queue;
    public static void BFS(int start, int N) {
        queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        answer[1][nodeCount++] = start;
        
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (visited[i])
                    continue;
                
                if (edge[node][i]) {
                    visited[i] = true;
                    answer[1][nodeCount++] = i;
                    queue.offer(i);
                }
                
            }
        }
    }
    
}
