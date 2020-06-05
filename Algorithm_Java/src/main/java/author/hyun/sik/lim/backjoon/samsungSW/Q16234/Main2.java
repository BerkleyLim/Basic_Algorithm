package author.hyun.sik.lim.backjoon.samsungSW.Q16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 출처 : https://lily-lee.postype.com/post/4885827
// 또 다른 방식 풀이

public class Main2 {
    public static int N, L, R, counts;
    public static int[][] maps, v;
    public static int[] dr = { 0, 0, 1, -1 }, dc = { 1, -1, 0, 0 };
    public static boolean flag = true;

    public static void main(String[] args) {
        INIT();
        while (flag) {    //flag true이면 계속 진행, 최대 2000번
            Union();
            if(flag)
                counts++;
        }
        System.out.println(counts);
    }

    public static void INIT() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            maps = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int DFS(int r, int c, int cnt, int group) {

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isIn(nr, nc)) {
                int abslr = Math.abs(maps[r][c] - maps[nr][nc]);
                if (L <= abslr && abslr <= R && v[nr][nc] == 0) {
                    v[nr][nc] = group;
                    cnt = DFS(nr, nc, cnt + 1, group);
                }
            }
        }
        return cnt;
    }

    public static void Union() {
        int group = 0, cnt = 0, sum = 0;
        flag = false;
        v = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (v[i][j] == 0) {
                    v[i][j] = ++group;
                    cnt = DFS(i, j, 1, group);
                    //System.out.println(cnt);
                    if (cnt == 1) {    //카운트가 시작값과 같으면 연합 없음
                        v[i][j] = 0;
                    } else {
                        flag = true;
                        sum = 0;
                        for (int k = 0; k < N; k++) {
                            for (int h = 0; h < N; h++) {
                                if (v[k][h] == group) {
                                    sum += maps[k][h];
                                }
                            }
                        }

                        sum /= cnt;

                        for (int k = 0; k < N; k++) {
                            for (int h = 0; h < N; h++) {
                                if (v[k][h] == group) {
                                    maps[k][h] = sum;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }

    public static void COUT() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(maps[i]));
        }
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(v[i]));
        }
    }
}
