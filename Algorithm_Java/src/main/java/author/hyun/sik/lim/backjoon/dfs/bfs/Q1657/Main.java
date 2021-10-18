package author.hyun.sik.lim.backjoon.dfs.bfs.Q1657;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 숨바꼭질
    // 수빈이와 수빈이의 동생이랑 숨바꼭질한다.
    // 수빈이 위치 : N
    // 수빈이 동생 : K
    // 수빈이는 걷거나 순간이동 가능
    // 수빈이의 위치가 X일때 
    // 걸을 시 : 1초 후 X-1 or X+1로 이동 가능
    // 순간이동시 : 1초 후 2*X
    // 수빈이의 동생을 찾을 수 있는 가장 빠른 시간은?
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        
//        System.out.println(solution(N,K));
        System.out.println(bfs(N,K));
    }

//    private static int solution(int N, int K) {
//        // TODO Auto-generated method stub
//        return bfs(N,K);
//    }
    
    static class Info{
        int n;
        int second;
        
        public Info (int n, int second) {
            this.n = n;
            this.second = second;
        }
    }
    private static int bfs(int N, int K) {
//        int answer = 0;
        
       if (N == K)
           return 0;
        
        boolean[] visited = new boolean[1000001];
//        Queue<Integer> queue = new LinkedList<>();
        Queue<Info> queue = new LinkedList<>();
        
//        queue.offer(N);
        queue.offer(new Info(N, 0));
        visited[N] = true;
//        boolean flag = true;
        
        while(!queue.isEmpty()) {
            
//            int n = queue.poll();
            Info info = queue.poll();
            int n = info.n;
            int second = info.second;
            
            for (int i = 0; i < 3; i++) {
                int dir = move(n,i);
                if (dir < 0 || dir > 1000000)
                    continue;
                
                if (dir == K) { 
//                    flag = false;
                    queue.clear();
                    return second + 1;
                }
                
                if (visited[dir])
                    continue;
                
                visited[dir] = true;
                queue.offer(new Info(dir,second+1));
                
            }
//            answer++;
//            System.out.println(answer + " " + queue.size());
        }
        
//        return answer;
        return 0;
    }
    
    private static int move(int n, int i) {
        if (i == 0) {
            return n + 1;
        } else if (i == 1) {
            return n - 1;
        } else {
            return n * 2;
        }
        
    }
    


}
