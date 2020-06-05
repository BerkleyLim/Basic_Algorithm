package author.hyun.sik.lim.backjoon.samsung.review.Q15685_not.copy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 드래곤 커브 (혼자 풀기)
// 시작점 , 시작방향, 세대

// DFS + BFS 문제

public class Main {
    static int[][] curve;   // 드래곤 커브 정보
    
    static boolean[][] map; // 맵에 그릴 좌표
    
    static int N; // 드래곤커브 갯수
    
    static final int dx[] = {1, 0, -1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static ArrayList<Integer> direction;
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        dragonCurve();
        scan();
    }

    private static void scan() {
        // TODO Auto-generated method stub
        int count = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                if(map[y][x] && map[y+1][x] && map[y][x+1] && map[y+1][x+1]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void dragonCurve() {
        // TODO Auto-generated method stub
        // 드래곤 커브 순차적으로 진행!
        for (int dragon = 0; dragon < N; dragon++) {
            int x = curve[dragon][0];
            int y = curve[dragon][1];
            int d = curve[dragon][2];
            int g = curve[dragon][3];
            
            direction = new ArrayList<>();
            
            // 0세대
            map[y][x] = true;
            int[] info = running(x, y, d);
            x = info[0];
            y = info[1];
            
            // 드래곤 커브 시작 (1 세대부터)
            for (int run = 1; run <= g; run++) {
                int size = direction.size();
                for (int i = size-1; i >= 0; i--) {
                    info = running(x, y, direction.get(i));
                    x = info[0];
                    y = info[1];
                }
            }
        }
    }

    private static int[] running(int x, int y, int d) {
        // TODO Auto-generated method stub
        int[] info = new int[2];
        
        int nx = x + dx[d];
        int ny = y + dy[d];
        
        map[ny][nx] = true;
        // 진행 방향의 반대방향으로 바꿔주고 다음 이동할 방향으로 바꿔준다.
        d = (d + 2) % 4;
        d--;
        if (d < 0)
            d = 3;
        
        info[0] = nx; info[1] = ny;
        
        direction.add(d);
        return info;
    }

    private static void init() throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        // 드래곤 커브 정보  (0 : x좌표 , 1 : y좌표, 2 : 시작 방향, 3 : 세대);
        curve = new int[N][4];
        map = new boolean[101][101]; // 최대 값 기준 잡는다.
        
        // 드래곤 커브 정보 입력
        for (int i = 0; i < N; i++) {
         // 드래곤 커브 정보  (0 : x좌표 , 1 : y좌표, 2 : 시작 방향, 3 : 세대);
            st = new StringTokenizer(br.readLine());
            curve[i][0] = Integer.parseInt(st.nextToken());
            curve[i][1] = Integer.parseInt(st.nextToken());
            curve[i][2] = Integer.parseInt(st.nextToken());
            curve[i][3] = Integer.parseInt(st.nextToken());
        }
        
        br.close();
    }

}
