package author.hyun.sik.lim.backjoon.Q17141;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 출처 : https://lily-lee.postype.com/post/4906606

public class Solution {
    public static int N, r,  min = Integer.MAX_VALUE, val;
    public static int[] di = { 1, -1, 0, 0 }, dj = { 0, 0, 1, -1 }, vis;
    public static int[][] lab;
    public static ArrayList<Point> al;

    public static void main(String[] args) throws Exception {

        long times = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        lab = new int[N][N]; // 연구소
        //copy = new int[N][N]; // 직접쓰는애
        al = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    al.add(new Point(i, j));
                    lab[i][j] = 0;
                } 
            }
        }
        vis = new int[al.size()];
        find_loc(0, 0);
        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min - 1);

        //System.out.println("times" + (System.currentTimeMillis() - times));
    }

    private static void find_loc(int start, int count) {
        if (count == r) {
            //System.out.println(Arrays.toString(vis));
            spread();
            
            min = min<val?min:val;
            return;
        }

        for (int i = start; i < al.size(); i++) {
            if (vis[i] == 0) {
                vis[i] = 1;
                find_loc(i, count + 1);
                vis[i] = 0;
            }
        }
    }

    private static void spread() {
        int[][] v = new int[N][N];
        int[][] copy = new int[N][N];
        
        for(int i = 0; i<N; i++) {
            copy[i] = lab[i].clone();
        }//카피 저장
        val = 0;
        Queue<Point> q = new LinkedList<>();
        for(int i = 0; i<al.size();i ++) {
            if(vis[i] == 1) {
                q.offer(al.get(i));
                lab[al.get(i).x][al.get(i).y] = 0;
                v[al.get(i).x][al.get(i).y] = 1;
            //    System.out.println(al.get(i));
            }
        }//바이러스 퍼트림
        
        
        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.poll().y;
            val = val>v[x][y]?val:v[x][y];
            
            for (int d = 0; d < 4; d++) {
                int nx = x + di[d];
                int ny = y + dj[d];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && copy[nx][ny] == 0 && v[nx][ny] == 0) {
                    q.add(new Point(nx, ny));
                    copy[nx][ny] = 2;
                    //val = copy[nx][ny];
                    v[nx][ny] = v[x][y] + 1;
                }
            }
        }
        /*
        for(int[] x: v)
            System.out.println(Arrays.toString(x));*/
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j] == 0 && v[i][j] == 0) {
                    val = Integer.MAX_VALUE;
                    return;
                }
            }
        }
        
        
        
    }

}// end of class
