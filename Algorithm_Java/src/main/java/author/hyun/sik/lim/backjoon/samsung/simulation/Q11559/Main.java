package author.hyun.sik.lim.backjoon.samsung.simulation.Q11559;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 뿌요뿌요 문제

// 여기서 연쇄 값을 구하라.

public class Main {
    static char[][] map;
    
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    
    static boolean[][] visited;
    static Queue<Point> queue;
    
    static final int HEIGHT = 12;
    static final int WEIGHT = 6;
    
    static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        System.out.println(logic());
    }

    private static int logic() {
        // TODO Auto-generated method stub
        int play = 0;
        while(true) {
            boolean isClear = false;
            
            visited = new boolean[HEIGHT][WEIGHT];
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WEIGHT; x++) {
                    if(!visited[y][x] && map[y][x] != '.' ) {
                        queue = new LinkedList<>();
                        if (bfs(x, y))
                            isClear = true;
                        
                    }
                }
            }
            
            if (!isClear) {
                return play;
            }
            play++;
            
            for (int x = 0; x < WEIGHT; x++) {
                int emptyY = HEIGHT - 1;
                for (int y = HEIGHT - 1; y >= 0; y--) {
                    if (map[y][x] != '.') {
                        map[emptyY][x] = map[y][x];
                        if (emptyY != y)
                            map[y][x] = '.';
                        emptyY--;
                    }
                }
            }
            
//            for (int y = 0; y < HEIGHT; y++) {
//                for (int x = 0; x < WEIGHT; x++) {
//                    System.out.print(map[y][x]);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
    }

    private static boolean bfs(int x1, int y1) {
        // TODO Auto-generated method stub
        int count = 0;
        Queue<Point> tempQueue = new LinkedList<>();
        
        tempQueue.add(new Point(x1,y1));
        queue.add(new Point(x1, y1));
        visited[y1][x1] = true;
        
        while(!tempQueue.isEmpty()) {
            Point point = tempQueue.remove();
            int x = point.x;
            int y = point.y;
            count++;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(outWall(nx, ny))
                    continue;
                
                if (!visited[ny][nx] && map[ny][nx] == map[y][x]) {
                    visited[ny][nx] = true;
                    queue.add(new Point(nx, ny));
                    tempQueue.add(new Point(nx, ny));
                }
            }
            
        }
        
        if (count >= 4) {
            mapRemove();
            return true;
        } else {
            return false;
        }
    }

    private static void mapRemove() {
        // TODO Auto-generated method stub
        while(!queue.isEmpty()) {
            Point point = queue.remove();
            int x = point.x;
            int y = point.y;
            
            map[y][x] = '.';
        }
    }
    
    private static boolean outWall(int nx, int ny) {
        // TODO Auto-generated method stub
        return (nx < 0 || ny < 0 || nx >= WEIGHT || ny >= HEIGHT) ? true : false;
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new char[HEIGHT][WEIGHT];
        
        for (int y = 0; y < HEIGHT; y++) {
            String str = br.readLine();
            for (int x = 0; x < WEIGHT; x++) {
                map[y][x] = str.charAt(x);
            }
        }
        
        
        br.close();
    }

}
