package author.hyun.sik.lim.programmers.kakao21.q4;

import java.util.Arrays;

public class Floid {

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
    
    // 플로이드 모드
    // 참조 1 : https://wellbell.tistory.com/101
    // 참조 2 : https://blog.naver.com/ndb796/221234427842
    
    // 정점 : 200, 요금 : 100000 기준(경로로 생각한다)
    static final int MAX = 200 * 100000 + 1;
    
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        // 결과 그래프 초기화 1
        int[][] dp = new int[n+1][n+1];
        
        // 결과 그래프 초기화 2
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }

        // 정점 및 간선 입력
        for (int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];

            dp[start][end] = cost;
            dp[end][start] = cost;
        }

        // k = 거쳐가는 노드
        for (int k = 1; k < n+1; k++) {
            // i = 출발 노드
            for (int i = 1; i < n+1; i++) {
                // j = 도착 노드
                for (int j = 1; j < n+1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        // 여기까지 플로이드 와샬 알고리즘 기본 형태
        // ------------------------------------------------------------
        
        // 여기서부터 중간지 경유 시작
        // 1) 각자 따로 도착지 계산
        int answer = dp[s][a] + dp[s][b];
        
        // 2) 경유지점 끼고 갈 경우
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] +dp[i][b]);
        }
        return answer;
    }
}
