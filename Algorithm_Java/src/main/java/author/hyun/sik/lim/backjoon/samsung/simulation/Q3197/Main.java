package author.hyun.sik.lim.backjoon.samsung.simulation.Q3197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백조와 호수
// 맵에 물 공간, 얼음이 주어지고
// 추가로 백조가 주어짐
// 여기서 물 공간과 접촉하는 얼음만 녹고
// 이후 백조를 만날 수 있는 경우
// 
public class Main {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    
    static int R;   // 세로
    static int C;   // 가로
    
    static char[][] map;
    
    static Queue<Point> swan;
    static Queue<Point> ice;
    
    static boolean[][] iceVisited;
    static int[][] swanVisited;
    
    static int swanCount;
    
    static class Point {
        int x;
        int y;
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
        ice = new LinkedList<>();
        
        swanVisited = new int[R][C];
        iceVisited = new boolean[R][C];
        // 초기 작업
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (!iceVisited[y][x] && map[y][x] == '.') {
                    initSearch(x,y); // 먼저 물을 검색 후 인접한 얼음을 추가
                }
            }
        }
        
        
        // 작업 시작
        int day = 0;
        while(true) {
            if (swanSearch())
                return day;
            removeIce();
        }
    }

    private static void removeIce() {
        // TODO Auto-generated method stub
        int size = ice.size();
        int index = 0;
        while(index < size) {
            Point point = ice.remove();
            int x = point.x;
            int y = point.y;
            swan.add(point);
            
            if (map[y][x] != 'L')
                map[y][x] = '.';
            
            // 벽 검색!
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (outWall(nx, ny))
                    continue;
                
                if (!iceVisited[ny][nx] 
                        && (map[ny][nx] == '.' || map[ny][nx] == 'L')) {
                    iceVisited[ny][nx] = true;
                } else if (!iceVisited[ny][nx] 
                        && map[ny][nx] == 'X') {
                    ice.add(new Point(nx, ny));
                    iceVisited[ny][nx] = true;
                }
            }
        }
    }

    private static boolean swanSearch() {
        // TODO Auto-generated method stub
        Point initPoint = swan.peek();
        swanVisited[initPoint.y][initPoint.x] += 1;
        while(!swan.isEmpty()) {
            Point point = swan.remove();
            int x = point.x;
            int y = point.y;
            
            if (swanVisited[y][x] == swanCount)
                return true;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (outWall(nx, ny))
                    continue;
                
                if (swanVisited[ny][nx] == 0 
                        && (map[ny][nx] == '.' || map[ny][nx] == 'L')) {
                    swan.add(new Point(nx, ny));
                    swanVisited[ny][nx] += 1;
                } else if (swanVisited[ny][nx] == 0
                        && map[ny][nx] == 'X') {
                    continue;
                }
                
            }
        }
        
        return false;
    }

    private static void initSearch(int x1, int y1) {
        // TODO Auto-generated method stub
        Queue<Point> waterSearch = new LinkedList<>();
        iceVisited[y1][x1] = true;
        waterSearch.add(new Point(x1, y1));
        
        while(!waterSearch.isEmpty()) {
            Point point = waterSearch.remove();
            int x = point.x;
            int y = point.y;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (outWall(nx, ny))
                    continue;
                
                if (!iceVisited[ny][nx] 
                        && (map[ny][nx] == '.' || map[ny][nx] == 'L')) {
                    waterSearch.add(new Point(nx, ny));
                    iceVisited[ny][nx] = true;
                } else if (!iceVisited[ny][nx] 
                        && map[ny][nx] == 'X') {
                    ice.add(new Point(nx, ny));
                    iceVisited[ny][nx] = true;
                }
            }
        }
        
    }

    private static boolean outWall(int x, int y) {
        // TODO Auto-generated method stub
        return (x < 0 || y < 0 || x >= C || y >= R)? true : false;
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
//        for (int i = 0; i < st.countTokens(); i++) {
//            R = Integer.parseInt(st.nextToken());
//            C = 
//        }
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        swan = new LinkedList<>();
        swanCount = 0;
        for (int y = 0; y < R; y++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int x = 0; x < C; x++) {
                map[y][x] = str.split("")[x].charAt(0);
                
                if (map[y][x] == 'L') {
                    swan.add(new Point(x, y));
                    swanCount++;
                }
            }
        }
        
        br.close();
    }

}
