package author.hyun.sik.lim.sw.expert.academy.Q1248;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// 1248 : 공통 조사
// 이진 트리가 주어질 시 임의의 두 정점의 공통 조상 중 가장 가까운 것을 찾을려고 함
// 자세한 내용은 문제 참조
// 공통조상이 정점 레벨 0일때 기초로 하고, 공통 조상 정점이 아래로 내려 가는 값이 가까워야 함
// 상속의 예로 치자면 : 자식 --> 부모 --> 조부모 --> ..... 
// 상속과 같은 개념으로 생각한다.

// 입력 조건 : 첫줄 : 테케
//         테스트 1줄 입력 : 트리의 정점의 총 갯수(V), 간선의 총 갯수(E), 공통 조상을 찾는 두개 정점번호
//         테스트 2줄 입력 : E개 간선 나열하여 (부모, 자식)정점 입력한다.
// 출력 조건 : 가장 가까운 공통 조상 정점, 루트로 하는 서브 트리의 크기
public class Solution {
    static int vertax;
    static int edge;
    
    static int[] nodeSize;
    static int[] rootIndex; // 자식 노드의 부모
    static boolean[] visited;   // childnode 2개의 공통 조상 찾는 여부 확인
    
    static boolean[] isVaild;   // 값 입력했는지 체크
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            vertax = sc.nextInt();
            edge = sc.nextInt();
            visited = new boolean[vertax + 1];
            nodeSize = new int[vertax + 1];
            rootIndex = new int[vertax + 1];
            
            isVaild = new boolean[vertax + 1];
            
            int[] childNode = new int[2];
            childNode[0] = sc.nextInt();
            childNode[1] = sc.nextInt();
            
            for (int i = 0; i < edge; i++) {
                int[] node = new int[2];
                
                for (int x = 0; x < 2; x++) {
                    node[x] = sc.nextInt();
                    
                    if(!isVaild[node[x]]) {
                        nodeSize[node[x]] = 1;
                        isVaild[node[x]] = true;
                    }
                    
                    // 부모 노드 생성
                    if(x == 1) {
                        rootIndex[node[x]] = node[0];
                        int root = node[0];
                        
                        while (root > 0) {
                            nodeSize[root] += nodeSize[node[1]];
                            root = rootIndex[root];
                        }
                    }
                }
            }
            
            int answer = operate(childNode);
            System.out.println("#" + test_case + " " + answer + " " + nodeSize[answer]);
        }
    }
    
    public static int operate(int[] childNode) {
        for (int i = 0; i < 2; i++) {
            int root = childNode[i];
            
            while (root > 0) {
                if (visited[root]) {
                    return root;
                } else {
                    visited[root] = true;
                    root = rootIndex[root];
                }
            }
            
        }
        return -9999;
    }
}
