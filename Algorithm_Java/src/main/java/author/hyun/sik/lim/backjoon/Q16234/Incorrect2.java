package author.hyun.sik.lim.backjoon.Q16234;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Incorrect2 {
    static class Node {
        int x, y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static boolean visited[][];
    
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        
        int second = 0;
        map = new int[N][N];
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = sc.nextInt();
            }
        }
        
        while (true) {
            visited = new boolean[N][N];    // 방문 초기화
            if (!check()) {
                second++;   // 인구 이동이 더 필요시
            } else {
                break;
            }
        }
        
        System.out.println(second);
    }
    
    public static boolean check() {
        List<Node> list;
        boolean isDone = true;
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                // 방문 x 시
                if (!visited[y][x]) {
                    list = new LinkedList<>();
                    list.add(new Node(x, y));
                    
                    // sum = 리스트에서 지정한 합
                    int sum = dfs(x,y,list,0);
                    
                    // 인구 이동이 필요한 경우
                    if (list.size() > 1) {
                        // 평균값 계산해서 인구 이동후 갱신
                        change(sum, list);
                        isDone = false;
                    }
                }
            }
        }
        
        return isDone;
    }
    
    public static void change(int sum, List<Node> list) {
        int average = sum / list.size();
        for (Node node : list) {
            map[node.y][node.x] = average;
        }
    }
    
    public static int dfs (int x, int y, List<Node> list, int sum) {
        visited[y][x] = true;
        sum = map[y][x];
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            
            if (!visited[ny][nx]) {
                int d = Math.abs(map[y][x] - map[ny][nx]);
                if (d >= L && d <= R) {
                    list.add(new Node(nx, ny));
                    sum += dfs(nx, ny, list, sum);
                }
            }
        }
        
        return sum;
    }

}
