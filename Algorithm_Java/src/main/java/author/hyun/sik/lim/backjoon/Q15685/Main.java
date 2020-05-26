package author.hyun.sik.lim.backjoon.Q15685;

import java.util.ArrayList;
import java.util.Scanner;

// 드래곤 커브 문제

// 참고 : https://heekim0719.tistory.com/371?category=816705
public class Main {
    static class Location {
        int x, y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean[][] map;
    static int[][] note;
    
    static final int dx[] = {0, -1, 0, 1};
    static final int dy[] = {1, 0, -1, 0};
    
    static final int nxtx[] = {1, 0, -1, 0};
    static final int nxty[] = {0, -1, 0, 1};
    
    static int result;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        // 드래곤 커브 갯수
        int N = sc.nextInt();
        result = 0;
        
        note = new int[N][4];
        // x, y, d, g : (x, y) : 드래곤 커브 시작점
        // d : 시작 방향
        // g : 세대
        for (int i = 0; i < N; i++) {
            note[i][0] = sc.nextInt();
            note[i][1] = sc.nextInt();
            note[i][2] = sc.nextInt();
            note[i][3] = sc.nextInt();
        }
        
        map = new boolean[101][101];
        // 커브 그리기
        for (int i = 0; i < N; i++) {
            // 0세대
            int y = note[i][0];
            int x = note[i][1];
            int direction = note[i][2];
            int depth = note[i][3];
            map[y][x] = true;
            
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            map[ny][nx] = true;
            
            ArrayList<Location> al = new ArrayList<>();
            al.add(new Location(x,y));
            al.add(new Location(x,y));
            
            // 다음 세대
            dfs(0, al, depth);
            
//            for (int p = 0; p < 10; p++) {
//                for (int q = 0; q < 10; q++) {
//                    System.out.print(map[p][q] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
        
        // 정사각형 갯수 구하기
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                if (map[y][x] && map[y+1][x] 
                        && map[y][x+1] && map[y+1][x+1]) {
                    result++;
                }
            }
        }
        
        System.out.println(result);
    }
    
    private static void dfs(int count, ArrayList<Location> al, int depth) {
        if (count == depth)
            return;
        
        int alSize = al.size();
        int x = al.get(alSize - 1).x;
        int y = al.get(alSize - 1).y;
        
        for (int i = alSize - 1; i > 0; i--) {
            int alx = al.get(i-1).x - al.get(i).x;
            int aly = al.get(i-1).y - al.get(i).y;
            
            int nx, ny;
            
            if (alx == 1 && aly == 0) {
                nx = x + nxtx[1];
                ny = y + nxty[1];
            } else if (alx == 0 && aly == -1) {
                nx = x + nxtx[2];
                ny = y + nxty[2];
            } else if (alx == -1 && aly == 0) {
                nx = x + nxtx[3];
                ny = y + nxty[3];
            } else {
                nx = x + nxtx[0];
                ny = y + nxty[0];
            }
            
            map[ny][nx] = true;
            al.add(new Location(nx,ny));
            x = nx;
            y = ny;
        }
        dfs(count + 1, al, depth);
    }

}
