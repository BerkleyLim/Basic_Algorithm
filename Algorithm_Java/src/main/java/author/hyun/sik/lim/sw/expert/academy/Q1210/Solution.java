package author.hyun.sik.lim.sw.expert.academy.Q1210;

import java.util.Scanner;

// 사다리 게임
// 어느 사다리 고르면 x표시에 도착하는 게임
// map 상에서 
// 사다리 생성시 0은 빈공간, 1은 평면, 2는 도착지점
// 백트레킹 알고리즘 구현
// 출력 : 시작점 x좌표 
// 사다리 크기 : 100 * 100
public class Solution {
    // 사이즈 크기 : 100
    private static final int SIZE = 100;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        
        
        for(int test_case = 1; test_case <= T; test_case++) {
            int num = sc.nextInt();
            
            int[][] map = new int[SIZE][SIZE];
            
            int arrivePointX = 0;
            for(int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    map[y][x] = sc.nextInt();
                    
                    // 기준점 설정
                    if(map[y][x] == 2)
                        arrivePointX = x;
                }
            }
            
            // 출발점 설정
            int startPointX = arrivePointX;
            
            // 백트레킹 알고리즘 실시
            for(int y = SIZE-1; y >= 0; y--) {
                // 오른쪽 탐색
                if (startPointX != (SIZE - 1) && map[y][startPointX+1] == 1) {
                    while (startPointX != (SIZE - 1) && map[y][startPointX+1] == 1) {
                        startPointX++;
                    }
                    // 왼쪽 탐색
                } else if (startPointX != 0 && map[y][startPointX-1] == 1) {
                    while (startPointX != 0 && map[y][startPointX-1] == 1) {
                        startPointX--;
                    }
                    // 진행
                } else {
                    
                }
            }
            
            System.out.println("#"+num+" "+startPointX);
            
        }
    }

}
