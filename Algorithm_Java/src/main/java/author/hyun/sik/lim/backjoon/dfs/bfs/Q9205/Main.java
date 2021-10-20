package author.hyun.sik.lim.backjoon.dfs.bfs.Q9205;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 맥주 마시면서 걸어가기
    // 상근이와 친구는 축제 가는 도중에 맥주를 마시면서 걷는다.
    // 출발 : 상근이 집, 맥주 한 박스 들고 감
    // 맥주 1박스 : 20 개
    // 걸을 때 50m당 1병 마시기
    // 먼 거리에 따라 편의점에서 맥주를 구매 가능하고, 1박스만 들고 갈 수 있고, 빈병을 버릴 수 있다.
    // 좌표 정보 : 편의점, 상근이네집, 축제장소
    // 입력 : t(테케)
    //       n(편의점 개수)
    //       상근이 좌표
    //       각 편의점 개수
    //       축제 좌표\
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        String[] string = new String[T];
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            
            int[][] info = new int[N+2][2];
            for (int i = 0; i < info.length; i++) {
                info[i][0] = sc.nextInt();
                info[i][1] = sc.nextInt();
            }
            
            string[test_case - 1] = solution(N + 2, info);
        }
        
        for (String str : string)
            System.out.println(str);
    }

    static boolean[][] visited;
    private static String solution(int N, int[][] info) {
        // TODO Auto-generated method stub
        int beerCount = 20;
        boolean flag = false;
        
        visited = new boolean[N][N];
        // 문제는 dfs, bfs이지만, dp와 섞어서 쓴다
        if (direct(info[0], info[N-1], beerCount))
            return "happy";
        
        flag = bfs(info, 0, N, beerCount);
        
        return (flag)? "happy" : "sad";
    }
    
    
    // 각 노드마다 이동 했는지 체크하고, 거리차이를 구한다.
    private static boolean bfs(int[][] info, int curNode, int N, int beerCount) {
        Queue<Integer> queue = new LinkedList<>(); 
        queue.offer(curNode);
        
        while(!queue.isEmpty()) {
            int nodeNumber = queue.poll();
            
            for (int nodeIndex = 1; nodeIndex < N; nodeIndex++) {
                if(!visited[nodeNumber][nodeIndex]) {
                    if (direct(info[nodeNumber], info[nodeIndex], beerCount)) {
                        if (nodeIndex >= N - 1)
                            return true;
                        else {
                            queue.offer(nodeIndex);
                            visited[nodeNumber][nodeIndex] = true;
                        }
                        
                    }
                }
            }
        }
        
        return false;
    }
    
    // 편의점 안 걸치고 다이렉트로 갈 수 있는 경우 체크
    private static boolean direct(int[] start, int[] end, int beerCount) {
        int times = beerCount * 50;
        // TODO Auto-generated method stub
        if (Math.abs(start[0] - end[0]) 
            + Math.abs(start[1] - end[1]) <= times)
            return true;
        else
            return false;
    }

}
