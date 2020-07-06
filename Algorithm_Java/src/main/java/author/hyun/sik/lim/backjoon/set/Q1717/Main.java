package author.hyun.sik.lim.backjoon.set.Q1717;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


//집합의 표현 : Union - Find 문제

//여기선 각각의 N+1개의 집합을 이루고 있으며 합집합 연산과, 
//두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행

//집합을 표현하는 프로그램 작성하기

//0 ~ n+1 집합
//m은 입력으로 주어지는 연산
//첫째줄 : N(1 ~ 100만), M : (1 ~ 10만)
//M개 줄까지 : 3개의 정수 : ex (0, a, b)

//합집합 : 0 a b의 형태 
//이는 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미
//두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산 : 1 a b
//이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다. 
//a와 b는 n 이하의 자연수 또는 0이며 같을 수도 있다.

//정리 : 앞 정수는 0 이면 합치고
//         1 이면 각각 일치한지 찾는다.

//참조 : https://twpower.github.io/115-union-find-disjoint-set


public class Main {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //
        st = new StringTokenizer(br.readLine());
        // N = 사이즈, M = 입력 받을 갯수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 1. 배열 초기화 (입력 조건이 주어지면 반복문으로 할 것)
        // 처음에는 각 원소들은 연결된 정보가 없어서 부모가 자기 자신을 가짐
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        // 2. Find 함수 : x로 들어온 원소의 Root 노드를 반환
//        Find(1);
        // 3. Union 함수 : x원소와 y원소를 합치는 함수로 y의 Root 노드를 x로 한다.
//        Union(1,2);
        
        int exitCount = 0;
        // M만큼 작업 시작
        while (exitCount < M) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // operation이 0일때 Union, 1일때 Find
            // 즉 0은 합집합 만들고
            // 1일때 합집합 여부 검사
            if (operation == 0) {
                Union(a,b);
            } else {
                int FindA = Find(a);
                int FindB = Find(b);
                
                if (FindA == FindB) {
                    bw.write("YES" + "\n");
                } else 
                    bw.write("NO" + "\n");
            }
            
            exitCount++;
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
