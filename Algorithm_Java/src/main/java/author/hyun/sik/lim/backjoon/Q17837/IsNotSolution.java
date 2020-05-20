package author.hyun.sik.lim.backjoon.Q17837;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://buddev.tistory.com/31 참고
public class IsNotSolution {
    private static int N;
    private static int K;
    private static int[][] color;
    private static LinkedList<Integer>[][] map;
    private static final int[] dx = {0, 1, -1, 0, 0};
    private static final int[] dy = {0, 0, 0, -1, 1};
    private static class Info {
        int x, y, direction;
        public Info() {
            
        }
        public Info(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    private static LinkedList<Info> horse;
    
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        color = new int[N + 1][N + 1];
        horse = new LinkedList<>();
        map = new LinkedList[N + 1][N + 1];
        
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                map[y][x] = new LinkedList<>();
                color[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        
        int x, y, direction;
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            direction = Integer.parseInt(st.nextToken());
            
            horse.add(new Info(x, y, direction));
            
            map[y][x].add(i);
        }
        
        turn();
    }


    private static void turn() {
        // TODO Auto-generated method stub
        for (int t = 0; t < 1000; t++) {
            for (int k = 0; k < K; k++) {
                Info info = horse.get(k);
                int x = info.x;
                int y = info.y;
                int direction = info.direction;
                
                int number = searchOrder(k, x, y);
                
                int nx = x + dx[direction];
                int ny = y + dy[direction];
                
                if (nx < 1 || nx > N || ny < 1 || ny > N || color[ny][nx] == 2) {
                    switch (direction) {
                    case 1 :
                        direction = 2;
                        break;
                    case 2 :
                        direction = 1;
                        break;
                    case 3: 
                        direction = 4;
                        break;
                    case 4:
                        direction = 3;
                        break;
                    }
                    
                    horse.set(k, new Info(x, y, direction));
                    
                    if (nx < 1 || nx > N || ny < 1 || ny > N || color[ny][nx] == 2)
                        continue;
                }
                
                if (move(x, y, nx, ny, number, color[ny][nx])) {
                    System.out.println(t);
                    return;
                }
                
            }
        }
        System.out.println("-1");
    }


    private static boolean move(int x, int y, int nx, int ny, int number,
            int order) {
        // TODO Auto-generated method stub
        while (map[y][x].size() > number) {
            int temp = -1;
            if (order == 0)
                temp = map[y][x].remove(number);
            else
                temp = map[y][x].removeLast();
            
            horse.set(temp, new Info(nx, ny, horse.get(temp).direction));
            
        }
        
        if (map[ny][nx].size() >= 4)
            return true;
        
        return false;
    }


    private static int searchOrder(int k, int x, int y) {
        // TODO Auto-generated method stub
        for (int i = 0; i < map[y][x].size(); i++) {
            if (map[y][x].get(i) == k)
                return i;
        }
        return -1;
    }

}
