package author.hyun.sik.lim.backjoon.samsung.review.Q16234;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 인구 이동 문제
// N * N 안에 조건이 만족시
// 인구 이동 기준 : L <= ?? <= R


public class Main {
    static class Location {
        int x, y, areaNumber;
        Location (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] area;
    static int L;
    static int R;
    static int areaNumber;
    static Queue<Location> queue;
    
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    
    static int[] information;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        init();
        int result = moving();
        System.out.println(result);
    }
    
    private static int moving() {
        // TODO Auto-generated method stub
        // 탐색 실시
        int index = 0;
        
        while (index < 2000) {
            // 각 지역별 정보 및 지역 갱신
            visited = new boolean[N][N];
            information = new int[N*N +1];
            area = new int[N][N];
            areaNumber = 0;
            
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (!visited[y][x]) {
                        areaNumber++;
                        // 이동 필요 없을 시
                        bfs(x, y);
                    } 
                }
            }
            
            
            // 변경
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    map[y][x] = information[area[y][x]];
                    //System.out.print(map[y][x] + " ");
                }
               //System.out.println();
            }
//            System.out.println();
//            System.out.println(areaNumber);
//            System.out.println();
//            System.out.println();
            if(areaNumber == N*N)
                return index;
            
            index++;
        }
        
        return index;
    }

    private static void bfs(int startX, int startY) {
        // TODO Auto-generated method stub
        queue.add(new Location(startX, startY));
        int count = 0;
        int sum = 0;
        visited[startY][startX] = true;
        
        while (!queue.isEmpty()) {
            Location location = queue.remove();
            int x = location.x;
            int y = location.y;
            area[y][x] = areaNumber;
            
            // 결과를 나타내는 변수 참조용(0 : 지역 합, 1 : 지역 갯수)
            sum += map[y][x];
            count++;
            
            // 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (isWall(nx,ny))
                    continue;
                
                
                if (!visited[ny][nx]
                        && (L <= Math.abs(map[y][x] - map[ny][nx])
                        && Math.abs(map[y][x] - map[ny][nx]) <= R)) {
                    visited[ny][nx] = true;
                    queue.add(new Location(nx,ny));
                }
            }
        }
        
//        System.out.println(area[startY][startX] + " " + count);
        information[areaNumber] = sum / count;
        
    }
    
    // 벽일 경우
    private static boolean isWall(int nx, int ny) {
        // TODO Auto-generated method stub
        return (nx < 0 || ny < 0 || nx >= N || ny >= N)? true : false;
    }
    
    // 초기 식
    private static void init() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        map = new int[N][N];
        L = sc.nextInt();
        R = sc.nextInt();
        queue = new LinkedList<>();
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = sc.nextInt();
            }
        }
        
        sc.close();
    }

}
