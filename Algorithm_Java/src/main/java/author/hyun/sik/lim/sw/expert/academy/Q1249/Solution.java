package author.hyun.sik.lim.sw.expert.academy.Q1249;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 보급로 문제
// 2차 세계 대전 : 연합군 vs 독일군
// 대규모 폭격 및 기사전으로 인해 도로가 파손
// 트럭이나 탱크 등 차량이 지나갈 수 있도록 파손된 도로를 복구 시켜야 함
// 공병대가 출발지(S) --> 도착지(G) 까지 가지 위한 도로 복구 작업 수행
// 가능하면 빠르게
// 도로가 파여진 깊이에 비례해서 복구 시간은 증가!

// 출발지 -> 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구할 것!
// 깊이 1일시 복구에 드는 시간은 1이라고 가정할것!

// 출발지(S) : 좌상단의 칸
// 도착지(G) : 우하단의 칸

// 이동 경로 : 상하좌우 방향으로 진행, 한칸씩 움직일 수 있음
// 각 칸은 파여진 도로의 깊이가 주어짐
// 현재 위치한 칸의 도로를 복구 해야만 다른 곳으로 이동 가능
// 이동시간 보다 도로를 복구시 필요한 시간이 매우 크다
// 출발지에서 도착지까지 거리에 대해서 고려할 필요 없음
// 각 칸에서 0이라고 주어지면 복구 작업이 불필요한 곳!


// 입력 조건 - 테케 별
// 1번째 줄 : 지도의 크기 (N * N) 를 주어지는 N 값 (N : 100 이내)
// 2번째 줄 이후 : 지도의 크기만큼 2차원 배열에서 각 칸마다 파여진 크기

// 출력 조건 - 테케 별
// 출발지 -> 도착지까지 가는 경로 중 복구 작업에 드는 시간이 가장 작은 경로의 복구 시간

public class Solution {
    static int size;
    static int[][] map;
    static int[][] distance;
    
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    static class Node {
        int y, x, cost;
        
        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int test_case = 1; test_case <= T; test_case++) {
            // 입력
            size = sc.nextInt();
            map = new int[size][size];
            distance = new int[size][size];
            
            
            for (int y = 0; y < size; y++) {
                String str = sc.next();
                for (int x = 0; x < size; x++) {
                    map[y][x] = str.charAt(x) - '0';
                    
                    // 거리값 주기
                    distance[y][x] = 9999999;
                }
            }
            // 입력 끝
            
            
            // 최단 경로 푸는 문제 : BFS냐 DFS냐, 필자는 BFS로 풀이
            System.out.println("#" + test_case + " " + BFS());
        }
    }
    
    public static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0));
        
        // 시작점 (1,1), 끝점 (N, N)
        while (!queue.isEmpty()) {
            int y = queue.peek().y;
            int x = queue.peek().x;
            int nowCost = queue.peek().cost;
            queue.poll(); // 큐 제거
            
            // 방향 결정
            for (int i = 0; i < 4; i++) {
                int nowY = y + dy[i];
                int nowX = x + dx[i];
                
                // 
                if (nowY < 0 || nowX < 0 || nowY >= size || nowX >= size)
                    continue;
                
                int nextCost = nowCost + map[nowY][nowX];
                
                // 만일 비용이 클 경우!
                if (nextCost >= distance[nowY][nowX])
                    continue;
                
                distance[nowY][nowX] = nextCost;
                queue.offer(new Node(nowY, nowX, nextCost));
            }
            
        }
        return distance[size-1][size-1];
    }
}
