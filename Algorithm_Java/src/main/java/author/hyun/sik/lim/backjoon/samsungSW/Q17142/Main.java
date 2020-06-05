package author.hyun.sik.lim.backjoon.samsungSW.Q17142;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소 3문제!
// 바이러스 연구 관련 문제
// 바이러스 유출 여부 : 활성화 및 비활성화
// 처음 : 모든 바이러스 비활성화
// 활성상태 : 바이러스 상하좌우 인접한 모든 빈칸으로 동시 복제되고 1초 걸림
// 바이러스 M개를 활성 상태로 변경할려고 함
// 연구소 크기 : N*N
// 1*1 크기의 정사각형으로 나눠진다.
// 연구소 구조 : 빈칸, 벽, 바이러스
// 벽은 칸 하나를 가득 차지!
// 0 : 빈칸, 1 : 벽, 2 : 바이러스
// 연구소의 상태를 주어지고 모든 빈칸에 바이러스를 퍼뜨리는 최소 시간을 구하기!

// 입력 조건
// 첫째줄 : 연구소의 크기(N), 놓을 수 있는 바이러스 개수 M
// 둘째줄 : N개의 줄에 연구소의 상태가 주어짐

// 출력 조건
// 연구소의 모든 빈 칸에 대한 바이러스가 있게 되는 최소 시간!
// 단, 모든 빈칸에 바이러스를 퍼뜨릴 수 없을 경우 -1 출력

// 출처 : https://lily-lee.postype.com/post/4906610
public class Main {
    public static int N, M, INF = 987654321, min = INF;
    public static boolean flag;
    public static int[][] maps, visit;
    public static boolean[] v;
    public static Point[] w;
    public static ArrayList<Point> vir;
    public static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
    public static void main(String[] args) throws Exception {

        INIT();
        comb(0,0);
        if(min == INF)
            System.out.println(-1);
        else
            System.out.println(min);
        
        
    }
    public static void INIT() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());    //연구소 크기
        M = Integer.parseInt(st.nextToken());    //활성시킬 바이러스 갯수
        maps = new int[N][N];
        vir = new ArrayList<Point>();
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if(maps[i][j] == 2)    //비활성바이러스라면
                    vir.add(new Point(i, j));
            }
        }//INIT END
        w = new Point[vir.size()];
        v = new boolean[vir.size()];
    }

    public static void comb(int start, int cnt) {
        if(M == cnt) {
            int sol = BFS();
            min = min<sol?min:sol;
/*            for(int[] x: visit)
                System.out.println(Arrays.toString(x));
            System.out.println();*/
            return;
        }
        for(int i = start; i<vir.size(); i++) {
            if(!v[i]) {
                v[i] = true;
                w[cnt] = vir.get(i);    //바이러스 위치 저장
                comb(i, cnt+1);
                v[i] = false;
            }
        }
    }
    
    public static int BFS() {
        int times = 1;
        flag = false;
        visit = new int[N][N];    //돌 때마다 새로 만들어줘야함
        int[][] copys = new int[N][N];
        for(int i = 0; i<N; i++) {
            copys[i] = maps[i].clone();
        }
        
        Queue <Point> q = new LinkedList<Point>();
        for(int i = 0; i<M; i++) {
            q.offer(w[i]);
            visit[w[i].x][w[i].y] = 1;
        }//활성 바이러스 위치 저장
        
        while(!q.isEmpty()) {
            int r = q.peek().x;
            int c = q.poll().y;
            if(copys[r][c] == 3)
                times = times>visit[r][c]?times:visit[r][c];
            
            loop:for(int i = 0; i<N; i++) {
                for(int j = 0; j<N; j++) {
                    if(flag)
                        break loop;
                    if(!flag && maps[i][j] == 0) {
                        flag = true;
                    }
                }
            }
            
            if(!flag)
                return times-1;
            
            
            for(int d = 0; d<dr.length; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(isIn(nr, nc) && visit[nr][nc] == 0 && copys[nr][nc] == 0) {
                    visit[nr][nc] = visit[r][c] + 1;
                    copys[nr][nc] = 3;
                    q.offer(new Point(nr, nc));
                } else if(isIn(nr, nc) && visit[nr][nc] == 0 && copys[nr][nc] == 2) {
                    visit[nr][nc] = visit[r][c]+ 1;
                    q.offer(new Point(nr, nc));
                }
                
            }
        }
        
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if(copys[i][j] == 0) {
                    return INF;
                }
            }
        }
        
        return times-1;
    }
    public static boolean isIn(int r, int c) {
        return (0<= r && r<N && 0<=c && c<N);
    }
}