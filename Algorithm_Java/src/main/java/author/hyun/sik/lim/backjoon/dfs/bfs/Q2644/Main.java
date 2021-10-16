package author.hyun.sik.lim.backjoon.dfs.bfs.Q2644;

import java.util.Scanner;

public class Main {
    // 촌수 계산
    // 가족 or 친척들 사이 관계 : 촌수
    // 부모와 자식 : 1촌
    // 나와 할아버지 : 2촌
    // 아버지 형체와 할아버지 : 1촌
    // 나와 아버지 : 3촌
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt(); // 전체 사람의 수 
        int start = sc.nextInt(); int end = sc.nextInt(); // 서로 다른 두사람의 번호 (촌수 계산을 위해)
        int m = sc.nextInt(); // 부모 자식들 간의 관계의 수
        int[][] relation = new int[m][2];
        
        for (int i = 0; i < m; i++) {
            relation[i][0] = sc.nextInt();
            relation[i][1] = sc.nextInt();
        }
        
        System.out.println(solution(n,start,end,m,relation));
    }

    
    static boolean visited[][];
    static int answer;
    static boolean DFSExit;
    private static int solution(int n, int start, int end, int m, int[][] relation) {
        // TODO Auto-generated method stub
        answer = -1;
        int[][] chon = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        for (int i = 0; i < m; i++) {
            chon[relation[i][0]][relation[i][1]] = 1;
            chon[relation[i][1]][relation[i][0]] = 1;
        }
        DFSExit = false;
        dfs(start, end, n, 0, chon);
        
        return answer;
    }
    
    private static void dfs(int start , int end, int n, int depth, int[][] chon) {
        if (start == end) {
            answer = depth;
            DFSExit = true;
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            if(DFSExit)
                return;
            
            if (visited[start][i]) {
                continue;
            }
            if (chon[start][i] > 0) {
                visited[start][i] = true;
                visited[i][start] = true;
                dfs(i, end, n, depth+1, chon);
            }
        }
        
    }

}
