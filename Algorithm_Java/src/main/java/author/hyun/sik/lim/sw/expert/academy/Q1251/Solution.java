package author.hyun.sik.lim.sw.expert.academy.Q1251;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

// 하나로 문제
// 이것은 해저 터널을 연결할 때 총 환경부담금을 최소화 할 수 있는 조건을 구하기

// 하나로 프로젝트
// 천해의 자연을 가진 인도네시아의 각 섬 간 교통이 원활치 않아 관광 산업의 발전을 저해하는 요소를 줄이고
// 부가 가치를 창출하고자 진행하는 프로젝트

// 인도네시아 내의 모든 섬을 해저터널로 연결 하는 것이 목표다

// 반드시 두 섬을 선분으로 연결, 교차시 물리적으로 연결 되지 않는다.
// 
// 환경 부담금 세율(E), 각 해저터널의 길이(L)의 제곱의 곱(E * L2) 만큼 지불

// 테케별 입력 변수 설명
// 1번째 줄 : N : 섬의 갯수
// 2번째 줄 : 각 섬의 X 좌표
// 3번째 줄 : 각 섬의 Y 좌표
// 마지막 줄 : 해저터널의 건설의 환경 부담 세율 실수 E

// 출력조건
// 최소 환경 부담금 정수 형태로 출력!
public class Solution {
    static int N;
    static double E;
    
    static class Island {
        long x, y;
        public Island(long x, long y) {
            this.x = x;
            this.y = y;
        }
        public Island(long x) {
            this.x = x;
        }
    }
    
    static class Node implements Comparable<Node> {
        int a, b;
        double weight; // 간선 가중치
        
        public Node(int a, int b, double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
        
        // 비교 정렬, 각 노드별로 정렬해주는 것?
        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            if((this.weight - o.weight) > 0) return 1;
            else if (this.weight == o.weight) return 0;
            else return -1;
        }
        
    }
    
    // 여기서부터 트리로 이용한 가중치 노드 찾기(크루스칼 알고리즘 응용)
    static int[] parents;   // 노드별
    
    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        // 합칠 수 있는 경우!
        if(aRoot != bRoot) {
            parents[bRoot] = aRoot;
            return true;
        }
        
        return false;
    }
    
    // 트리로 이용한 가중치 노드 찾기 끝
    
    
    // 거리 계산
    public static double getCharge(Island a, Island b, double e) {
        long L = (long) 
                (Math.pow(Math.abs(a.x - b.x), 2)
                + Math.pow(Math.abs(a.y - b.y), 2));
        return e * L;
    }
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++) {
            // 입력 시작
            int N = sc.nextInt();
            
            Island[] island = new Island[N];
            
            for (int i = 0; i < N; i++) {
                island[i] = new Island(sc.nextLong());
            }
            
            for (int i = 0; i < N; i++) {
                island[i] = new Island(island[i].x, sc.nextLong());
            }
            
            E = sc.nextDouble();
            // 입력 끝
            
            // Math.sqrt(3); // 3의 관련 루트 값 계산
            
            // 모두가 연결되는 경우 경우의 수
            // N * (N - 1) / 2
            // 처음 N : 섬의 개수
            // N - 1 : 그 섬이 갈 수 있는 경우의 수
            // /2 방향이 없으므로 2로 나눈다.
            Node[] nodes = new Node[N * (N-1)/2];
            
            // 여기는 간선의 각 출발 도착별 가중치부분
            int index = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    nodes[index++] = new Node(i, j, getCharge(island[i], island[j], E));
                }
            }
            
            Arrays.sort(nodes);
            
            // 
            parents = new int[N];
            Arrays.fill(parents, -1); // 배열 전체 초기화 메소드, 전체 -1로 초기화
            
            
            // 여기서 트리로 최단거리 찾기 알고리즘 응용(크루스칼 알고리즘)
            int count = 0;
            double result = 0;
            double r = 0.0;
            
            for (int i = 0; i < (N * (N - 1)) / 2; i++) {
                if (count == N - 1) break;
                
                // 최선의 경우 생각하면 아래와 같은 if문은 생략 가능
                if(!union(nodes[i].a, nodes[i].b)) continue;
                
                result += nodes[i].weight;
                result += r;
                count++;
                
            }
            
            // 알고리즘 끝
            
            System.out.println("#" + test_case + " " + Math.round(result));
        }
    }
}
