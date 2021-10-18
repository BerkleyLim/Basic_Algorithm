package author.hyun.sik.lim.backjoon.dfs.bfs.Q5014;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 스타트와 링크
    // 스타트링크 회사 면접날 배경
    // 총 F층으로 구성
    // F층 - 사무실
    // G층 - 스타트 링크 위치
    // S층 - 면접자 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int F = sc.nextInt(); // 층 개수
        int S = sc.nextInt(); // 면접자
        int G = sc.nextInt(); // 스타트링크 위치
        int U = sc.nextInt(); // 엘레비에터 올라가는 버튼 클릭시 U만큼 이동
        int D = sc.nextInt(); // 엘레비에터 내려가는 버튼 클릭시 D만큼 이동
        
        int answer = solution(F,S,G,U,D);
        System.out.println((answer == Integer.MAX_VALUE) ? "use the stairs" : answer);
    }

    static boolean[] visited;
    static int answer; 
    static int[] upDown;
    static int[] floorTarget;
    
    private static int solution(int F, int S, int G, int U, int D) {
        // TODO Auto-generated method stub
        answer = Integer.MAX_VALUE;
        visited = new boolean[F+1];
        upDown = new int[2];
        upDown[0] += U;
        upDown[1] -= D;
        floorTarget = new int[F+1];
//        Arrays.fill(floor, Integer.MAX_VALUE);
        
//        dfs(S, G, F, 0, U, D);
        
        if (S == G) return 0;
        
        bfs(S,F,G);
        return answer;
    }
    
    private static void bfs(int start, int F, int target) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int floor = queue.poll();
            
            for (int i = 0; i < upDown.length; i++) {
                if (floor + upDown[i] < 1 || floor + upDown[i] > F || visited[floor + upDown[i]] == true)
                    continue;
                
                queue.offer(floor + upDown[i]);
                visited[floor + upDown[i]] = true;
                floorTarget[floor + upDown[i]] = floorTarget[floor] + 1; 
                
                
                if (floor + upDown[i] == target) {
                    answer = floorTarget[floor + upDown[i]];
                    return;
                }
            }
        }
        
    }
    
//    private static void dfs(int start, int target, int F, int count, int U, int D) {
////        System.out.println(start);
//        visited[start]++;
//        // TODO Auto-generated method stub
//        if (start == target) {
//            answer = Math.min(answer, count);
//            return;
//        }
//        
//        if(floor[start] > count) {
//            floor[start] = count++;
//            
//            int up = start + U;
//            int down = start- D;
//            
//            if (up <= F && visited[up] < 2) {
//                dfs(up, target, F, count, U, D);
//            }
//            if (down > 0 && visited[down] < 2) {
//                dfs(down, target, F, count, U, D);
//            }
//            
//        }
//        
//        
//        for (int i = 0; i < 2; i++) {
//            if (upDown[i] == 0 || start+upDown[i] < 1 || start+upDown[i] > F)
//                continue;
//            
//            
//            visited[start+upDown[i]] = true;
//            dfs(start+upDown[i], target, F, count+1);
//            visited[start+upDown[i]] = false;
//            
//            // 시간절약을 위한 것 
//            if (isExit)
//                return;
//        }
//    }

}
