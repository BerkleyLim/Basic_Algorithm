package author.hyun.sik.lim.sw.expert.academy.Q7396;

import java.util.Scanner;

// 문제 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWm8hNu6llcDFASj&categoryId=AWm8hNu6llcDFASj&categoryType=CODE
// 7396. 종구의 딸이름 짓기
// N행 M열 보드 각 칸안에 알파벳 소문자 하나씩 기입하여 작명소에 제출!
// 왼쪽 위부터 시작하여 오른쪽 or 아래쪽으로 이동하여 최종적으로 이름을 짓는다.

public class Q7396 {
    // 이동 관련 좌표 0번째 : 아래로 이동, 1번째 오른쪽으로 이동, 2번째 : 대각선(오른쪽, 아랫쪽 동일시)
    final static int[] dx = {0, 1, 1};
    final static int[] dy = {1, 0, 1};
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        // 구슬 탈출 문제 (백준 Q13460 문제와 동일)
        for(int test_case = 1; test_case <= T; test_case++) {
            // N행 * M열 보드 입력
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();
            String[][] map = new String[N][M];
            
            // 보드 입력
            for (int y = 0; y < N; y++) {
                String string = sc.nextLine();
                map[y] = string.split("");
            }
            
            String answer = logic(map, N, M);
            
            System.out.println("#" + test_case + " " + answer);
        }
    }
    
    public static String logic(String[][] map, int N, int M) {
        // 초기 값
        String name = map[0][0];
        
        int startX = 0;
        int startY = 0;
        while(true) {
            // 여기서 도달시!
            if ((N == startX && ((M - 1) == startY) ||
                    (N - 1) == startX && M == startY)) {
                break;
            }
            
            // 아랫쪽 값과 오른쪽값 비교 (양수값일 시 아래값이 더 큰 것이고, 음수 값일 시 오른값이 더 큰것이다) 
            int isValue = map[startY+dy[0]][startX+dx[0]].compareTo(
                    map[startY+dy[1]][startX+dx[1]]);
            
            if (!(startY == N) && isValue > 0) {
                // 여기는 아랫쪽 값이 더 크다 따라서 오른쪽 이동
                startX += dx[1];
                startY += dx[1];
                name += map[startY][startX];
                
            } else if (!(startX == M) && isValue < 0) {
                // 여기는 오른쪽 값이 더 크다 따라서 아랫쪽 이동
                startX += dx[0];
                startY += dx[0];
                name += map[startY][startX];
            } else {
                // 여기서 오른쪽, 아랫쪽 문자열이 같을 경우
                searchDown(map, startX, startY, M);
                searchRight(map, startX, startY, N);
            }
        }
        
        return name;
    }
    
    // 아래로 검색
    public static void searchDown(String[][] map, int startX, int startY, int M) {
        for (int i = startY; i < M; i++) {
            
        }
    }
    
    // 오른쪽으로 검색
    public static void searchRight(String map[][], int startX, int startY, int N) {
        for (int i = startX; i < N; i++) {
            
        }
    }
}
