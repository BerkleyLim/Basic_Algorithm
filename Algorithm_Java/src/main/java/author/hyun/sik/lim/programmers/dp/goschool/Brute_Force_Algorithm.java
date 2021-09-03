package author.hyun.sik.lim.programmers.dp.goschool;

import java.util.LinkedList;
import java.util.Queue;

public class Brute_Force_Algorithm {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};
        
        System.out.println(solution(m,n,puddles));

    }

    
    // 등굣길 문제
    // 최단 거리 갯수 구하는 알고리즘, Brute-force 방식 접근
    // 캡슐화
    static class PathList {
        public PathList(int x, int y, int count) {
            // TODO Auto-generated constructor stub
            this.x=x;
            this.y=y;
            this.count=count;
        }
        int x;
        int y;
        int count;
    }
    static Queue<PathList> queue;
    static int[][] map;
    static boolean isExit;
    
    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        isExit = false;
        map = new int[m+1][n+1];
        queue = new LinkedList<>();
        
        // map 배열에서 -1 : 웅덩이, 2 : 도착점
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            map[x][y] = 1;
        }
        map[m][n] = 2;
        
        queue.offer(new PathList(1,1,0));
        
        // 최단경로 찾기 시작!
        while(!queue.isEmpty()) {
            int endPoint = 0;
            int length = queue.size();
            
            for (int i = 0; i < length; i++) {
                PathList p = queue.poll();
                endPoint += down(m, n, p);
                endPoint += right(m, n, p);
            }
            
            // 경로 도착시!
            if (isExit) {
                answer = endPoint % 1000000007;
                break;
            }
        }
        
        
        return answer;
    }
    
    private static int down (int m, int n, PathList p) {
        int x = p.x;
        int y = p.y + 1;
        int count = p.count + 1;
        
        if (x == m && y == n)
            isExit = true;
        
        if (!outWall(x, y,m,n)) {
            queue.offer(new PathList(x, y, count));
            return 1;
        } else {
            return 0;
        }
    }
    
    private static int right (int m, int n, PathList p) {
        int x = p.x + 1;
        int y = p.y;
        int count = p.count + 1;
        
        if (x == m && y == n)
            isExit = true;
        
        if (!outWall(x, y,m,n)) {
            queue.offer(new PathList(x, y, count));
            return 1;
        } else {
            return 0;
        }
    }
    
    private static boolean outWall(int x, int y, int m, int n) {
        if (x > m || y > n)
            return true;
        else if (map[x][y] < 0)
            return true;
        else
            return false;
    }
    
}
