package author.hyun.sik.lim.programmers.kakao21.q4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // 82
        System.out.println(solution(6, 4, 6, 2, 
                new int[][] {{4, 1, 10}, 
                             {3, 5, 24}, 
                             {5, 6, 2}, 
                             {3, 1, 41},
                             {5, 1, 24}, 
                             {4, 6, 50},
                             {2, 4, 66},
                             {2, 3, 22},
                             {1, 6, 25}}));
        
        // 14
        System.out.println(solution(7, 3, 4, 1, 
                new int[][] {{5, 7, 9},
                             {4, 6, 4},
                             {3, 6, 1}, 
                             {3, 2, 3}, 
                             {2, 1, 6}}));
        
        // 18
        System.out.println(solution(6, 4, 5, 6, 
                new int[][] {{2, 6, 6},
                             {6, 3, 7},
                             {4, 6, 7}, 
                             {6, 5, 11}, 
                             {2, 5, 12},
                             {5, 3, 20},
                             {2, 4, 8},
                             {4, 3, 9}}));
        
        
    }
    
    // 합승 택시 요금
    // 플로이드 및 다익스트라 알고리즘
    // 플로이드 : 각 정점간 최단 경로를 구할때
    // 다익스트라 : 시작점으로 부터 나머지 정점까지 최단거기를 구할 때

    
    //                  플로이드                다익스트라
    // 공간 복잡도 :         V^2             V^2(인접행렬) / V + E (인접 리스트)
    // 시간 복잡도 :         V^3             V^2(인접행렬) / ElogV (인접리스트 + 우선순위 큐)
    
    // 플로이드 : 알고리즘 소스가 훨씬 더 간결 / 간선 수가 많으면 다익스트라보다 알고리즘이 빠를 수 있다 / 그래프의 음의 가중치 간선이 있을때 
    // 다익스트라 : 시작점으로부터 각 정점까지 최단거리만 구해도 될 때 플로이드 알고리즘보다 더 빠름 / 그래프의 음의 가중치 간선이 있으면 사용 불가
    
    // 합승 택시 요금
    // 택시비를 아낄려는 전략 사용
    // A,B 두사람이 택시를 탈 경우 집으로 도착할 수 있는 최소 비용
    
    // 다익스트라 모드
    // 참조 : https://wellbell.tistory.com/101
    
    static class Edge implements Comparable<Edge> {
        int index;
        int cost;
        Edge(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
        
       @Override
       public int compareTo(Edge e) {
           return this.cost - e.cost;
       }
    }
    
    // 정점 : 200, 요금 : 100000 기준(경로로 생각한다)
    static final int MAX = 200 * 100000 + 1;
    static LinkedList<LinkedList<Edge>> graph;
    
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX;
        
        // 그래프 정의
        graph = new LinkedList<>();
        
        // 노드 생성
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }
        
        // 각각 요금(거리) 설정 (최단경로, <문제에선 최저요금>)
        for(int[] i : fares){
            graph.get(i[0]).add(new Edge(i[1], i[2]));
            graph.get(i[1]).add(new Edge(i[0], i[2]));
        }
        
        // 대문자는 각 start 지점
        int[] A = new int[n + 1];
        int[] B = new int[n + 1];
        int[] S = new int[n + 1];
        
        Arrays.fill(A, MAX);
        Arrays.fill(B, MAX);
        Arrays.fill(S, MAX);
        
        // 각각 스타트 위치 지정하여 도착점까지
        A = dijkstra(a, A);
        B = dijkstra(b, B);
        S = dijkstra(s, S);       
        
        for(int i = 1; i <= n ; i ++) 
            answer = Math.min(answer, A[i] + B[i] + S[i]);
        return answer;
    }
    
    // bfs 기반으로 다익스트라 알고리즘 사용한다
    private static int[] dijkstra(int start, int[] costs) {
        Queue<Edge> queue = new PriorityQueue<>();
        
        // queue를 이용
        queue.offer(new Edge(start, 0));
        costs[start] = 0;
        
        // bfs 시작
        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            int nodeIndex = current.index;
            int nodeCost = current.cost;
            
            if (nodeCost > costs[nodeIndex])
                continue;
            
            List<Edge> edges = graph.get(nodeIndex);
            
            for (Edge edge : edges) {
                int cost = costs[nodeIndex] + edge.cost;
                
                if (cost < costs[edge.index]) {
                    costs[edge.index] = cost;
                    queue.offer(new Edge(edge.index, cost));
                }
            }
            
        }
        
        return costs;
    }
}
