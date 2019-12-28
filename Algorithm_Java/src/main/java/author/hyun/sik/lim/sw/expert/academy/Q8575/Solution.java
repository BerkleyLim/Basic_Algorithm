package author.hyun.sik.lim.sw.expert.academy.Q8575;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        
        // 알고리즘 문제영역
        for(int test_case = 1; test_case <= T; test_case++) {
            // N = 정점 갯수(Vertex), M = 간선갯수(edge), C = 최대 비용
            int N = sc.nextInt();
            int M = sc.nextInt();
            int C = sc.nextInt();
            
            int[] def = new int[4];
            
            
            // 인접 리스트 초기화
            ArrayList<ArrayList<Integer>> ad = new <ArrayList<Integer>> ArrayList();
            
            
            for (int i = 1; i <= N; i++) {
                ad.add(new<Integer> ArrayList()); //ad 리스트에 담을 리스트 초기화
            }
            
            //MultiLinkedList answer = new MultiLinkedList();
            for (int i = 0; i < M; i++) {
                // 0 : xi, 1 : yi, 2 : di, 3 : ci
                // xi : 출발간선
                // yi : 도착간선
                // di : 간선길이
                // ci : 비용계수
                def[0] = sc.nextInt();
                def[1] = sc.nextInt();
                def[2] = sc.nextInt();
                def[3] = sc.nextInt();
                
                // 단방향 그래프 기준
                ad.get(def[0]).add(def[1]);
                //ad.get(def[1]).add(def[0]); // 양방향 그래프일 경우 주석 제거
            }
        }
    }
}






//// 링크드 리스트
//class MultiLinkedList {
//    private Node header; // 헤더부분 생성
//    private int size; // 노드 갯수
//    
//    // 연결리스트 생성
//    public MultiLinkedList(){
//        header = new Node(null);
//        size = 0;
//    }
//
//    // node 생성
//    class Node {
//        private Object data;
//        private Node nextNode;
//        
//        Node (Object data) {
//            this.data = data;
//            this.nextNode = null;
//        }
//    }
//}