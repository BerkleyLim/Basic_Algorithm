package author.hyun.sik.lim.programmers.kakao21.q4;

public class Dijkstra {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

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
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        return answer;
    }
}
