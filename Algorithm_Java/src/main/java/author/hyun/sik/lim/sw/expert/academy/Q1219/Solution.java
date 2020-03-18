package author.hyun.sik.lim.sw.expert.academy.Q1219;

import java.util.Scanner;

// 길 찾기 (기초)
// 길 중간 중간 2갈래길 존재
// A --> B 도시로 가는 길이 존재 여부하는 알고리즘 구성
// 모든 길은 순서쌍으로 이뤄짐!
// 2번에서 출발 할 수 있는 길의 표현 : (2, 5), (2, 9)로도 가능
// 화살표 방향을 거슬러 돌아가기 불가
// 출발점 : 0, 도착점 : 99
// 총 0 ~ 99개의 길이 있다.
// 한개의 정점에서 선택할 수 있는 길의 개수가 2개가 넘어가지 않음
// 

public class Solution {
    static boolean[] visited;
    static boolean[][] map;
    static boolean check;
    static final int SIZE = 100;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            visited = new boolean[SIZE];
            map = new boolean[SIZE][SIZE];
            check = false;
            
            int n = sc.nextInt();
            int edge = sc.nextInt();
            
            // 입력
            for (int i = 0; i < edge; i++) {
                int startNode = sc.nextInt();
                int endNode = sc.nextInt();
                
                map[startNode][endNode] = true;
            }
            
            // DFS 알고리즘
            DFS(0);
            
            System.out.print("#" + n + " ");
            
            if(check) System.out.println(1);
            else System.out.println(0);
        }
    }
    
    public static void DFS(int v) {
        // v 방문
        visited[v] = true;
        
        // 99번째 정점 도착시 방문 완료!
        if (v == 99) {
            check = true;
            return;
        }
        
        for (int i = 0; i < SIZE; i++) {
            if (!visited[i] && map[v][i])
                DFS(i);
        }
    }
    
}
