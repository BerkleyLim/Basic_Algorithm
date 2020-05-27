package author.hyun.sik.lim.backjoon.Q15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 또 다른 방법

// 출처 : https://lily-lee.postype.com/post/4960018

public class Main {
    public static class MAP{
        int r, c;

        public MAP(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
    }
    public static int N, M, maps[][], w[], min = Integer.MAX_VALUE, dr[] = {0,0,-1,1}, dc[] = {-1,1,0,0}, v[][]; 
    public static boolean check[];
    public static ArrayList<MAP> chk;
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][N];
        v = new int[N][N];
        chk = new ArrayList<>();
        for(int i =0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                //0은 빈자리 1은 집 2는 치킨
                maps[i][j] = Integer.parseInt(st.nextToken());
                if(maps[i][j] == 2) {
                    chk.add(new MAP(i, j));
                    maps[i][j] = 0;
                }
            }
        }//INIT END
        
        
        check = new boolean[chk.size()];
        w = new int[M];
        
        comb(0,0);
        
        System.out.println(min);
        
    }
    public static void comb(int idx, int cnt) {
        if (cnt == M) {
            v = new int[N][N];
            int tmp = BFS();
            min = min<tmp?min:tmp;
            return;
        }
        for(int i = idx; i<chk.size(); i++) {
            if(!check[i]) {
                check[i] = true;
                w[cnt] = i;
                comb(i, cnt+1);
                check[i] = false;
            }
        }
    }
    public static int BFS() {
        Queue <MAP> q = new LinkedList<MAP>();
        int sum = 0;
        for(int i = 0; i<M; i++) {
            q.add(chk.get(w[i]));
            v[chk.get(w[i]).r][chk.get(w[i]).c] = 1;
            maps[chk.get(w[i]).r][chk.get(w[i]).c] = 2;
        }
        
        while(!q.isEmpty()) {
            int r = q.peek().r;
            int c = q.poll().c;
            
            if(maps[r][c] == 1) {
                sum += (v[r][c]-1);
            }
            
            for(int d = 0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(isIn(nr, nc) && v[nr][nc]==0) {
                    q.offer(new MAP(nr, nc));
                    v[nr][nc] = v[r][c] + 1;
                }
                
            }
        }
        
        for(int i = 0; i<M; i++) {
            maps[chk.get(w[i]).r][chk.get(w[i]).c] = 0;
        }
        
        return sum;
        
    }
    public static boolean isIn(int r, int c) {
        return (0<= r && r<N &&0<=c && c<N);
    }

}