package author.hyun.sik.lim.sw.expert.academy.Q1211;

import java.util.Scanner;

// 사다리 게임
// 어느 사다리 고르면 x표시에 도착하는 게임
// map 상에서 
// 사다리 생성시 0은 빈공간, 1은 평면, 2는 도착지점
// 백트레킹 알고리즘 구현
// 출력 : 시작점 x좌표 
// 사다리 크기 : 100 * 100
// 다음은 이동거리 가장 짧은 최소값 구하기, 복수개인 경우 가장 큰 좌표 구하기!
public class Solution {
    // 사이즈 크기 : 100
    private static final int SIZE = 100;
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        
        
        for(int test_case = 1; test_case <= T; test_case++) {
            int num = sc.nextInt();
            
            int[][] map = new int[SIZE][SIZE];
            
            // 시작점 지정
            int[] startPointX = new int[SIZE];
            
            // 시작점 개수 
            int startPointCountX = 0;
            for(int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    map[y][x] = sc.nextInt();
                    
                    // 시작점 개수 지정
                    if(y == 0 && map[0][x] == 1) {
                        startPointX[startPointCountX++] = x;
                    }
                }
            }
            
            // 출발점 설정, 시작좌표 정답 선언 (초기식)
            int minDistance = 999999;
            int answer = -999999;
            
            // 백트레킹 알고리즘 실시
            for(int x = 0; x < startPointCountX; x++) {
                // 최종 도착지점 설정 및 거리 정의
                int arrivedPointX = startPointX[x];
                int arrivedCount = 0;
                
                for (int y = 0; y < SIZE; y++) {
                    // 오른쪽 탐색
                    if (arrivedPointX != (SIZE - 1) && map[y][arrivedPointX+1] == 1) {
                        while (arrivedPointX != (SIZE - 1) && map[y][arrivedPointX+1] == 1) {
                            arrivedPointX++;
                            arrivedCount++;
                        }
                        // 왼쪽 탐색
                    } else if (arrivedPointX != 0 && map[y][arrivedPointX-1] == 1) {
                        while (arrivedPointX != 0 && map[y][arrivedPointX-1] == 1) {
                            arrivedPointX--;
                            arrivedCount++;
                        }
                        // 진행
                    } else {
                        arrivedCount++;
                    }
                }
                
                // 최소거리 지정
                if (minDistance > arrivedCount) {
                    minDistance = arrivedCount;
                    answer = startPointX[x];
                }
            }
            
            System.out.println("#"+num+" "+answer);
            
        }
    }

}
