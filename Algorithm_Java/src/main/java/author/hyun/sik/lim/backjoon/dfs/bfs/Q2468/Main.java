package author.hyun.sik.lim.backjoon.dfs.bfs.Q2468;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
    // 안전 영역
    // 물이 잠기지 않는 영역을 구하라
    // 높이, 높이 기준으로 더 높을때 안전영역
    // 높이는 1 ~ 100이 주어지면 그 범위 안에 안전영역이 가장 많은 구역을 구하는 것을 목표로 한다.
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int[][] boards = new int[N][N];
//        int maxHeight = Integer.MIN_VALUE;
//        int minHeight = Integer.MAX_VALUE;
        Set<Integer> number = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            for (int d = 0; d < N; d++) {
                boards[i][d] = sc.nextInt();
//                maxHeight = Math.max(maxHeight, boards[i][d]);
//                minHeight = Math.min(minHeight, boards[i][d]);
                number.add(boards[i][d]);
            }
        }
        sc.close();
//        System.out.println(number);
//        for (int i : number) {
//            
//        }
        
//        System.out.println(solution(N, boards, maxHeight, minHeight));
        System.out.println(solution(N, boards, number));
        
//        Runtime.getRuntime().gc();
//        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        System.out.print(usedMemory + " bytes");
    }

    
    static class Info {
        int x;
        int y;
        
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Info> queue;
//    private static int solution(int N, int[][] boards, int maxHeight, int minHeight) {
    private static int solution(int N, int[][] boards, Set<Integer> number) {
        // TODO Auto-generated method stub
        System.out.println("run()");
        int answer = 0;
        queue = new LinkedList<>();
        
        // 각 영역별로 구하기
//        int[] index = new int[number.size()];
//        for (int i = minHeight; i <= maxHeight; i++) {
        Iterator<Integer> iterater = number.iterator();
        for (int index = 0; index < number.size() -1; index++) {
            int i = iterater.next();
//            System.out.println(i);
            int count = 0;
            boolean[][] visited = new boolean[N][N];
            
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visited[y][x] || boards[y][x] <= i) {
                        continue;
                    }
                    queue.offer(new Info(x,y));
                    System.out.print(" size : " + queue.size());
                    bfs(i,boards,visited,N);
                    count++;
//                    System.out.print(count + " : " + x + " " + y + "  /  ");
                }
            }
            System.out.println();
            answer = Math.max(answer, count) ;
            
        }
        
        return answer;
    }
    
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    private static void bfs(int height, int[][] boards, boolean[][] visited, int N) {
        // TODO Auto-generated method stub
        while(!queue.isEmpty()) {
            Info info = queue.poll();
            int startX = info.x;
            int startY = info.y;
            visited[startY][startX] = true;
            
            for (int i = 0; i < dx.length; i++) {
                int x = startX + dx[i];
                int y = startY + dy[i];
                
                if (x < 0 || y < 0 || x >= N || y >= N || visited[y][x] || boards[y][x] <= height) {
                    continue;
                }
                
//                visited[y][x] = true;
                queue.offer(new Info(x,y));
            }
        }
    }

}
