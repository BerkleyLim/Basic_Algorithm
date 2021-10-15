package author.hyun.sik.lim.sw.expert.academy.Q12223;

import java.util.Scanner;

public class Solution {
    // 감시 카메라
    // N : 카메라를 해킹한 갯수
    // (x1,x2,y1,y2)
    // 순열과 조합 문제
    // 경우의 수
    static int answer;
    static boolean[]visited;
    static int[] x1;
    static int[] x2;
    static int[] y1;
    static int[] y2;
    
    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
//            int[][] boards = new int[1001][1001];
            answer = 0;
            int N = sc.nextInt();
            x1 = new int[N];
            x2 = new int[N];
            y1 = new int[N];
            y2 = new int[N];
            visited = new boolean[N];
            
            for (int i =0; i < N; i++) {
                x1[i] = sc.nextInt();
                y1[i] = sc.nextInt();
                x2[i] = sc.nextInt();
                y2[i] = sc.nextInt();
            }
            
            permutation(0,0,N,x1[0],y1[0],x2[0],y2[0]);
            System.out.println("#" + test_case + " " + answer);
        }
    }
    
    // 2) 순열, 조합 알고리즘 (dfs 방식)
    private static void permutation(int depth, int i, int N, int x11, int y11, int x22, int y22) {
        // TODO Auto-generated method stub
//        System.out.println(combination);
        visited[i] = true;
        if (2 == depth) {
            if (isTrue(i, x11,x22,y11,y22))
                answer++;
            return;
        } 
        
        for (int x = i; x < N; x++) {
            if (visited[x])
                continue;
            
            if(isTrue(x, i, x11,x22,y11,y22)) {
                x11 = Math.max(x1[x], x11);
                x22 = Math.min(x2[x], x22);
                y11 = Math.max(y1[x], y11);
                y22 = Math.min(y2[x], y22);
            } else {
                continue;
            }
            
            permutation(depth + 1, x, N, x11,y11,x22,y22);
            visited[x] = false;
        }
    
        
    }

    private static boolean isTrue(int x, int i, int x11, int x22, int y11, int y22) {
        // TODO Auto-generated method stub
        int shortX = Math.min(x1[x], x11);
        int longX = Math.max(x2[x], x22);
        
        
        if (x11 == x1[x]) {
            if (y11 == y1[x]) {
                y22 = Math.min(y22, y2[x]);
            }
            x22 = Math.min(x22, x2[x]);
        }
        
        
        if (x1[x] < x22 || x2[x] < x11 || y1[x] < y22 || y2[x] < y11) {
            
        }
        if (((x1[x] <= x11 || x11 <= x2[x]) && (x1[x] <= x22 || x22 <= x2[x])) &&
                ((y1[x] <= y11 || y11 <= y2[x]) && (y1[x] <= y22 || y22 <= y2[x]))) {
           return true;            
        } else {
           return false;
        }
    }
    
    // 3) 카드 찾으러 이동하기  (bfs 방식)
//    private static int bfs(int[] position, int target, int[][] boardCopy) {
//        Queue<XY> q = new LinkedList<>();
//        boolean[][] visited = new boolean[4][4];
//        int x= position[0];
//        int y= position[1];
//        
//        visited[x][y] = true;
//        q.add(new XY(x,y,0));
//        
//        while(!q.isEmpty()) {
//            XY next = q.poll();
//            int px = next.x;
//            int py = next.y;
//            int move = next.move;
//            
//            if(boardCopy[next.x][next.y] == target) {
////                System.out.println("["+target+ "] find! "+ next.x+","+next.y+ ":"+ move);
//                position[0] = next.x;
//                position[1] = next.y;
//                return move;
//            }
//            
//            //상하좌우 1칸 direction 
//            for(int i=0; i<4; i++) {
//                int nx = px + dx[i];
//                int ny = py + dy[i];
//                
//                if(nx<0 || ny<0 || nx>3 || ny>3) continue;
//                if(visited[nx][ny]) continue;
//                
//                visited[nx][ny] = true;
//                q.add(new XY(nx,ny, move+1));
//            }
//            
//            // ctrl+상하좌우 direction
//            for(int i=0; i<4; i++) {
//                XY res = isCtrlRoute(px,py, i, boardCopy);
//                int nx = res.x, ny =res.y;
//                
//                if(nx==x && ny ==y) continue;
//                if(visited[nx][ny]) continue;
//                
//                visited[nx][ny] = true;
//                q.add(new XY(nx,ny, move+1));
//            }
//        }
//        return 0;
//        
//        
//        
//    }
}
