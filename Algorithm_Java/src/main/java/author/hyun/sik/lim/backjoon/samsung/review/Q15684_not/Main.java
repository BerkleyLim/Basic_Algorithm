package author.hyun.sik.lim.backjoon.samsung.review.Q15684_not;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사다리 조작
// 예를 들면
// 1번은 1번으로, 2번은 2번으로 ..... N-1번은 N-1번으로, N번은 N번으로 이동할 것

public class Main {
    static int[][] map;
    static int N; // 세로선
    static int M; // 가로선 
    static int H; // 기본적으로 가로선 놓을 수 있는 갯수
    
    static int answer;
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        dfs(1, 0);
        System.out.println((answer >= 4) ? -1 : answer);
    }

    private static void dfs(int x, int count) {
        // TODO Auto-generated method stub
        if (answer <= count) return;
        else {
            if (ladder()) {
                answer = count;
                return;
            }
        }
        
        for (int y = 1; y <= N; y++) {
            for (int h = 1; h < H; h++) {
                if (map[y][h] == 0 && map[y][h+1] == 0) {
                    map[y][h] = 1;
                    map[y][h+1] = 2;
                    dfs(h, count + 1);
                    map[y][h] = map[y][h+1] = 0;
                }
            }
        }
        
    }
    
    // 로직시작! dfs로 통해 로직이 정해져 있으면!
    private static boolean ladder() {
        // TODO Auto-generated method stub
        for (int i = 1; i <= H; i++) {
            int x = i, y = 1;
            
            for (int j = 0; j < N; j++) {
                if (map[y][x] == 1) x++;
                else if (map[y][x] == 2) x--;
                y++;
            }
            
            // 일치 않을 경우!
            if (x != i)
                return false;
        }
        return true;
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][H+1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            map[x][y] = 1;
            map[x][y+1] = 2;
        }
        
        answer = 4;
        br.close();
        
    }
    
    

}
