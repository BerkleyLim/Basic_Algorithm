package author.hyun.sik.lim.sw.expert.academy.basic.algorithm;

import java.util.Scanner;

// <문제>
// 숫자가 있는 원 : 정점(Vertex)
// 정점과 정점을 잇는 연결선 : 간선(Edge)
// 정점 최대 갯수 : 30개
// 정점과 정점의 연결관계가 인접행렬로 주어질 때
// DFS를 이용하여 시작 정점으로부터 모든 정점을 탐색한 결과를 순서대로 출력

// <입력>
// 1        // 테케갯수
// 8 1      // 정점 갯수, 시작 정점
// 1 2      // 정점간 연결 관계. 1과 2가 연결
// 2 4
// 2 5
// 4 8 
// 5 8
// 3 6
// 3 7
// 6 8
// 7 8
// -1 -1    // 입력 끝

public class DFS {
    
    static final int MAX_VERTEX = 30; // 최대 정점 개수
    
    static int vertex;
    static int map[][] = new int[MAX_VERTEX][MAX_VERTEX];
    static int visit[] = new int [MAX_VERTEX];
    
    static void depthFirstSearch (int v) {
        visit[v] = 1;
        for (int i = 1; i <= vertex; i++) {
            if (map[v][i] == 1 && visit[i] == 0) {
                System.out.printf("%d ", i);
                depthFirstSearch(i);
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            vertex = sc.nextInt();
            int start = sc.nextInt();
            
            map = new int[MAX_VERTEX][MAX_VERTEX];
            visit = new int[MAX_VERTEX];
            
            while (true) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                
                // 입력이 끝나면
                if (v1 == -1 && v2 == -1) {
                    break;
                }
                
                map[v1][v2] = map[v2][v1] = 1;
                
            }
            System.out.printf("#%d ", test_case);
            System.out.printf("%d ", start);
            depthFirstSearch(start);    // DFS
            System.out.println();
            
        }
        sc.close();
    }

}
