package author.hyun.sik.lim.programmers.DFS.BFS.network;


// 참조 : https://velog.io/@ajufresh/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EB%AC%B8%EC%A0%9C%ED%92%80%EC%9D%B4-Java
// 네트워크 문제

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // return : 2
        int n = 3;
        int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
        System.out.println(solution(n,computers));
        
        // return : 1
        n = 3;
        computers = new int[][]{{1,1,0}, {1,1,1}, {0,1,1}};
        System.out.println(solution(n,computers));
    }
    
    // BFS 문제
    // n 은 200 이내
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        
        // 연결되는 갯수
        boolean[] check = new boolean[n];
        
        for (int x = 0; x < n; x++) {
            if (!check[x]) {
                dfs(computers, x, check);
                answer++;
            }
        }
        
        return answer;
    }

    private static boolean[] dfs(int[][] computers, int x, boolean[] check) {
        // TODO Auto-generated method stub
        check[x] = true;
        
        for (int y = 0; y < computers.length; y++) {
            if ((x != y) && (computers[x][y] == 1) && !check[y]) {
                check = dfs(computers, y, check);
            }
        }
        return check;
    }

}
