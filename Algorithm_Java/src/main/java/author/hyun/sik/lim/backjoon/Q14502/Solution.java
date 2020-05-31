package author.hyun.sik.lim.backjoon.Q14502;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 연구소 문제
// 출처 : https://lily-lee.postype.com/post/4906603

public class Solution {
    public static int N, M, max;
    public static Point[] w;
    public static boolean[] v;
    public static int[] dr = { 0, 0, 1, -1 }, dc = { -1, 1, 0, 0 };
    public static int[][] maps, def;
    public static ArrayList<Point> empt;
    public static ArrayList<Point> vir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];

        w = new Point[3]; // 벽의 갯수는 3개

        empt = new ArrayList<Point>();
        vir = new ArrayList<Point>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 0) {
                    empt.add(new Point(i, j)); // 빈칸일 때 -> 벽 세울 수 있는 가능성
                } else if (maps[i][j] == 2) {
                    vir.add(new Point(i, j)); // 바이러스 일 때 -> 퍼져나가야함
                }
            }
        } // INIT END

        v = new boolean[empt.size()]; // nC3 방문처리

        Walls(0, 0);

        System.out.println(max);

    }

    public static void Walls(int start, int cnt) {
        if (cnt == 3) {
            // 벽 세우고 DFS
            def = new int[N][M];    //벽 초기화
            for (int i = 0; i < w.length; i++) {
                maps[w[i].x][w[i].y] = 1; // 벽 세우기
            }
            for (int i = 0; i < vir.size(); i++) {
                Germ(vir.get(i).x, vir.get(i).y);
            } // 바이러스 위치 지정

            int safe = 0;
            for(int i = 0; i<N; i++) {
                for(int j =0; j<M; j++) {
                    if(maps[i][j] == 0 && def[i][j] ==0) {    //바이러스 퍼지지 않은 곳
                        safe++;
                    }
                }
            }
            
            for (int i = 0; i < w.length; i++) {
                maps[w[i].x][w[i].y] = 0; // 벽 지우기
            }

            max = max>safe?max:safe;
            
            return;
        }

        for (int i = start; i < empt.size(); i++) {
            if (!v[i]) {
                v[i] = true;
                w[cnt] = empt.get(i);
                Walls(i, cnt + 1);
                v[i] = false;
            }
        }
    }// nC3 종료

    public static void Germ(int r, int c) {
        def[r][c] = 1;
        for (int d = 0; d < dr.length; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isIn(nr, nc) && def[nr][nc] == 0 && maps[nr][nc] == 0) {

                Germ(nr, nc);
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }
}