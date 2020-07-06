package author.hyun.sik.lim.basic.algorithm.practice.union.find.no1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// Union find 예제 1
// 참조 : https://twpower.github.io/115-union-find-disjoint-set
// 여러 서로소 집합의 정보를 저장하고 있는 자료구조를 의미
// 응용으로는 집합 알고리즘을 응용하는 개념으로 생각해보기!
// 여기서 순열 조합으로도 구현 가능 (경우의 수 활용 가능)

// 예제
// {1, 2, 3, 4, 5, 6, 7, 8} 이라는 숫자가 주어질 시
// {1, 2, 5, 6, 8}, {3, 4}, {7} 로 서로서 집합 나눠보기
public class Solution {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st;
        
        //
//        st = new StringTokenizer(br.readLine());
        // N = 사이즈, M = 
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
        
        // 1. 배열 초기화 (입력 조건이 주어지면 반복문으로 할 것)
        // 처음에는 각 원소들은 연결된 정보가 없어서 부모가 자기 자신을 가짐
        parent = new int[9];
        for (int i = 0; i <= 9; i++) {
            parent[i] = i;
        }
        
        // 2. Find 함수 : x로 들어온 원소의 Root 노드를 반환
        Find(1);
        
        // 3. Union 함수 : x원소와 y원소를 합치는 함수로 y의 Root 노드를 x로 한다.
        Union(1,2);
        
        
        while (true) {
            break;
        }
        
        br.close();
        bw.close();
    }
    
    // 3. Union 함수 : x원소와 y원소를 합치는 함수로 y의 Root 노드를 x로 한다.
    public static void Union(int x, int y) {
        // TODO Auto-generated method stub
        
        // x, y 원소가 들어오면
        // 각 x에는 들어온 x 루트 노드 y에는 들어온 y 루트 노드를 저장해서 비교하고
        // x,y를 붙이는 방식 --> y의 Root 노드를 x로 설정
        x = Find(x);
        y = Find(y);
        
        if (x != y) {
            parent[y] = x;
        }
    }

    // 2. Find 함수 : x로 들어온 원소의 Root 노드를 반환
    public static int Find(int x) {
        // TODO Auto-generated method stub
        
        // Root인 경우 x를 반환
        if (x == parent[x]) {
            return x;
        } else {
            int p = Find(parent[x]);
            parent[x] = p;
            return p;
        }
    }

}
