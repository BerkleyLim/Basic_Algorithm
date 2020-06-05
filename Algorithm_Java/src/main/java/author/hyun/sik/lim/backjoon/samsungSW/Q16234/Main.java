package author.hyun.sik.lim.backjoon.samsungSW.Q16234;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// 참고 : https://heekim0719.tistory.com/366?category=816705
public class Main {
    static class Info {
        int x, y;
        
        public Info() {}
        
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] visited;
    static boolean isFinish;
    
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    
    static int peopleNumber = 0;
    static int result = 0;
    
    static Stack<Info> team;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        
        map = new int[N][N];
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = sc.nextInt();
            }
        }
        
        // 종료조건
        isFinish = true;
        team = new Stack<>();
        while (isFinish) {
            isFinish = false;
            visited = new int[N][N];
            team.clear();
            peopleNumber = 0;
            
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if(visited[y][x] == 0) {
                        BFS(x,y);
                    }
                }
            }
            
//            for (int y = 0; y < N; y++) {
//                for (int x = 0; x < N; x++) {
//                    System.out.print(map[y][x] + " ");
//                }
//                System.out.println();
//            }
            
            // 인구 이동이 있을 때
            if (isFinish) {
                result++;
            }
        }
        
        System.out.println(result);
    }

    private static void BFS(int x1, int y1) {
        // TODO Auto-generated method stub
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(x1, y1));
        visited[y1][x1] = 1;
        
        boolean isOk = false;
        
        while(!queue.isEmpty()) {
            Info info = queue.remove();
            int x = info.x;
            int y = info.y;
            
            team.push(info);
            peopleNumber += map[y][x];
            
            // 연합국 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[ny][nx]==0 
                        && (Math.abs(map[y][x] - map[ny][nx]) >= L 
                        && Math.abs(map[y][x] - map[ny][nx]) <= R)) {
                    queue.add(new Info(nx, ny));
                    visited[ny][nx] = 1;
                    isOk = true;
                    isFinish = true; 
                }
            }
        }
        
        // 연합 존재 x시
        if (!isOk) {
            peopleNumber -= map[y1][x1];
            team.pop();
            return;
        }
        
        // 연합 존재시
        int average = peopleNumber / team.size();
        
        for (int i = 0; i < team.size(); i++) {
            map[team.get(i).y][team.get(i).x] = average;
        }
        team.clear();
        peopleNumber = 0;
    }
    
}
