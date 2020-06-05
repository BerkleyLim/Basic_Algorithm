package author.hyun.sik.lim.backjoon.samsungSW.Q16236;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 아기 상어 문제
// 출처 : https://lily-lee.postype.com/post/4624882

public class Main {
    public static class Shark {
        int x, y;
        int lv;
        int fcnt;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.lv = 2;
            this.fcnt = 0;
        }

    }

    public static int N;
    public static int[][] sea;
    public static int[][] check;
    public static int[] dr = { 0, 0, 1, -1 };
    public static int[] dc = { 1, -1, 0, 0 };
    public static Shark shark;
    public static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        int sum = 0;
        int cnt = 1;
        INIT();
        while (cnt>0) {
            cnt = bfs();
            if(cnt>0)
                sum += cnt;
        }
        System.out.println(sum);
    }

    public static void INIT() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N]; // 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    shark = new Shark(i, j);
                    sea[i][j] = 0;
                }
            }
        } // INIT END
        
        br.close();
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }
    public static void eatFish(Shark fish) {
        // 작은 크기의 물고기만 먹을 수 있음
        shark.x = fish.x;
        shark.y = fish.y;
        sea[fish.x][fish.y] = 0; // 물고기 죽임

        shark.fcnt++; // 먹은 물고기 수 카운트
        if (shark.lv<7 && shark.fcnt == shark.lv) {
            // 자신의 크기만큼 물고기 먹으면 성장
            shark.fcnt = 0;
            shark.lv++;
        }
    }
    public static int bfs() {
        check = new int[N][N]; // check 변수 초기화
        q = new LinkedList<Point>();
        q.offer(new Point(shark.x, shark.y));
        check[shark.x][shark.y] = 1;

        int FishCheck = 0;
        Shark fish = new Shark(N, N);
        loop: while (!q.isEmpty()) {
            int r = q.peek().x;
            int c = q.poll().y;

            for (int d = 0; d < dr.length; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 지나갈 수 있는 곳: 자신보다 큰 물고기가 없는 곳
                if (isIn(nr, nc) && check[nr][nc] == 0 && sea[nr][nc] <= shark.lv) {
                    check[nr][nc] = check[r][c] + 1;
                    q.offer(new Point(nr, nc));

                    // 위치가 더 커질 경우, 더이상 확인할 필요 X
                    if (FishCheck != 0 && FishCheck < check[nr][nc]) {
                        break loop;
                    }
                    
                    // 처음 먹을 수 있는 자기보다 물고기가 발견 되었을 때
                    if (0 < sea[nr][nc] && sea[nr][nc] < shark.lv && FishCheck == 0) {
                        FishCheck = check[nr][nc];
                        fish.x = nr;
                        fish.y = nc;
                        fish.lv = sea[nr][nc];
                    }
                    // 같은 위치에 여러 마리 있을 경우, 가장 위의 가장 왼쪽 물고기부터 먹음
                    else if (FishCheck == check[nr][nc] && 0 < sea[nr][nc] && sea[nr][nc] < shark.lv) {
                        if (nr < fish.x) { // 가장 위에 있는 거 우선권
                            fish.x = nr;
                            fish.y = nc;
                            fish.lv = sea[nr][nc];
                        } else if (nr == fish.x && nc < fish.y) { // 다 가장 위일 경우, 가장 왼쪽 우선권
                            fish.x = nr;
                            fish.y = nc;
                            fish.lv = sea[nr][nc];
                        }

                    }

                }else if(isIn(nr, nc) && check[nr][nc] == 0) {
                    check[nr][nc] = -1;
                }
            }
        }
        // idx 초과 안날 경우
        if (fish.x != N && fish.y != N) {
            eatFish(fish);
        }
        
        return (FishCheck - 1);
    }

}
