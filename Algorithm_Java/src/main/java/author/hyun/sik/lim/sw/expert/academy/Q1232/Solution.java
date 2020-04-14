package author.hyun.sik.lim.sw.expert.academy.Q1232;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

// 계산식 결과 값 출력
// Tree의 중위순회로 사칙연산 구하기

// dfs 풀이
class Solution {
    static String[] vertax; // 정점
    static int size; // 정점 크기
    static boolean[][] edge; // 간선
    static boolean[] visit; // 방문여부
    static Stack <Integer> numbers; // stack 개수
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            size = sc.nextInt();
            sc.nextLine();
            
            vertax = new String[size + 1];
            edge = new boolean[size + 1][size + 1];
            visit = new boolean[size + 1];
            numbers = new Stack<>();
            
            for (int i = 1; i <= size; i++) {
                // StringTokenizer 메소드에서는 문자열을 토큰이라고 함
                // 토큰의 수가 몇개 있고, 사용할 토큰이 있는지 판별한다.
                // countToken : int로 리턴, 마지막으로 토큰을 읽어오기 전 남아있는 토큰 수
                // hasMoreToken : 토큰이 남으면 true, 없으면 false
                // nextToken : 토큰 읽어오기
                StringTokenizer token = new StringTokenizer(sc.nextLine());
                int from = Integer.parseInt(token.nextToken()); // 첫번째 토큰 읽기
                vertax[from] = token.nextToken(); // 두번째 토큰 읽기
                
                // 부모와 자식간의 노드 연결(간선)
                for (int childNodeCount = 0; childNodeCount < 2; childNodeCount++) {
                    if (token.hasMoreTokens()) {
                        int to = Integer.parseInt(token.nextToken());
                        edge[from][to] = true;
                    }
                }
                
            }
            
            dfs(1);
            // 최종 계산값 반환
            System.out.println("#" + test_case + " " + numbers.pop());
        }
    }
    
 // dfs 알고리즘! (중회순회 무시)
    public static void dfs(int x) {
        // 방문 정점 체크
        visit[x] = true;
        
        for (int i = 1; i <= size; i++) {
            // 간선 존재하고 방문 하지 않을 경우!
            if (edge[x][i] && !visit[i])
                dfs(i);
        }
        calculation(vertax[x]);
    }
    
    public static void calculation(String string) {
        // 연산자일때 두개의 숫자를 스택을 비운 후 연산후 다시 삽입
        if(string.equals("+")) {
            int a = numbers.pop();
            int b = numbers.pop();
            numbers.push(b+a);
        } else if(string.equals("-")) {
            int a = numbers.pop();
            int b = numbers.pop();
            numbers.push(b-a);
        } else if(string.equals("/")) {
            int a = numbers.pop();
            int b = numbers.pop();
            numbers.push(b/a);
        } else if(string.equals("*")) {
            int a = numbers.pop();
            int b = numbers.pop();
            numbers.push(b*a);
        } else {
            // 정수일때 숫자를 무조건 스택에 집어 넣는다.
            int num = Integer.parseInt(string);
            numbers.push(num);
        }
    }
}
