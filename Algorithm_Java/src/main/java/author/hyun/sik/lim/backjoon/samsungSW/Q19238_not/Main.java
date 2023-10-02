package author.hyun.sik.lim.backjoon.samsungSW.Q19238_not;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 스타트 택시
    
    // 손님을 도착지로 데려다 주면 연료가 충전
    // 연료 바닥시 그날 업무가 끝난다
    
    // 오늘 M명의 승객을 태우는 것이 목표!
    //
    // 최단경로로 이동하여 N*N 크기에서 상하좌우로 인접한 벽을 제외하고 이동 가능
    // 최단 거리로 간다
    // 연료 충전시 승객 태운 기점부터 승객에 데려다 줄때까지의 거리의 2배로 충전됨
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int charge = sc.nextInt();
        int[][] boards = new int[N+1][N+1];
        
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                boards[y][x] = sc.nextInt();
            }
        }
        
        int[] taxi = new int[2];
        taxi[1] = sc.nextInt();
        taxi[0] = sc.nextInt();
        int [][] distination = new int[M+2][2];
        for (int i = 2; i <= M+1; i++) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            boards[y][x] = i;
            
            y = sc.nextInt();
            x = sc.nextInt();
            distination[i][0] = x;
            distination[i][1] = y;
        }
//        for (int y = 1; y <= N; y++) {
//            for (int x = 1; x <= N; x++) {
//                System.out.print(boards[y][x] + "\t");
//            }
//            System.out.println();
//        }
        
        System.out.println(bfs(N, M, charge, boards, taxi, distination));
        
        
    }
    static final int[] dx = {0,-1,1,0};
    static final int[] dy = {-1,0,0,1};
    static boolean visited[][];
    static class Info {
        int x;
        int y;
        int charge;
        int plus;
        
        public Info(int x, int y, int charge) {
            this.x = x;
            this.y = y;
            this.charge = charge;
            this.plus = 0;
        }
        
        public Info(int x, int y, int charge, int plus) {
            this.x = x;
            this.y = y;
            this.charge = charge;
            this.plus = plus;
        }
    }
    private static int bfs(int N, int M, int curCharge, int[][] boards, int[] taxi, int[][] distination) {
        // TODO Auto-generated method stub
        if (curCharge == 0)
            return -1;
        
        Queue<Info> queue = new LinkedList<>();
        visited = new boolean[N+1][N+1];
        
        int personCount = 0;
        int person = 0;
        if (boards[taxi[1]][taxi[0]] > 1) {
            person = boards[taxi[1]][taxi[0]];
            boards[taxi[1]][taxi[0]] = 0;
            personCount++;
        }
        queue.offer(new Info (taxi[0], taxi[1], curCharge));
        while(!queue.isEmpty()) {
            Info info = queue.poll();
            
            int curX = info.x;
            int curY = info.y;
            int charge = info.charge;
            int plus = info.plus;
            visited[curY][curX]= true;
            
            if (personCount == M)
                return charge;
            
            if (charge < 1)
                continue;
            
            for (int i = 0; i < dx.length; i++) {
                int x = curX + dx[i];
                int y = curY + dy[i];
                
                if (x > 0 && y > 0 && x <= N && y <= N) {
                    if (visited[y][x] || boards[y][x] == 1) {
                        continue;
                    }
                    
                    if (person < 2) {
//                        System.out.println(x + " " + y + " " + (charge -1));
                        if (boards[y][x] > 1) {
                            queue = new LinkedList<>();
//                            System.out.println(queue.size());
                            visited = new boolean[N+1][N+1];
                            queue.offer(new Info (x,y, charge-1, plus + 1));
                            person = boards[y][x];
                            boards[y][x] = 0;
                            personCount++;
                            break;
                        }
                        queue.offer(new Info (x,y, charge-1));
                    } else {
                        if (distination[person][0] == x && distination[person][1] == y) {
                            queue = new LinkedList<>();
                            visited = new boolean[N+1][N+1];
//                            boards[y][x] = 0;
                            person = 0;
                            plus++;
                            queue.offer(new Info(x,y, (((charge -1) + (plus * 2)) >= 500000)?500000:((charge -1) + (plus * 2))));
//                            System.out.println();
                            break;
                            
                        }
                        queue.offer(new Info (x,y, charge-1, plus + 1));
                    }
                }
            }
        }
        
        return -1;
    }
    
}
